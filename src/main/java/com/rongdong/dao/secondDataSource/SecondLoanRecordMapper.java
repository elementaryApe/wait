package com.rongdong.dao.secondDataSource;

import com.rongdong.model.priDataSource.LoanRecord;
import com.rongdong.model.secondDataSource.SecondLoanRecord;

import java.util.List;

public interface SecondLoanRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(SecondLoanRecord record);

    int insertSelective(SecondLoanRecord record);

    SecondLoanRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SecondLoanRecord record);

    int updateByPrimaryKey(SecondLoanRecord record);

    List<LoanRecord> findLoanRecords(LoanRecord loanRecord);

}