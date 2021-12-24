package com.neighbor.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.enums.FinalWalkthroughStatusType;
import com.neighbor.enums.TransactionStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinalWalkthrough {
    private int id;
    private Transaction transaction;
    private TransactionStatusType transactionStatusType;
    private FinalWalkthroughStatusType finalWalkthroughStatusType;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;
}
