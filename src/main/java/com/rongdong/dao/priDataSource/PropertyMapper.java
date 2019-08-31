package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.Property;

import java.util.List;

public interface PropertyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Property record);

    int insertSelective(Property record);

    Property selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);

    List<Property> findPropertyList(Property query);

}