package com.rongdong.service;

import com.rongdong.common.jdbc.PageInfo;
import com.rongdong.model.priDataSource.LoanRecord;

/**
 * Created by hsh on 2018/4/2.
 */
public interface LoanService {

    PageInfo<LoanRecord> findLoanRecordPages(LoanRecord query,Integer dataSourceType, PageInfo<LoanRecord> pageInfo) throws Exception;

}
