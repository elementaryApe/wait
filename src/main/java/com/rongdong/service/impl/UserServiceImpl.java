package com.rongdong.service.impl;


import com.github.pagehelper.PageHelper;
import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.constant.DataSourceTypeEnum;
import com.rongdong.dao.fiveDataSource.FiveUserInfoMapper;
import com.rongdong.dao.fourDataSource.FourUserInfoMapper;
import com.rongdong.dao.priDataSource.UserInfoMapper;
import com.rongdong.dao.secondDataSource.SecondUserInfoMapper;
import com.rongdong.dao.threeDataSource.ThreeUserInfoMapper;
import com.rongdong.model.fiveDataSource.FiveUserInfo;
import com.rongdong.model.fourDataSource.FourUserInfo;
import com.rongdong.model.priDataSource.UserInfo;
import com.rongdong.model.secondDataSource.SecondUserInfo;
import com.rongdong.model.threeDataSource.ThreeUserInfo;
import com.rongdong.service.UserService;
import com.rongdong.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户service实现
 *
 * @author hsh
 * @create 2018-03-21 17:44
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SecondUserInfoMapper secondUserInfoMapper;

    @Autowired
    private ThreeUserInfoMapper threeUserInfoMapper;

    @Autowired
    private FourUserInfoMapper fourUserInfoMapper;

    @Autowired
    private FiveUserInfoMapper fiveUserInfoMapper;

    @Override
    public UserInfo getUserById(String userId, Integer dataSourceType) throws Exception {
        if (dataSourceType == DataSourceTypeEnum.one.getIndex()) {
            return userInfoMapper.selectByPrimaryKey(userId);
        } else if (dataSourceType == DataSourceTypeEnum.two.getIndex()) {
            UserInfo userInfoTar = new UserInfo();
            SecondUserInfo secondUserInfo = secondUserInfoMapper.selectByPrimaryKey(userId);
            BeanUtils.copyProperties(secondUserInfo, userInfoTar);
            return userInfoTar;
        } else if (dataSourceType == DataSourceTypeEnum.three.getIndex()) {
            UserInfo userInfoTar = new UserInfo();
            ThreeUserInfo threeUserInfo = threeUserInfoMapper.selectByPrimaryKey(userId);
            BeanUtils.copyProperties(threeUserInfo, userInfoTar);
            return userInfoTar;
        } else if (dataSourceType == DataSourceTypeEnum.four.getIndex()) {
            UserInfo userInfoTar = new UserInfo();
            FourUserInfo fourUserInfo = fourUserInfoMapper.selectByPrimaryKey(userId);
            BeanUtils.copyProperties(fourUserInfo, userInfoTar);
            return userInfoTar;
        } else if (dataSourceType == DataSourceTypeEnum.five.getIndex()) {
            UserInfo userInfoTar = new UserInfo();
            FiveUserInfo fiveUserInfo = fiveUserInfoMapper.selectByPrimaryKey(userId);
            BeanUtils.copyProperties(fiveUserInfo, userInfoTar);
            return userInfoTar;
        }
        return null;
    }

    @Override
    public PageInfo<UserInfo> findUserInfoWithPageForOneDataSource(UserInfoVo userInfo, PageInfo<UserInfo> pageInfo) throws Exception {
        Integer dataSourceType = userInfo.getDateSourceType();
        if (dataSourceType == DataSourceTypeEnum.one.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(userInfoMapper.getUserInfoList(userInfo));
        } else if (dataSourceType == DataSourceTypeEnum.two.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(secondUserInfoMapper.getUserInfoList(userInfo));
        } else if (dataSourceType == DataSourceTypeEnum.three.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(threeUserInfoMapper.getUserInfoList(userInfo));
        } else if (dataSourceType == DataSourceTypeEnum.four.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(fourUserInfoMapper.getUserInfoList(userInfo));
        } else if (dataSourceType == DataSourceTypeEnum.five.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(fiveUserInfoMapper.getUserInfoList(userInfo));
        }
        return null;
    }

    @Override
    public List<UserInfo> findUserInfoList(UserInfoVo userInfo) throws Exception {
        Integer dataSourceType = userInfo.getDateSourceType();
        if (dataSourceType == DataSourceTypeEnum.one.getIndex()) {
            return userInfoMapper.getUserInfoList(userInfo);
        } else if (dataSourceType == DataSourceTypeEnum.two.getIndex()) {
            return secondUserInfoMapper.getUserInfoList(userInfo);
        } else if (dataSourceType == DataSourceTypeEnum.three.getIndex()) {
            return threeUserInfoMapper.getUserInfoList(userInfo);
        } else if (dataSourceType == DataSourceTypeEnum.four.getIndex()) {
            return fourUserInfoMapper.getUserInfoList(userInfo);
        } else if (dataSourceType == DataSourceTypeEnum.five.getIndex()) {
            return fiveUserInfoMapper.getUserInfoList(userInfo);
        }
        return null;
    }
}
