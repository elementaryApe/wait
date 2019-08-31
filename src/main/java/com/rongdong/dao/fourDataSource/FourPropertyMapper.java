package com.rongdong.dao.fourDataSource;

import com.rongdong.model.fourDataSource.FourProperty;
import com.rongdong.model.priDataSource.Property;

import java.util.List;

public interface FourPropertyMapper {
    int deleteByPrimaryKey(String id);

    int insert(FourProperty record);

    int insertSelective(FourProperty record);

    FourProperty selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FourProperty record);

    int updateByPrimaryKey(FourProperty record);

    List<Property> findPropertyList(Property query);

}