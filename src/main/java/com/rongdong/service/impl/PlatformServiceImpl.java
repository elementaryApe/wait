package com.rongdong.service.impl;

import com.rongdong.constant.DataSourceTypeEnum;
import com.rongdong.dao.fiveDataSource.FivePlatformMapper;
import com.rongdong.dao.fourDataSource.FourPlatformMapper;
import com.rongdong.dao.priDataSource.PlatformMapper;
import com.rongdong.dao.secondDataSource.SecondPlatformMapper;
import com.rongdong.dao.threeDataSource.ThreePlatformMapper;
import com.rongdong.model.fiveDataSource.FivePlatform;
import com.rongdong.model.fourDataSource.FourPlatform;
import com.rongdong.model.priDataSource.Platform;
import com.rongdong.model.secondDataSource.SecondPlatform;
import com.rongdong.model.threeDataSource.ThreePlatform;
import com.rongdong.service.PlatformService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author hsh
 * @create 2018-04-02 2:18
 **/
@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private PlatformMapper platformMapper;
 /*   @Autowired
    private SecondPlatformMapper secondPlatformMapper;
    @Autowired
    private ThreePlatformMapper threePlatformMapper;
    @Autowired
    private FourPlatformMapper fourPlatformMapper;
    @Autowired
    private FivePlatformMapper fivePlatformMapper;*/

    @Override
    public Platform selectByPrimaryKey(String platformId, Integer dataSourceType) throws Exception {
        Assert.hasText(platformId, "平台ID不能为空");
        Platform platform = new Platform();
        if (dataSourceType == DataSourceTypeEnum.one.getIndex()) {
            platform = platformMapper.selectByPrimaryKey(platformId);
        } /*else if (dataSourceType == DataSourceTypeEnum.two.getIndex()) {
            SecondPlatform secondPlatform = secondPlatformMapper.selectByPrimaryKey(platformId);
            BeanUtils.copyProperties(secondPlatform, platform);
        } else if (dataSourceType == DataSourceTypeEnum.three.getIndex()) {
            ThreePlatform threePlatform = threePlatformMapper.selectByPrimaryKey(platformId);
            BeanUtils.copyProperties(threePlatform, platform);
        } else if (dataSourceType == DataSourceTypeEnum.four.getIndex()) {
            FourPlatform fourPlatform = fourPlatformMapper.selectByPrimaryKey(platformId);
            BeanUtils.copyProperties(fourPlatform, platform);
        } else if (dataSourceType == DataSourceTypeEnum.five.getIndex()) {
            FivePlatform fivePlatform = fivePlatformMapper.selectByPrimaryKey(platformId);
            BeanUtils.copyProperties(fivePlatform, platform);
        }*/
        return platform;
    }
}
