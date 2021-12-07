package com.neighbor.model.Transaction;

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
public class Transaction {
    private int id;
    private Agent agent;
    private Client client;
    private String firstName;
    private String lastName;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;
}
