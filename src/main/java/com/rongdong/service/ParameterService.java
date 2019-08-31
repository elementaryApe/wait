package com.rongdong.service;

import com.rongdong.model.priDataSource.Parameter;

/**
 * Created by hsh on 2018/4/2.
 */
public interface ParameterService {

    Parameter selectByParamCode(String paramCode) throws Exception;

}
