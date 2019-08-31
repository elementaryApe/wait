package com.rongdong.dao.threeDataSource;

import com.rongdong.model.priDataSource.LoanRecord;
import com.rongdong.model.threeDataSource.ThreeLoanRecord;

import java.util.List;

public interface ThreeLoanRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(ThreeLoanRecord record);

    int insertSelective(ThreeLoanRecord record);

    ThreeLoanRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ThreeLoanRecord record);

    int updateByPrimaryKey(ThreeLoanRecord record);

    List<LoanRecord> findLoanRecords(LoanRecord loanRecord);

}