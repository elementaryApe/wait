package com.rongdong.dao.fourDataSource;

import com.rongdong.model.fourDataSource.FourUserDetails;
import com.rongdong.model.priDataSource.UserDetails;

import java.util.List;

public interface FourUserDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(FourUserDetails record);

    int insertSelective(FourUserDetails record);

    FourUserDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FourUserDetails record);

    int updateByPrimaryKey(FourUserDetails record);

    List<UserDetails> findUserDetailsList(UserDetails userDetails) ;

}