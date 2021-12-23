package com.neighbor.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class AcceptedOffer {
    private int id;
    private Transaction transaction;
    private TransactionStatusType transactionStatusType;
    private String address;
    private BigDecimal purchasePrice;
    private BigDecimal propertyTaxes;
    private BigDecimal downPayment;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;
}
