package com.rongdong.dao.threeDataSource;

import com.rongdong.model.threeDataSource.ThreePlatform;

public interface ThreePlatformMapper {
    int deleteByPrimaryKey(String id);

    int insert(ThreePlatform record);

    int insertSelective(ThreePlatform record);

    ThreePlatform selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ThreePlatform record);

    int updateByPrimaryKey(ThreePlatform record);
}