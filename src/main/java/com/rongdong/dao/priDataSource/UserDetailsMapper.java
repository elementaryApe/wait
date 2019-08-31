package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.UserDetails;

import java.util.List;

public interface UserDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDetails record);

    int insertSelective(UserDetails record);

    UserDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDetails record);

    int updateByPrimaryKey(UserDetails record);

    List<UserDetails> findUserDetailsList(UserDetails userDetails) ;

}