package com.rongdong.service.impl;

import com.github.pagehelper.PageHelper;
import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.constant.DataSourceTypeEnum;
import com.rongdong.dao.fiveDataSource.FiveLoanRecordMapper;
import com.rongdong.dao.fourDataSource.FourLoanRecordMapper;
import com.rongdong.dao.priDataSource.LoanRecordMapper;
import com.rongdong.dao.secondDataSource.SecondLoanRecordMapper;
import com.rongdong.dao.threeDataSource.ThreeLoanRecordMapper;
import com.rongdong.model.priDataSource.LoanRecord;
import com.rongdong.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 贷款记录
 *
 * @author hsh
 * @create 2018-04-02 1:55
 **/
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRecordMapper loanRecordMapper;
//    @Autowired
//    private SecondLoanRecordMapper secondLoanRecordMapper;
//    @Autowired
//    private ThreeLoanRecordMapper threeLoanRecordMapper;
//    @Autowired
//    private FourLoanRecordMapper fourLoanRecordMapper;
//    @Autowired
//    private FiveLoanRecordMapper fiveLoanRecordMapper;
    @Override
    public PageInfo<LoanRecord> findLoanRecordPages(LoanRecord query, Integer dataSourceType, PageInfo<LoanRecord> pageInfo) throws Exception {
        if (dataSourceType == DataSourceTypeEnum.one.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(loanRecordMapper.findLoanRecords(query));
        }  /*else if (dataSourceType == DataSourceTypeEnum.two.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(secondLoanRecordMapper.findLoanRecords(query));
        }else if (dataSourceType == DataSourceTypeEnum.three.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(threeLoanRecordMapper.findLoanRecords(query));
        }else if (dataSourceType == DataSourceTypeEnum.four.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(fourLoanRecordMapper.findLoanRecords(query));
        }else if (dataSourceType == DataSourceTypeEnum.five.getIndex()) {
            PageHelper.startPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return pageInfo.parsePages(fiveLoanRecordMapper.findLoanRecords(query));
        }*/
        return null;
    }
}
