package com.rongdong.vo;

import com.rongdong.model.priDataSource.ChannelRecord;

/**
 * @author hsh
 * @create 2018-04-02 16:18
 **/
public class ChannelRecordVo extends ChannelRecord {

    private String queryDate;

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }
}
