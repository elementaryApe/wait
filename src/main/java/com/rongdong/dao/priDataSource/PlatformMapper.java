package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.Platform;

public interface PlatformMapper {
    int deleteByPrimaryKey(String id);

    int insert(Platform record);

    int insertSelective(Platform record);

    Platform selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Platform record);

    int updateByPrimaryKey(Platform record);
}