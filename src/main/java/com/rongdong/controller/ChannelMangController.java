package com.rongdong.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.constant.CommonConstants;
import com.rongdong.controller.base.BaseController;
import com.rongdong.exception.SignatureException;
import com.rongdong.model.priDataSource.ChannelInfo;
import com.rongdong.model.priDataSource.ChannelRecord;
import com.rongdong.service.ChannelService;
import com.rongdong.utils.CalculateUtils;
import com.rongdong.utils.ResultUtils;
import com.rongdong.utils.UUIDUtils;
import io.jsonwebtoken.JwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 渠道商管理
 *
 * @author hsh
 * @create 2018-04-01 19:31
 **/
@Api(value = "渠道商管理", description = "渠道商管理接口(管理员权限)", position = 100, protocols = "http")
@RestController
@RequestMapping(value = "/channel")
public class ChannelMangController extends BaseController {

    @Autowired
    private ChannelService channelService;

    @ApiOperation(value = "获取渠道商列表", notes = "分页获取渠道商列表(管理员权限)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "当前页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10", dataType = "String"),
            @ApiImplicitParam(name = "channelId", value = "渠道商ID", dataType = "String"),
            @ApiImplicitParam(name = "channelName", value = "渠道商名称", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "渠道商状态", dataType = "Integer"),
    })
    @RequestMapping(value = "/page/{pageIndex}", method = RequestMethod.GET)
    public String getChannelList(@PathVariable(value = "pageIndex") Integer pageIndex,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(value = "channelName", required = false) String channelName,
                                 @RequestParam(value = "channelId", required = false) String channelId,
                                 @RequestParam(value = "status", required = false) Integer status,
                                 @CookieValue(value = "authorization") String authorization) {
        try {
            getAuthentication(authorization, true);
            JSONObject info = new JSONObject();
            ChannelInfo query = new ChannelInfo();
            query.setId(channelId);
            query.setChannelName(channelName);
            query.setStatus(status);
            PageInfo<ChannelInfo> channelInfoWithPage = channelService.findChannelInfoWithPage(query, new PageInfo<>(pageIndex, pageSize));
            List<ChannelInfo> channelInfos = channelInfoWithPage.getList();
            info.put("totalCount", channelInfoWithPage.getTotal());
            info.put("pageIndex", pageIndex);
            if (!CollectionUtils.isEmpty(channelInfos)) {
                JSONArray infoArray = new JSONArray();
                for (ChannelInfo channelInfo : channelInfos) {
                    JSONObject one = new JSONObject();
                    one.put("channelName", ResultUtils.parseResult(channelInfo.getChannelName(), ""));
                    one.put("channelId", ResultUtils.parseResult(channelInfo.getId(), ""));
                    one.put("channelCode", ResultUtils.parseResult(channelInfo.getChannelCode(), ""));
                    one.put("contacts", ResultUtils.parseResult(channelInfo.getContacts(), ""));
                    one.put("contactsPhone", ResultUtils.parseResult(channelInfo.getContactsPhone(), ""));
                    one.put("salesman", ResultUtils.parseResult(channelInfo.getSalesman(), ""));
                    one.put("userCount", ResultUtils.parseResult(channelInfo.getUserCount(), ""));
                    one.put("balance", ResultUtils.parseResult(channelInfo.getBalance(), ""));
                    one.put("status", ResultUtils.parseResult(channelInfo.getStatus(), ""));
                    infoArray.add(one);
                }
                info.put("infos", infoArray);
            } else {
                info.put("pageSize", 0);
                info.put("infos", new JSONArray());
            }
            return ResultUtils.fillResultString(HttpServletResponse.SC_OK, info);
        } catch (SignatureException ise) {
            return ResultUtils.fillResultString(HttpServletResponse.SC_UNAUTHORIZED,
                    ise.getMessage(), "");
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

    /**
     * 修改渠道商信息
     */
    @ApiOperation(value = "修改渠道商信息", notes = "修改渠道商信息(管理员权限)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channelId", value = "渠道商ID", dataType = "String"),
            @ApiImplicitParam(name = "channelName", value = "渠道商名称", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String"),
            @ApiImplicitParam(name = "channelCode", value = "渠道商Code", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "渠道商状态", dataType = "Integer"),
            @ApiImplicitParam(name = "contacts", value = "联系人", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "联系电话", dataType = "String"),
            @ApiImplicitParam(name = "salesman", value = "业务员", dataType = "String"),
            @ApiImplicitParam(name = "balance", value = "新增账户余额", dataType = "String")
    })
    @RequestMapping(value = "{channelId}", method = RequestMethod.PUT)
    public String updateChannelInfo(@PathVariable(value = "channelId") String channelId,
                                    @RequestParam(value = "userName", required = false) String userName,
                                    @RequestParam(value = "password", required = false) String password,
                                    @RequestParam(value = "channelCode", required = false) String channelCode,
                                    @RequestParam(value = "contacts", required = false) String contacts,
                                    @RequestParam(value = "phone", required = false) String phone,
                                    @RequestParam(value = "salesman", required = false) String salesman,
                                    @RequestParam(value = "balance", required = false) String balance,
                                    @RequestParam(value = "status", required = false) Integer status,
                                    @CookieValue(value = "authorization") String authorization) {
        try {
            getAuthentication(authorization, true);
            ChannelInfo query = new ChannelInfo();
            query.setId(channelId);
            ChannelInfo oldChannelInfo = channelService.getChannelInfo(query);
            ChannelInfo channelInfo = new ChannelInfo();
            channelInfo.setId(channelId);
            channelInfo.setChannelName(userName);
//            根据中文生成英文首字母
//            if(StringUtils.isBlank(channelCode))
//                channelCode="";
            channelInfo.setChannelCode(channelCode);
            channelInfo.setContacts(contacts);
            channelInfo.setContactsPhone(phone);
            channelInfo.setSalesman(salesman);
            if (StringUtils.isNotEmpty(balance)) {
                channelInfo.setBalance(String.valueOf(CalculateUtils.add(Double.parseDouble(balance), Double.parseDouble(oldChannelInfo.getBalance()))));
            }
            if (StringUtils.isNotEmpty(password))
                channelInfo.setChannelPwd(DigestUtils.md5Hex(password));
            channelInfo.setStatus(status);
            if (!channelService.updateChannelInfo(channelInfo))
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                        CommonConstants.ErrorCode.ERROR_OPERAT_FAIL.getDescription(), null);
            if (StringUtils.isNotEmpty(balance)) {
                //添加充值记录
                ChannelRecord channelRecord = new ChannelRecord();
                channelRecord.setId(UUIDUtils.getUUID());
                channelRecord.setChannelId(channelId);
                channelRecord.setBalance(balance);
                if (!channelService.insertChannelRecord(channelRecord))
                    return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                            CommonConstants.ErrorCode.ERROR_OPERAT_RECHARGE_FAIL.getDescription(), null);
            }
            return ResultUtils.fillResultString(HttpServletResponse.SC_OK, "", "");
        } catch (SignatureException ise) {
            return ResultUtils.fillResultString(HttpServletResponse.SC_UNAUTHORIZED,
                    ise.getMessage(), "");
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

