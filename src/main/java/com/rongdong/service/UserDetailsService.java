package com.rongdong.service;

import com.rongdong.model.priDataSource.Property;
import com.rongdong.model.priDataSource.UserDetails;


public interface UserDetailsService {

    UserDetails findUserDetailsByUserInfoId(String userInfoId, Integer dataSourceType) throws Exception;

    Property findPropertyByUserInfoId(String userInfoId, Integer dataSourceType) throws Exception;


}
