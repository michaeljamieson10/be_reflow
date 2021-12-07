package com.neighbor.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.enums.TransactionInvitationStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionInvitation {
    private int id;
    private Agent agent;
    private Client client;
    private TransactionInvitationStatusType transactionInvitationStatusType;
    private Timestamp acceptedTimestamp;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;

}