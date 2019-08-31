package com.rongdong.service;


import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.model.priDataSource.UserInfo;
import com.rongdong.vo.UserInfoVo;

import java.util.List;


public interface UserService {

    UserInfo getUserById(String userId, Integer dataSourceType) throws Exception;

    PageInfo<UserInfo> findUserInfoWithPageForOneDataSource(UserInfoVo userInfo, PageInfo<UserInfo> pageInfo) throws Exception;

    List<UserInfo> findUserInfoList(UserInfoVo userInfo) throws Exception;

}
