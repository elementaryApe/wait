package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.ChannelUser;
import com.rongdong.vo.ChannelUserVo;

import java.util.List;

public interface ChannelUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChannelUser record);

    int insertSelective(ChannelUser record);

    ChannelUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChannelUser record);

    int updateByPrimaryKey(ChannelUser record);

    List<ChannelUser> getChannelUserList(ChannelUserVo channelUser);

}