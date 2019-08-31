package com.rongdong.service.impl;

import com.rongdong.constant.DataSourceTypeEnum;
import com.rongdong.dao.fiveDataSource.FivePropertyMapper;
import com.rongdong.dao.fiveDataSource.FiveUserDetailsMapper;
import com.rongdong.dao.fourDataSource.FourPropertyMapper;
import com.rongdong.dao.fourDataSource.FourUserDetailsMapper;
import com.rongdong.dao.priDataSource.PropertyMapper;
import com.rongdong.dao.priDataSource.UserDetailsMapper;
import com.rongdong.dao.secondDataSource.SecondPropertyMapper;
import com.rongdong.dao.secondDataSource.SecondUserDetailsMapper;
import com.rongdong.dao.threeDataSource.ThreePropertyMapper;
import com.rongdong.dao.threeDataSource.ThreeUserDetailsMapper;
import com.rongdong.model.priDataSource.Property;
import com.rongdong.model.priDataSource.UserDetails;
import com.rongdong.service.UserDetailsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hsh
 * @create 2018-04-02 0:57
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsMapper userDetailsMapper;
 /*   @Autowired
    private SecondUserDetailsMapper secondUserDetailsMapper;
    @Autowired
    private ThreeUserDetailsMapper threeUserDetailsMapper;
    @Autowired
    private FourUserDetailsMapper fourUserDetailsMapper;
    @Autowired
    private FiveUserDetailsMapper fiveUserDetailsMapper;*/
    @Autowired
    private PropertyMapper propertyMapper;
/*    @Autowired
    private SecondPropertyMapper secondPropertyMapper;
    @Autowired
    private ThreePropertyMapper threePropertyMapper;
    @Autowired
    private FourPropertyMapper fourPropertyMapper;
    @Autowired
    private FivePropertyMapper fivePropertyMapper;*/

    @Override
    public UserDetails findUserDetailsByUserInfoId(String userInfoId, Integer dataSourceType) throws Exception {
        UserDetails query = new UserDetails();
        query.setUserId(userInfoId);
        List<UserDetails> userDetailsList = new ArrayList<>();
        if (dataSourceType == DataSourceTypeEnum.one.getIndex()) {
            userDetailsList = userDetailsMapper.findUserDetailsList(query);
        } /*else if (dataSourceType == DataSourceTypeEnum.two.getIndex()) {
            userDetailsList = secondUserDetailsMapper.findUserDetailsList(query);
        } else if (dataSourceType == DataSourceTypeEnum.three.getIndex()) {
            userDetailsList = threeUserDetailsMapper.findUserDetailsList(query);
        } else if (dataSourceType == DataSourceTypeEnum.four.getIndex()) {
            userDetailsList = fourUserDetailsMapper.findUserDetailsList(query);
        } else if (dataSourceType == DataSourceTypeEnum.five.getIndex()) {
            userDetailsList = fiveUserDetailsMapper.findUserDetailsList(query);
        }*/
        if (CollectionUtils.isNotEmpty(userDetailsList)) {
            return userDetailsList.get(0);
        }
        return null;
    }

    @Override
    public Property findPropertyByUserInfoId(String userInfoId, Integer dataSourceType) throws Exception {
        Assert.hasText(userInfoId, "用户ID不能为空");
        Property query = new Property();
        query.setUserId(userInfoId);
        List<Property> propertyList = new ArrayList<>();
        if (dataSourceType == DataSourceTypeEnum.one.getIndex()) {
            propertyList = propertyMapper.findPropertyList(query);
        } /*else if (dataSourceType == DataSourceTypeEnum.two.getIndex()) {
            propertyList = secondPropertyMapper.findPropertyList(query);
        } else if (dataSourceType == DataSourceTypeEnum.three.getIndex()) {
            propertyList = threePropertyMapper.findPropertyList(query);
        } else if (dataSourceType == DataSourceTypeEnum.four.getIndex()) {
            propertyList = fourPropertyMapper.findPropertyList(query);
        } else if (dataSourceType == DataSourceTypeEnum.five.getIndex()) {
            propertyList = fivePropertyMapper.findPropertyList(query);
        }*/
        if (CollectionUtils.isNotEmpty(propertyList)) {
            return propertyList.get(0);
        }
        return null;
    }
}
