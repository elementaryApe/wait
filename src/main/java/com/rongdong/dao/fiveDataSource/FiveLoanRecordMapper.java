package com.rongdong.dao.fiveDataSource;

import com.rongdong.model.fiveDataSource.FiveLoanRecord;
import com.rongdong.model.priDataSource.LoanRecord;

import java.util.List;

public interface FiveLoanRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FiveLoanRecord record);

    int insertSelective(FiveLoanRecord record);

    FiveLoanRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FiveLoanRecord record);

    int updateByPrimaryKey(FiveLoanRecord record);

    List<LoanRecord> findLoanRecords(LoanRecord loanRecord);

}