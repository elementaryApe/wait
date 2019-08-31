package com.rongdong.service;

import com.rongdong.model.priDataSource.Platform;

/**
 * Created by hsh on 2018/4/2.
 */
public interface PlatformService {

    Platform selectByPrimaryKey(String platformId,Integer dataSourceType) throws Exception;

}
