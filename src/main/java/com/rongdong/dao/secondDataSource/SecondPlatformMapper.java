package com.rongdong.dao.secondDataSource;

import com.rongdong.model.secondDataSource.SecondPlatform;

public interface SecondPlatformMapper {
    int deleteByPrimaryKey(String id);

    int insert(SecondPlatform record);

    int insertSelective(SecondPlatform record);

    SecondPlatform selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SecondPlatform record);

    int updateByPrimaryKey(SecondPlatform record);
}