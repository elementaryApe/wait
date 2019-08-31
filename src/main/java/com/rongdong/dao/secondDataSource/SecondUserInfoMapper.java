package com.rongdong.dao.secondDataSource;

import com.rongdong.model.priDataSource.UserInfo;
import com.rongdong.model.secondDataSource.SecondUserInfo;
import com.rongdong.vo.UserInfoVo;

import java.util.List;

public interface SecondUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SecondUserInfo record);

    int insertSelective(SecondUserInfo record);

    SecondUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SecondUserInfo record);

    int updateByPrimaryKey(SecondUserInfo record);

    List<UserInfo> getUserInfoList(UserInfoVo userInfo);
}