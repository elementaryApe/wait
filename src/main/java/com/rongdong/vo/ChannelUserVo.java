package com.rongdong.vo;

import com.rongdong.model.priDataSource.ChannelUser;

/**
 * @author hsh
 * @create 2018-04-03 18:38
 **/
public class ChannelUserVo extends ChannelUser {

    private String queryDate;

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }
}
