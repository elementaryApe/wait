package com.rongdong.service.impl;

import com.github.pagehelper.PageHelper;
import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.dao.priDataSource.ChannelInfoMapper;
import com.rongdong.dao.priDataSource.ChannelRecordMapper;
import com.rongdong.model.priDataSource.ChannelInfo;
import com.rongdong.model.priDataSource.ChannelRecord;
import com.rongdong.service.ChannelService;
import com.rongdong.vo.ChannelRecordVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 渠道商服务实现
 *
 * @author hsh
 * @create 2018-04-01 0:40
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelInfoMapper channelInfoMapper;
    @Autowired
    private ChannelRecordMapper channelRecordMapper;

    @Override
    public Boolean checkChannelExits(ChannelInfo query) throws Exception {
        Assert.notNull(query, "查询条件不能为空");
        List<ChannelInfo> channelList = channelInfoMapper.getChannelList(query);
        return CollectionUtils.isNotEmpty(channelList);
    }

    @Override
    public ChannelInfo getChannelByName(String channelName) throws Exception {
        Assert.hasText(channelName, "渠道名称不能为空");
        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.setChannelName(channelName);
        List<ChannelInfo> channelList = channelInfoMapper.getChannelList(channelInfo);
        if (CollectionUtils.isEmpty(channelList))
            throw new Exception("渠道数据不存在");
        return channelList.get(0);
    }

    @Override
    public ChannelInfo getChannelInfo(ChannelInfo query) throws Exception {
        Assert.notNull(query, "查询条件不能为空");
        List<ChannelInfo> channelList = channelInfoMapper.getChannelList(query);
        if (CollectionUtils.isEmpty(channelList))
            throw new Exception("渠道数据不存在");
        return channelList.get(0);
    }

    @Override
    public Boolean addChannel(ChannelInfo channelInfo) throws Exception {
        Assert.notNull(channelInfo, "渠道商对象不能为空");
        Assert.hasText(channelInfo.getId(), "渠道商ID不能为空");
        Assert.hasText(channelInfo.getChannelCode(), "渠道商Code不能为空");
        return channelInfoMapper.insertSelective(channelInfo) > 0;
    }

    @Override
    public boolean updateChannelInfo(ChannelInfo channelInfo) throws Exception {
        Assert.notNull(channelInfo, "渠道商对象不能为空");
        Assert.hasText(channelInfo.getId(), "渠道商ID不能为空");
        ChannelInfo oldChannelInfo = channelInfoMapper.selectByPrimaryKey(channelInfo.getId());
        if (oldChannelInfo == null)
            throw new Exception("渠道商数据不存在");
        if (StringUtils.isNotEmpty(channelInfo.getChannelName()) && !StringUtils.equals(oldChannelInfo.getChannelName(), channelInfo.getChannelName())) {
            ChannelInfo queryByName = new ChannelInfo();
            queryByName.setChannelName(channelInfo.getChannelName());
            List<ChannelInfo> channelNameList = channelInfoMapper.getChannelList(queryByName);
            if (CollectionUtils.isNotEmpty(channelNameList) && (channelNameList.size() > 1 || !StringUtils.equals(channelInfo.getId(), channelNameList.get(0).getId()))) {
                throw new Exception("渠道商名称已存在，请重新填写");
            }
        }
        if (StringUtils.isNotEmpty(channelInfo.getChannelCode()) && !StringUtils.equals(oldChannelInfo.getChannelCode(), channelInfo.getChannelCode())) {
            ChannelInfo queryByCode = new ChannelInfo();
            queryByCode.setChannelCode(channelInfo.getChannelCode());
            List<ChannelInfo> channelCodeList = channelInfoMapper.getChannelList(queryByCode);
            if (CollectionUtils.isNotEmpty(channelCodeList) && (channelCodeList.size() > 1 || !StringUtils.equals(channelInfo.getId(), channelCodeList.get(0).getId()))) {
                throw new Exception("渠道商Code已存在，请重新填写");
            }
        }
        return channelInfoMapper.updateByPrimaryKeySelective(channelInfo) > 0;
    }


    @Override
    public boolean insertChannelRecord(ChannelRecord channelRecord) throws Exception {
        Assert.notNull(channelRecord, "渠道商对象不能为空");
        Assert.hasText(channelRecord.getId(), "充值记录ID不能为空");
        Assert.hasText(channelRecord.getChannelId(), "渠道商ID不能为空");
        return channelRecordMapper.insertSelective(channelRecord) > 0;
    }

    @Override
    public PageInfo<ChannelInfo> findChannelInfoWithPage(ChannelInfo channelInfo, PageInfo<ChannelInfo> pageInfo) throws Exception {
        PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
        return pageInfo.parsePages(channelInfoMapper.getChannelList(channelInfo));
    }

    @Override
    public PageInfo<ChannelRecord> findChannelRecordInfoWithPage(ChannelRecordVo channelRecordVo, PageInfo<ChannelRecord> pageInfo) throws Exception {
        PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
        return pageInfo.parsePages(channelRecordMapper.getChannelRecord(channelRecordVo));
    }
}
