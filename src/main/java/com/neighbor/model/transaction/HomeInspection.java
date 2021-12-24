package com.neighbor.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.enums.HomeInspectionStatusType;
import com.neighbor.enums.TransactionStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeInspection {
    private int id;
    private Transaction transaction;
    private TransactionStatusType transactionStatusType;
    private HomeInspectionStatusType homeInspectionStatusType;
    private Timestamp scheduledDateTime;
    private Long scheduledDateTimeMilli;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;
}
