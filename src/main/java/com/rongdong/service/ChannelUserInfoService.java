package com.rongdong.service;

import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.model.priDataSource.ChannelUser;
import com.rongdong.vo.ChannelUserVo;


public interface ChannelUserInfoService {

    /**
     * 存在 true
     */
    boolean isConnectUserByChannel(ChannelUserVo channelUserVo) throws Exception;

    /**
     * 新增
     */
    boolean insertConnectUserByChannel(ChannelUser channelUser) throws Exception;

    /**
     * 获取渠道用户关联关系
     */
    PageInfo<ChannelUser> findChannelUserWithPage(ChannelUserVo channelUser, PageInfo<ChannelUser> pageInfo) throws Exception;

}
