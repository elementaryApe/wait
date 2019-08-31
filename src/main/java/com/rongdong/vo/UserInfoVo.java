package com.rongdong.vo;

import com.rongdong.model.priDataSource.UserInfo;

/**
 * 用户查询实体
 *
 * @author hsh
 * @create 2018-03-31 14:49
 **/
public class UserInfoVo extends UserInfo {

    /**
     * 数据源 1-5
     */
    private Integer dateSourceType;
    /**
     * 身份证是否允许为空 0是 1否
     */
    private String idCardType;
    /**
     * 姓名是否允许为空 0是 1否
     */
    private String realNameType;

    private String queryDate;

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getRealNameType() {
        return realNameType;
    }

    public void setRealNameType(String realNameType) {
        this.realNameType = realNameType;
    }

    public Integer getDateSourceType() {
        return dateSourceType;
    }

    public void setDateSourceType(Integer dateSourceType) {
        this.dateSourceType = dateSourceType;
    }
}
