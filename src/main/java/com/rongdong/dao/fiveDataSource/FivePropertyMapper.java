package com.rongdong.dao.fiveDataSource;

import com.rongdong.model.fiveDataSource.FiveProperty;
import com.rongdong.model.priDataSource.Property;

import java.util.List;

public interface FivePropertyMapper {
    int deleteByPrimaryKey(String id);

    int insert(FiveProperty record);

    int insertSelective(FiveProperty record);

    FiveProperty selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FiveProperty record);

    int updateByPrimaryKey(FiveProperty record);

    List<Property> findPropertyList(Property query);

}