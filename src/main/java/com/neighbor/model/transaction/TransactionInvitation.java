package com.neighbor.model.transaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.enums.TransactionInvitationStatusType;
import com.neighbor.model.Agent;
import com.neighbor.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionInvitation {
    private int id;
    private Agent agent;
    private Client client;
    private Transaction transaction;
    private TransactionInvitationStatusType transactionInvitationStatusType;
    private Timestamp acceptedTimestamp;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;

}