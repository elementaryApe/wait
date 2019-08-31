package com.rongdong.dao.threeDataSource;

import com.rongdong.model.priDataSource.UserDetails;
import com.rongdong.model.threeDataSource.ThreeUserDetails;

import java.util.List;

public interface ThreeUserDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(ThreeUserDetails record);

    int insertSelective(ThreeUserDetails record);

    ThreeUserDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ThreeUserDetails record);

    int updateByPrimaryKey(ThreeUserDetails record);

    List<UserDetails> findUserDetailsList(UserDetails userDetails) ;

}