package com.rongdong.vo;

import com.rongdong.model.priDataSource.ChannelInfo;
import com.rongdong.model.priDataSource.ChannelRole;

import java.io.Serializable;

/**
 * 渠道商补充实体
 *
 * @author hsh
 * @create 2018-04-02 10:40
 **/
public class ChannelInfoVo extends ChannelInfo implements Serializable {

    private static final long serialVersionUID = -7241858869992481575L;
    private ChannelRole role;

    public ChannelRole getRole() {
        return role;
    }

    public void setRole(ChannelRole role) {
        this.role = role;
    }
}
