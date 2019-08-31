package com.rongdong.dao.fourDataSource;

import com.rongdong.model.fourDataSource.FourLoanRecord;
import com.rongdong.model.priDataSource.LoanRecord;

import java.util.List;

public interface FourLoanRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FourLoanRecord record);

    int insertSelective(FourLoanRecord record);

    FourLoanRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FourLoanRecord record);

    int updateByPrimaryKey(FourLoanRecord record);

    List<LoanRecord> findLoanRecords(LoanRecord loanRecord);

}