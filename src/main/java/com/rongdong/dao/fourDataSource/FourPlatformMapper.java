package com.rongdong.dao.fourDataSource;

import com.rongdong.model.fourDataSource.FourPlatform;

public interface FourPlatformMapper {
    int deleteByPrimaryKey(String id);

    int insert(FourPlatform record);

    int insertSelective(FourPlatform record);

    FourPlatform selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FourPlatform record);

    int updateByPrimaryKey(FourPlatform record);
}