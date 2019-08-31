package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.ChannelInfo;

import java.util.List;

public interface ChannelInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChannelInfo record);

    int insertSelective(ChannelInfo record);

    ChannelInfo selectByPrimaryKey(String id);

    List<ChannelInfo>  getChannelList(ChannelInfo query);

    int updateByPrimaryKeySelective(ChannelInfo record);

    int updateByPrimaryKey(ChannelInfo record);
}