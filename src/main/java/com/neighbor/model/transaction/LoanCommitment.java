package com.neighbor.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.enums.LoanCommitmentType;
import com.neighbor.enums.TransactionStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanCommitment {
    private int id;
    private Transaction transaction;
    private TransactionStatusType transactionStatusType;
    private LoanCommitmentType loanCommitmentType;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;
}
