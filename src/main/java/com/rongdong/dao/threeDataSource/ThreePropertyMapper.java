package com.rongdong.dao.threeDataSource;

import com.rongdong.model.priDataSource.Property;
import com.rongdong.model.threeDataSource.ThreeProperty;

import java.util.List;

public interface ThreePropertyMapper {
    int deleteByPrimaryKey(String id);

    int insert(ThreeProperty record);

    int insertSelective(ThreeProperty record);

    ThreeProperty selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ThreeProperty record);

    int updateByPrimaryKey(ThreeProperty record);

    List<Property> findPropertyList(Property query);


}