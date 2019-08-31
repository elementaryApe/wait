package com.rongdong.dao.fiveDataSource;

import com.rongdong.model.fiveDataSource.FiveUserInfo;
import com.rongdong.model.priDataSource.UserInfo;
import com.rongdong.vo.UserInfoVo;

import java.util.List;

public interface FiveUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FiveUserInfo record);

    int insertSelective(FiveUserInfo record);

    FiveUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FiveUserInfo record);

    int updateByPrimaryKey(FiveUserInfo record);

    List<UserInfo> getUserInfoList(UserInfoVo userInfo);

}