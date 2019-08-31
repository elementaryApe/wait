package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.ChannelRole;

import java.util.List;

public interface ChannelRoleMapper {

    int insert(ChannelRole record);

    int insertSelective(ChannelRole record);

    List<ChannelRole> getChannelRoleList(ChannelRole channelRole);
}