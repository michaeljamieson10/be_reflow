package com.neighbor.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private HomeCriteria homeCriteria;
    private PreApproval preApproval;
    private AcceptedOffer acceptedOffer;
    private HomeInspection homeInspection;
    private ContractsSigned contractsSigned;
    private Appraisal appraisal;
    private LoanCommitment loanCommitment;
    private HomeownersInsurance homeownersInsurance;
    private ClearToClose clearToClose;
    private FinalWalkthrough finalWalkthrough;
    private Closing closing;
    private int transactionsComplete;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;
}
