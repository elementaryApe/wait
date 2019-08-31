package com.rongdong.service.impl;

import com.rongdong.dao.priDataSource.ParameterMapper;
import com.rongdong.model.priDataSource.Parameter;
import com.rongdong.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author hsh
 * @create 2018-04-02 4:00
 **/
@Service
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParameterMapper parameterMapper;

    @Override
    public Parameter selectByParamCode(String paramCode) throws Exception {
        Assert.hasText(paramCode, "参数CODE不能为空");
        return parameterMapper.selectByParamCode(paramCode);
    }
}
