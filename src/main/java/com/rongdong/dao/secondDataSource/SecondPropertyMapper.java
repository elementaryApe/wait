package com.rongdong.dao.secondDataSource;

import com.rongdong.model.priDataSource.Property;
import com.rongdong.model.secondDataSource.SecondProperty;

import java.util.List;

public interface SecondPropertyMapper {
    int deleteByPrimaryKey(String id);

    int insert(SecondProperty record);

    int insertSelective(SecondProperty record);

    SecondProperty selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SecondProperty record);

    int updateByPrimaryKey(SecondProperty record);

    List<Property> findPropertyList(Property query);

}