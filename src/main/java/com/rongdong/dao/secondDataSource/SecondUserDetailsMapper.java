package com.rongdong.dao.secondDataSource;

import com.rongdong.model.priDataSource.UserDetails;
import com.rongdong.model.secondDataSource.SecondUserDetails;

import java.util.List;

public interface SecondUserDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SecondUserDetails record);

    int insertSelective(SecondUserDetails record);

    SecondUserDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SecondUserDetails record);

    int updateByPrimaryKey(SecondUserDetails record);

    List<UserDetails> findUserDetailsList(UserDetails userDetails) ;
}