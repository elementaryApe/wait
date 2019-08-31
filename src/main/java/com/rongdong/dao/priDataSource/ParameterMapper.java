package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.Parameter;

public interface ParameterMapper {
    int deleteByPrimaryKey(String id);

    int insert(Parameter record);

    int insertSelective(Parameter record);

    Parameter selectByPrimaryKey(String id);

    Parameter selectByParamCode(String paramCode);


    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);
}