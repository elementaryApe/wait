package com.rongdong.dao.fiveDataSource;

import com.rongdong.model.fiveDataSource.FivePlatform;

public interface FivePlatformMapper {
    int deleteByPrimaryKey(String id);

    int insert(FivePlatform record);

    int insertSelective(FivePlatform record);

    FivePlatform selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FivePlatform record);

    int updateByPrimaryKey(FivePlatform record);
}