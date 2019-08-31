package com.rongdong.dao.threeDataSource;

import com.rongdong.model.priDataSource.UserInfo;
import com.rongdong.model.threeDataSource.ThreeUserInfo;
import com.rongdong.vo.UserInfoVo;

import java.util.List;

public interface ThreeUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ThreeUserInfo record);

    int insertSelective(ThreeUserInfo record);

    ThreeUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ThreeUserInfo record);

    int updateByPrimaryKey(ThreeUserInfo record);

    List<UserInfo> getUserInfoList(UserInfoVo userInfo);

}