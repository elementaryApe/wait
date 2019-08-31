package com.rongdong.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.controller.base.BaseController;
import com.rongdong.exception.SignatureException;
import com.rongdong.model.priDataSource.LoanRecord;
import com.rongdong.model.priDataSource.Platform;
import com.rongdong.service.LoanService;
import com.rongdong.service.PlatformService;
import com.rongdong.utils.DateUtil;
import com.rongdong.utils.ResultUtils;
import io.jsonwebtoken.JwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 借款记录查询
 *
 * @author hsh
 * @create 2018-04-02 1:45
 **/
@Api(value = "借款记录查询", description = "借款记录查询接口", position = 100, protocols = "http")
@RestController
@RequestMapping(value = "loan/record")
public class LoanRecordController extends BaseController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private PlatformService platformService;

    @ApiOperation(value = "分页查询贷款记录信息", notes = "分页查询贷款记录信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataType", value = "数据库类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, defaultValue = "10", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/page/{dataType}/{pageIndex}", method = RequestMethod.GET)
    public String getUserInfoWithPage(@PathVariable("dataType") Integer dataType,
                                      @PathVariable(value = "pageIndex") Integer pageIndex,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                      @RequestParam(value = "userId") String userId,
                                      @CookieValue("authorization") String authorization) {
        try {
            getAuthentication(authorization, false);
            LoanRecord query = new LoanRecord();
            query.setUserId(userId);

            PageInfo<LoanRecord> loanRecordPages = loanService.findLoanRecordPages(query, dataType, new PageInfo<>(pageIndex, pageSize));
            List<LoanRecord> elements = loanRecordPages.getList();
            JSONObject info = new JSONObject();
            info.put("totalCount", loanRecordPages.getTotal());
            info.put("pageIndex", pageIndex);
            if (CollectionUtils.isNotEmpty(elements)) {
                info.put("pageSize", elements.size());
                JSONArray infoArray = new JSONArray();
                for (LoanRecord loanRecord : elements) {
                    JSONObject oneInfo = new JSONObject();
                    oneInfo.put("loanRecordId", loanRecord.getId());
                    oneInfo.put("platformId", loanRecord.getPlatfromId());
                    oneInfo.put("creditPeriod", ResultUtils.parseResult(loanRecord.getCreditPeriod(), ""));
                    oneInfo.put("loanAmount", ResultUtils.parseResult(loanRecord.getLoanAmount(), ""));
                    oneInfo.put("createTime", DateUtil.format(loanRecord.getCreateTime()));
                    Platform platform = platformService.selectByPrimaryKey(loanRecord.getPlatfromId(), dataType);
                    if (platform != null) {
                        oneInfo.put("platformLogo", ResultUtils.parseResult(platform.getLogo(), ""));
                        oneInfo.put("platformName", ResultUtils.parseResult(platform.getPlatformName(), ""));
                        oneInfo.put("toPlatformUrl", ResultUtils.parseResult(platform.getToPlatformUrl(), ""));
                    } else {
                        oneInfo.put("platformLogo", "");
                        oneInfo.put("platformName", "");
                        oneInfo.put("toPlatformUrl", "");
                    }
                    infoArray.add(oneInfo);
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
