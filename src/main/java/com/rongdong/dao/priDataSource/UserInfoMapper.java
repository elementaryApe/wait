package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.UserInfo;
import com.rongdong.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> getUserInfoList(UserInfoVo userInfo);

}