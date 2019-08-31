package com.rongdong.service;

import com.rongdong.model.priDataSource.ChannelRole;

import java.util.List;

/**
 * @author hsh
 * @create 2018-04-01 15:48
 **/
public interface ChannelRoleService {

    List<ChannelRole> getChannelRoleList(ChannelRole channelRole)throws Exception;
}
