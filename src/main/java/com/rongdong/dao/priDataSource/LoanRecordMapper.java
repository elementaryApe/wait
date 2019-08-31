package com.rongdong.dao.priDataSource;

import com.rongdong.model.priDataSource.LoanRecord;

import java.util.List;

public interface LoanRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(LoanRecord record);

    int insertSelective(LoanRecord record);

    LoanRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LoanRecord record);

    int updateByPrimaryKey(LoanRecord record);

    List<LoanRecord> findLoanRecords(LoanRecord loanRecord);

}