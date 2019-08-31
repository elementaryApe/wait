package com.rongdong.service.impl;

import com.github.pagehelper.PageHelper;
import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.dao.priDataSource.ChannelUserMapper;
import com.rongdong.model.priDataSource.ChannelUser;
import com.rongdong.service.ChannelUserInfoService;
import com.rongdong.vo.ChannelUserVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 渠道用户关联实体
 *
 * @author hsh
 * @create 2018-04-01 23:52
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class ChannelUserInfoServiceImpl implements ChannelUserInfoService {

    @Autowired
    private ChannelUserMapper channelUserMapper;

    @Override
    public boolean isConnectUserByChannel(ChannelUserVo channelUserVo) throws Exception {
        Assert.notNull(channelUserVo, "查询条件不能为空");
        return CollectionUtils.isNotEmpty(channelUserMapper.getChannelUserList(channelUserVo));
    }

    @Override
    public boolean insertConnectUserByChannel(ChannelUser channelUser) throws Exception {
        Assert.notNull(channelUser, "数据不能为空");
        Assert.hasText(channelUser.getId(), "ID不能为空");
        Assert.hasText(channelUser.getChannelId(), "渠道商ID不能为空");
        Assert.hasText(channelUser.getUserId(), "用户ID不能为空");
        return channelUserMapper.insertSelective(channelUser) > 0;
    }

    @Override
    public PageInfo<ChannelUser> findChannelUserWithPage(ChannelUserVo channelUser, PageInfo<ChannelUser> pageInfo) throws Exception {
        Assert.notNull(channelUser, "数据不能为空");
        PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
        return pageInfo.parsePages(channelUserMapper.getChannelUserList(channelUser));
    }
}
