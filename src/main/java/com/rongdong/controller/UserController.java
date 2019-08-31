package com.rongdong.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.constant.CommonConstants;
import com.rongdong.controller.base.BaseController;
import com.rongdong.exception.SignatureException;
import com.rongdong.model.priDataSource.ChannelInfo;
import com.rongdong.model.priDataSource.ChannelRecord;
import com.rongdong.model.priDataSource.ChannelRole;
import com.rongdong.service.ChannelRoleService;
import com.rongdong.service.ChannelService;
import com.rongdong.utils.CookieUtils;
import com.rongdong.utils.ResultUtils;
import com.rongdong.utils.UUIDUtils;
import com.rongdong.vo.ChannelInfoVo;
import com.rongdong.vo.ChannelRecordVo;
import io.jsonwebtoken.JwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户登录退出等
 *
 * @author hsh
 * @create 2018-04-01 0:20
 **/
@Api(value = "渠道商服务接口", description = "渠道商服务接口操作文档简介", position = 100, protocols = "http")
@RestController
@RequestMapping(value = "/login")
public class UserController extends BaseController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelRoleService channelRoleService;

    @ApiOperation(value = "登录", notes = "渠道商登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "渠道商名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/channel", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username", required = true) String userName,
                        @RequestParam(value = "password", required = true) String passWord,
                        HttpServletResponse res) {
        try {
            ChannelInfo query = new ChannelInfo();
            query.setChannelName(userName);
            if (!channelService.checkChannelExits(query))
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                        CommonConstants.ErrorCode.ERROR_PHONE_NO_EXISTS.getDescription(), null);
            ChannelInfo channel = channelService.getChannelByName(userName);
            if (!StringUtils.equals(channel.getChannelPwd(), DigestUtils.md5Hex(passWord)))
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                        CommonConstants.ErrorCode.ERROR_PASSWORD_ERROR.getDescription(), null);
            ChannelRole channelRole = new ChannelRole();
            channelRole.setChannelId(channel.getId());
            List<ChannelRole> channelRoleList = channelRoleService.getChannelRoleList(channelRole);
            if (CollectionUtils.isEmpty(channelRoleList))
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                        CommonConstants.ErrorCode.ERROR_FORBIDDEN.getDescription(), null);
            String token = addAuthentication(res, userName, passWord, channelRoleList.get(0).getRole());
            return ResultUtils.fillResultString(HttpServletResponse.SC_OK, "", "Bearer " + token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fillResultString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage(), "");
        }
    }

    @ApiOperation(value = "退出", notes = "渠道商退出")
    @RequestMapping(value = "/out", method = RequestMethod.POST)
    public String loginout(HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.addHeader(HEADER_STRING, "");
            CookieUtils.removeCookie(HEADER_STRING);
            CookieUtils.addCookie(HEADER_STRING, "");
            return ResultUtils.fillResultString(HttpServletResponse.SC_OK, "", "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fillResultString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage(), "");
        }
    }


    @ApiOperation(value = "添加渠道商", notes = "添加渠道商信息(管理员权限)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "passWord", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "confirmPassWord", value = "确认密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "channelCode", value = "渠道商代码(登录使用)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contacts", value = "联系人", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "salesman", value = "业务员", dataType = "String"),
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "userName") String userName,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "channelCode") String channelCode,
                           @RequestParam(value = "confirmPassword") String confirmPassword,
                           @RequestParam(value = "contacts", required = false) String contacts,
                           @RequestParam(value = "phone", required = false) String phone,
                           @RequestParam(value = "salesman", required = false) String salesman,
                           @CookieValue(value = "authorization") String authorization) {
        try {
            getAuthentication(authorization, true);

            if (!StringUtils.equals(password, confirmPassword)) {
                return ResultUtils.fillResultString(CommonConstants.ErrorCode.ERROR_CONFIRM_PASSWORD.getCode(),
                        CommonConstants.ErrorCode.ERROR_CONFIRM_PASSWORD.getDescription(), null);
            }
            ChannelInfo query = new ChannelInfo();
            query.setChannelCode(channelCode);
            if (channelService.checkChannelExits(query))
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                        CommonConstants.ErrorCode.ERROR_DATA_EXIST.getDescription(), null);
            ChannelInfo channelInfo = new ChannelInfo();
            channelInfo.setId(UUIDUtils.getUUID());
            channelInfo.setChannelName(userName);
//            根据中文生成英文首字母
//            if(StringUtils.isBlank(channelCode))
//                channelCode="";
            channelInfo.setChannelCode(channelCode);
            channelInfo.setContacts(contacts);
            channelInfo.setContactsPhone(phone);
            channelInfo.setSalesman(salesman);
            channelInfo.setChannelPwd(DigestUtils.md5Hex(password));
            if (!channelService.addChannel(channelInfo)) {
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                        CommonConstants.ErrorCode.ERROR_OPERAT_FAIL.getDescription(), null);
            }
            return ResultUtils.fillResultString(HttpServletResponse.SC_OK, "", channelInfo.getId());
        } catch (SignatureException ise) {
            return ResultUtils.fillResultString(HttpServletResponse.SC_UNAUTHORIZED,
                    ise.getMessage(), "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fillResultString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage(), "");
        }
    }

    @ApiOperation(value = "获取登录信息", notes = "获取登录的渠道商信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getChanneInfoByToken(@CookieValue(value = "authorization") String authorization) {
        try {
            ChannelInfoVo authentication = getAuthentication(authorization, false);
            JSONObject info = new JSONObject();
            info.put("channelName", authentication.getChannelName());
            info.put("channelID", authentication.getId());
            info.put("channelCode", authentication.getChannelCode());
            info.put("channelCount", authentication.getUserCount());
            info.put("channelBalance", authentication.getBalance());
            info.put("channelRole", StringUtils.equals(authentication.getRole().getRole(), CommonConstants.ROLE_CHANNEL_ADMIN) ? "OK" : "NO");
            return ResultUtils.fillResultString(HttpServletResponse.SC_OK, info);
        }catch (SignatureException ise){
            return ResultUtils.fillResultString(HttpServletResponse.SC_UNAUTHORIZED ,
                    ise.getMessage(),"");
        } catch (JwtException ise) {
            ise.printStackTrace();
            return ResultUtils.fillResultString(HttpServletResponse.SC_UNAUTHORIZED,
                    ise.getMessage(), "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fillResultString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage(), "");
        }
    }

    @ApiOperation(value = "获取充值记录", notes = "获取充值记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "当前页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10", dataType = "String"),
            @ApiImplicitParam(name = "channelId", value = "渠道商ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/page/{pageIndex}", method = RequestMethod.GET)
    public String getChannelList(@PathVariable(value = "pageIndex") Integer pageIndex,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(value = "channelId") String channelId,
                                 @CookieValue(value = "authorization") String authorization) {
        try {
            ChannelInfoVo authentication = getAuthentication(authorization, false);
            JSONObject info = new JSONObject();
            //充值记录
            ChannelRecordVo query = new ChannelRecordVo();
            if (StringUtils.isNotEmpty(channelId))
                query.setChannelId(channelId);
            else
                query.setChannelId(authentication.getId());
            PageInfo<ChannelRecord> data = channelService.findChannelRecordInfoWithPage(query, new PageInfo<>(pageIndex, pageSize));
            List<ChannelRecord> channelInfos = data.getList();
            info.put("totalCount", data.getTotal());
            info.put("pageIndex", pageIndex);
            if (!org.springframework.util.CollectionUtils.isEmpty(channelInfos)) {
                JSONArray infoArray = new JSONArray();
                for (ChannelRecord channelInfo : channelInfos) {
                    JSONObject one = new JSONObject();
                    one.put("balance", ResultUtils.parseResult(channelInfo.getBalance(), ""));
                    one.put("create_time", ResultUtils.parseResult(channelInfo.getCreateTime(), ""));
                    infoArray.add(one);
                }
                info.put("infos", infoArray);
            } else {
                info.put("pageSize", 0);
                info.put("infos", new JSONArray());
            }
            return ResultUtils.fillResultString(HttpServletResponse.SC_OK, info);
        }catch (SignatureException ise){
            return ResultUtils.fillResultString(HttpServletResponse.SC_UNAUTHORIZED ,
                    ise.getMessage(),"");
        } catch (JwtException ise) {
            ise.printStackTrace();
            return ResultUtils.fillResultString(HttpServletResponse.SC_UNAUTHORIZED,
                    ise.getMessage(), "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fillResultString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage(), "");
        }

    }

}
