package com.rongdong.service;

import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.model.priDataSource.ChannelInfo;
import com.rongdong.model.priDataSource.ChannelRecord;
import com.rongdong.vo.ChannelRecordVo;

/**
 * 渠道商管理服务
 * Created by hsh on 2018/4/1.
 */
public interface ChannelService {

    /**
     * 添加渠道商
     */
    Boolean addChannel(ChannelInfo channelInfo) throws Exception;

    /**
     * 检查渠道是否存在(true 存在 false 不存在)
     */
    Boolean checkChannelExits(ChannelInfo query) throws Exception;

    /**
     * 根据渠道名称获取渠道
     */
    ChannelInfo getChannelByName(String channelName) throws Exception;

    ChannelInfo getChannelInfo(ChannelInfo query) throws Exception;

    boolean updateChannelInfo(ChannelInfo channelInfo) throws Exception;

    boolean insertChannelRecord(ChannelRecord channelRecord) throws Exception;

    PageInfo<ChannelInfo> findChannelInfoWithPage(ChannelInfo channelInfo, PageInfo<ChannelInfo> pageInfo) throws Exception;

    /**
     * 充值记录
     */
    PageInfo<ChannelRecord> findChannelRecordInfoWithPage(ChannelRecordVo channelRecordVo, PageInfo<ChannelRecord> pageInfo) throws Exception;

}
