package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.ChannelRecord;
import com.rongdong.vo.ChannelRecordVo;

import java.util.List;

public interface ChannelRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChannelRecord record);

    int insertSelective(ChannelRecord record);

    ChannelRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChannelRecord record);

    int updateByPrimaryKey(ChannelRecord record);

    List<ChannelRecord> getChannelRecord(ChannelRecordVo channelRecord);
}