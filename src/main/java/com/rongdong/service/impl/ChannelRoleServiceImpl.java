package com.rongdong.service.impl;

import com.rongdong.dao.priDataSource.ChannelRoleMapper;
import com.rongdong.model.priDataSource.ChannelRole;
import com.rongdong.service.ChannelRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hsh
 * @create 2018-04-01 15:50
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class ChannelRoleServiceImpl implements ChannelRoleService {

    @Autowired
    private ChannelRoleMapper channelRoleMapper;

    @Override
    public List<ChannelRole> getChannelRoleList(ChannelRole channelRole) throws Exception {
        return channelRoleMapper.getChannelRoleList(channelRole);
    }
}
