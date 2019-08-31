package com.rongdong.dao.fiveDataSource;

import com.rongdong.model.fiveDataSource.FiveUserDetails;
import com.rongdong.model.priDataSource.UserDetails;

import java.util.List;

public interface FiveUserDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(FiveUserDetails record);

    int insertSelective(FiveUserDetails record);

    FiveUserDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FiveUserDetails record);

    int updateByPrimaryKey(FiveUserDetails record);

    List<UserDetails> findUserDetailsList(UserDetails userDetails) ;

}