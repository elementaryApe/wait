package com.rongdong.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.constant.CommonConstants;
import com.rongdong.controller.base.BaseController;
import com.rongdong.exception.SignatureException;
import com.rongdong.model.priDataSource.*;
import com.rongdong.service.*;
import com.rongdong.utils.CalculateUtils;
import com.rongdong.utils.ResultUtils;
import com.rongdong.utils.StringUtil;
import com.rongdong.utils.UUIDUtils;
import com.rongdong.vo.ChannelInfoVo;
import com.rongdong.vo.ChannelUserVo;
import com.rongdong.vo.UserInfoVo;
import io.jsonwebtoken.JwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 前端用户
 *
 * @author hsh
 * @create 2018-03-21 17:42
 **/
@Api(value = "用户管理", description = "用户管理相关操作文档简介", position = 100, protocols = "http")
@RestController
@RequestMapping(value = "/user/mange")
public class UserMangController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelUserInfoService channelUserInfoService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ChannelService channelService;
    @Autowired
    private ParameterService parameterService;


    @ApiOperation(value = "获取用户信息", notes = "根据用户ID 获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "datatype", value = "数据库类型", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/{id}/{datatype}", method = RequestMethod.GET)
    public String getUserById(@PathVariable("id") String userId,
                              @PathVariable("datatype") Integer dataType,
                              @CookieValue("authorization") String authorization
                              /*@RequestHeader("authorization") String authorization*/) {
        try {
            ChannelInfo authentication = getAuthentication(authorization, false);

            UserInfo user = userService.getUserById(userId, dataType);
            if (user == null)
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                        CommonConstants.ErrorCode.ERROR_DATA_NO_EXIST.getDescription(), null);
            JSONObject info = new JSONObject();
            JSONObject userInfo = new JSONObject();

            //基本信息
            userInfo.put("userId", ResultUtils.parseResult(user.getId(), ""));

            ChannelUserVo channelUser = new ChannelUserVo();
            channelUser.setChannelId(authentication.getId());
            channelUser.setUserId(user.getId());
            if (channelUserInfoService.isConnectUserByChannel(channelUser)) {
                userInfo.put("idCard", ResultUtils.parseResult(user.getIdCard(), ""));
                userInfo.put("userName", ResultUtils.parseResult(user.getRealName(), ""));
                userInfo.put("phone", ResultUtils.parseResult(user.getPhone(), ""));
                userInfo.put("isConnect", "0");
            } else {
                userInfo.put("idCard", ResultUtils.parseResult(StringUtil.encryptString(user.getIdCard(), 4, 4), ""));
                userInfo.put("userName", ResultUtils.parseResult(StringUtil.encryptString(user.getRealName(), 1, 0), ""));
                userInfo.put("phone", ResultUtils.parseResult(StringUtil.encryptString(user.getPhone(), 3, 4), ""));
                userInfo.put("createTime", ResultUtils.parseResult(user.getCreateTime(), ""));
                userInfo.put("isConnect", "1");
            }
            Parameter parameter;
            if (StringUtils.isNotEmpty(user.getIdCard()) && StringUtils.isNotEmpty(user.getRealName()))
                parameter = parameterService.selectByParamCode(CommonConstants.USER_AUTHEN);
            else
                parameter = parameterService.selectByParamCode(CommonConstants.USER_NO_AUTHEN);
            if (parameter == null || StringUtils.isEmpty(parameter.getParamValue()))
                throw new IllegalArgumentException("系统参数有误，请检查");
            userInfo.put("userPrice",ResultUtils.parseResult(parameter.getParamValue(),"0"));
            info.put("userInfo", userInfo);
            //详情信息
            JSONObject detailsInfo = new JSONObject();
            UserDetails userDetails = userDetailsService.findUserDetailsByUserInfoId(userId, dataType);
            if (userDetails != null) {
                detailsInfo.put("detailsId", userDetails.getId());
                detailsInfo.put("occupation", ResultUtils.parseResult(userDetails.getOccupation(), ""));
                detailsInfo.put("incomeForm", ResultUtils.parseResult(userDetails.getIncomeForm(), ""));
                detailsInfo.put("monthlyIncome", ResultUtils.parseResult(userDetails.getMonthlyIncome(), ""));
                detailsInfo.put("companyType", ResultUtils.parseResult(userDetails.getCompanyType(), ""));
                detailsInfo.put("workingTime", ResultUtils.parseResult(userDetails.getWorkingTime(), ""));
                detailsInfo.put("fund", ResultUtils.parseResult(userDetails.getFund(), ""));
                detailsInfo.put("socialSecurity", ResultUtils.parseResult(userDetails.getSocialSecurity(), ""));
                detailsInfo.put("academic", ResultUtils.parseResult(userDetails.getAcademic(), ""));
                detailsInfo.put("isMarried", ResultUtils.parseResult(userDetails.getIsMarried(), ""));
            }
            info.put("userDetails", detailsInfo);
            Property property = userDetailsService.findPropertyByUserInfoId(userId, dataType);
            JSONObject propertyInfo = new JSONObject();
            if (property != null) {
                propertyInfo.put("propertyId", property.getId());
                propertyInfo.put("hasCreditCard", ResultUtils.parseResult(property.getHasCreditCard(), ""));
                propertyInfo.put("house", ResultUtils.parseResult(property.getHouse(), ""));
                propertyInfo.put("car", ResultUtils.parseResult(property.getCar(), ""));
                propertyInfo.put("hasSuccessLoan", ResultUtils.parseResult(property.getHasSuccessLoan(), ""));
                propertyInfo.put("credit", ResultUtils.parseResult(property.getCredit(), ""));
            }
            info.put("property", propertyInfo);
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

    @ApiOperation(value = "获取用户信息", notes = "分页获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataType", value = "数据库类型", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "Integer", defaultValue = "10"),
            @ApiImplicitParam(name = "idCardType", value = "身份证是否为空(0允许为空,1不允许为空)", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "realNameType", value = "用户真实姓名是否为空(0允许为空,1不允许为空)", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "idCard", value = "身份证", dataType = "String"),
            @ApiImplicitParam(name = "queryDate", value = "查询日期", dataType = "String"),
            @ApiImplicitParam(name = "realName", value = "真实姓名", dataType = "String")
    })
    @RequestMapping(value = "/page/{dataType}/{pageIndex}", method = RequestMethod.GET)
    public String getUserInfoWithPage(@PathVariable("dataType") Integer dataType,
                                      @PathVariable(value = "pageIndex") Integer pageIndex,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                      @RequestParam(value = "idCardType", required = false, defaultValue = "0") String idCardType,
                                      @RequestParam(value = "realNameType", required = false, defaultValue = "0") String realNameType,
                                      @RequestParam(value = "phone", required = false) String phone,
                                      @RequestParam(value = "queryDate", required = false) String queryDate,
                                      @RequestParam(value = "idCard", required = false) String idCard,
                                      @RequestParam(value = "realName", required = false) String realName,
                                      @CookieValue("authorization") String authorization
                                     /*@RequestHeader("authorization") String authorization*/) {
        try {
            ChannelInfoVo authentication = getAuthentication(authorization, false);
            UserInfoVo userInfo = new UserInfoVo();
            userInfo.setPhone(phone);
            userInfo.setIdCard(idCard);
            userInfo.setRealName(realName);
            userInfo.setIdCardType(idCardType);
            userInfo.setRealNameType(realNameType);
            userInfo.setDateSourceType(dataType);
            userInfo.setQueryDate(queryDate);
            JSONObject info = new JSONObject();
            PageInfo<UserInfo> data = userService.findUserInfoWithPageForOneDataSource(userInfo, new PageInfo<>(pageIndex, pageSize));
            List<UserInfo> list = data.getList();
            info.put("totalCount", data.getTotal());
            info.put("pageIndex", pageIndex);
            if (CollectionUtils.isNotEmpty(list)) {
                JSONArray infoArray = new JSONArray();
                for (UserInfo user : list) {
                    JSONObject one = new JSONObject();
                    one.put("userId", ResultUtils.parseResult(user.getId(), ""));
                    ChannelUserVo channelUser = new ChannelUserVo();
                    channelUser.setChannelId(authentication.getId());
                    channelUser.setUserId(user.getId());
                    if (channelUserInfoService.isConnectUserByChannel(channelUser)) {
                        one.put("idCard", ResultUtils.parseResult(user.getIdCard(), ""));
                        one.put("userName", ResultUtils.parseResult(user.getRealName(), ""));
                        one.put("phone", ResultUtils.parseResult(user.getPhone(), ""));
                        one.put("createTime", ResultUtils.parseResult(user.getCreateTime(), ""));
                        one.put("isConnect", "0");
                    } else {
                        one.put("idCard", ResultUtils.parseResult(StringUtil.encryptString(user.getIdCard(), 4, 4), ""));
                        one.put("userName", ResultUtils.parseResult(StringUtil.encryptString(user.getRealName(), 1, 0), ""));
                        one.put("phone", ResultUtils.parseResult(StringUtil.encryptString(user.getPhone(), 3, 4), ""));
                        one.put("createTime", ResultUtils.parseResult(user.getCreateTime(), ""));
                        one.put("isConnect", "1");
                    }
                    Parameter parameter;
                    if (StringUtils.isNotEmpty(user.getIdCard()) && StringUtils.isNotEmpty(user.getRealName()))
                        parameter = parameterService.selectByParamCode(CommonConstants.USER_AUTHEN);
                    else
                        parameter = parameterService.selectByParamCode(CommonConstants.USER_NO_AUTHEN);
                    if (parameter == null || StringUtils.isEmpty(parameter.getParamValue()))
                        throw new IllegalArgumentException("系统参数有误，请检查");
                    one.put("userPrice",ResultUtils.parseResult(parameter.getParamValue(),"0"));
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

    @ApiOperation(value = "付费查询用户信息", notes = "付费查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataType", value = "数据库类型", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String")
    })
    @RequestMapping(value = "/{dataType}/{userId}", method = RequestMethod.POST)
    public String addConnect(@PathVariable("dataType") Integer dataType,
                             @PathVariable("userId") String userId,
                             @CookieValue("authorization") String authorization) {
        try {
            ChannelInfoVo authentication = getAuthentication(authorization, false);
            ChannelUserVo channelUser = new ChannelUserVo();
            channelUser.setChannelId(authentication.getId());
            channelUser.setUserId(userId);
            channelUser.setDataType(dataType);
            if (channelUserInfoService.isConnectUserByChannel(channelUser))
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                        CommonConstants.ErrorCode.ERROR_DATA_EXIST.getDescription(), "");
            //查询用户余额是否足够
            //1确认查询的用户扣费级别
            Parameter parameter;
            UserInfo user = userService.getUserById(userId, dataType);
            if (StringUtils.isNotEmpty(user.getIdCard()) && StringUtils.isNotEmpty(user.getRealName()))
                parameter = parameterService.selectByParamCode(CommonConstants.USER_AUTHEN);
            else
                parameter = parameterService.selectByParamCode(CommonConstants.USER_NO_AUTHEN);
            if (parameter == null || StringUtils.isEmpty(parameter.getParamValue()))
                throw new IllegalArgumentException("系统参数有误，请检查");
            String paramValue = parameter.getParamValue();
            ChannelInfo channelInfo = channelService.getChannelByName(authentication.getChannelName());
            String balance = channelInfo.getBalance();
            //2购买
            double subtract = CalculateUtils.subtract(Double.parseDouble(balance), Double.parseDouble(paramValue));
            if (subtract < 0)
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST, "余额不足请充值", "");
            channelUser.setId(UUIDUtils.getUUID());
            channelUser.setDataType(dataType);
            if (!channelUserInfoService.insertConnectUserByChannel(channelUser))
                return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                        CommonConstants.ErrorCode.ERROR_OPERAT_FAIL.getDescription(), null);
            //3.扣费
            channelInfo.setBalance(String.valueOf(subtract));
            channelInfo.setUserCount(String.valueOf(Integer.valueOf(channelInfo.getUserCount()) + 1));
            channelService.updateChannelInfo(channelInfo);
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

    /**
     * 查看已拥有的用户
     */
    @RequestMapping(value = "/own/page/{dataType}/{pageIndex}",method = RequestMethod.GET)
    public String getOwnUserInfoWithPage(@PathVariable("dataType") Integer dataType,
                                         @PathVariable(value = "pageIndex") Integer pageIndex,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                         @RequestParam(value = "phone", required = false) String phone,
                                         @RequestParam(value = "queryDate", required = false) String queryDate,
                                         @RequestParam(value = "idCard", required = false) String idCard,
                                         @CookieValue("authorization") String authorization
                                     /* @RequestHeader("authorization") String authorization*/) {

        try {
            //1.根据数据库类型读取关联数据
            ChannelInfoVo authentication = getAuthentication(authorization, false);
            JSONObject info = new JSONObject();
            String queryId = "";
            if (StringUtils.isNotEmpty(idCard) || StringUtils.isNotEmpty(phone)) {
                // 2 如果搜索 先点位该数据库中是否有信息
                UserInfoVo userInfoVo = new UserInfoVo();
                userInfoVo.setPhone(phone);
                userInfoVo.setIdCard(idCard);
                List<UserInfo> userInfoList = userService.findUserInfoList(userInfoVo);
                if (CollectionUtils.isEmpty(userInfoList))
                    return ResultUtils.fillResultString(HttpServletResponse.SC_BAD_REQUEST,
                            CommonConstants.ErrorCode.ERROR_DATA_EXIST.getDescription(), null);
                queryId = userInfoList.get(0).getId();
            }
            ChannelUserVo channelUser = new ChannelUserVo();
            channelUser.setChannelId(authentication.getId());
            channelUser.setDataType(dataType);
            if (StringUtils.isNotEmpty(queryId))
                channelUser.setUserId(queryId);
            channelUser.setQueryDate(queryDate);
            PageInfo<ChannelUser> data = channelUserInfoService.findChannelUserWithPage(channelUser, new PageInfo<>(pageIndex, pageSize));
            List<ChannelUser> list = data.getList();
            info.put("totalCount", data.getTotal());
            info.put("pageIndex", pageIndex);
            if (!CollectionUtils.isEmpty(list)) {
                JSONArray infoArray = new JSONArray();
                for (ChannelUser user : list) {
                    JSONObject one = new JSONObject();
                    UserInfo userInfo = userService.getUserById(user.getUserId(), user.getDataType());
                    one.put("userId", ResultUtils.parseResult(userInfo.getId(), ""));
                    one.put("idCard", ResultUtils.parseResult(userInfo.getIdCard(), ""));
                    one.put("userName", ResultUtils.parseResult(userInfo.getRealName(), ""));
                    one.put("phone", ResultUtils.parseResult(userInfo.getPhone(), ""));
                    one.put("purchaseTime", ResultUtils.parseResult(user.getCreateTime(), ""));
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


}
