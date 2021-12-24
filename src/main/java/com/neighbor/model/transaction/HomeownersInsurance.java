package com.neighbor.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.enums.HomeOwnersInsuranceStatusType;
import com.neighbor.enums.TransactionStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeownersInsurance {
    private int id;
    private Transaction transaction;
    private TransactionStatusType transactionStatusType;
    private HomeOwnersInsuranceStatusType homeOwnersInsuranceStatusType;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;
}
