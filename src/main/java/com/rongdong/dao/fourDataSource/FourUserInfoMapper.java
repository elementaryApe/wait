package com.rongdong.dao.fourDataSource;

import com.rongdong.model.fourDataSource.FourUserInfo;
import com.rongdong.model.priDataSource.UserInfo;
import com.rongdong.vo.UserInfoVo;

import java.util.List;

public interface FourUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FourUserInfo record);

    int insertSelective(FourUserInfo record);

    FourUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FourUserInfo record);

    int updateByPrimaryKey(FourUserInfo record);

    List<UserInfo> getUserInfoList(UserInfoVo userInfo);

}