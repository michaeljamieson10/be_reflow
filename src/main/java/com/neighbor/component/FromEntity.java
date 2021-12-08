package com.neighbor.component;

import com.neighbor.model.Agent;
import com.neighbor.model.Client;
import com.neighbor.model.transaction.*;
import com.neighbor.model.User;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
import com.neighbor.persistence.entity.transaction.*;
import com.neighbor.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FromEntity {

    @Autowired
    public FromEntity(
    ) {
    }

    public Agent fromAgentEntity(AgentEntity agentEntity) {
        return Agent.builder()
                .id(agentEntity.getId())
                .isActive(agentEntity.isActive())
                .user(User.builder().id(agentEntity.getUserEntity().getId()).build())
                .build();
    }
    public Client fromClientEntity(ClientEntity clientEntity) {
        return Client.builder()
                .id(clientEntity.getId())
                .isActive(clientEntity.isActive())
                .user(User.builder().id(clientEntity.getUserEntity().getId()).build())
                .build();
    }
    public User fromUserEntity(UserEntity userEntity) {
        return User.builder().id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .systemAdministrator(userEntity.isSystemAdministrator())
                .build();
    }
    public TransactionInvitation fromTransactionInvitationEntity(TransactionInvitationEntity transactionInvitationEntity) {
        Client client = Client.builder().build();
        if(Objects.isNull(transactionInvitationEntity.getClientEntity()))client = Client.builder().id(transactionInvitationEntity.getClientEntity().getId()).build();
        return TransactionInvitation.builder()
                .id(transactionInvitationEntity.getId())
                .client(client)
                .agent(Agent.builder().id(transactionInvitationEntity.getAgentEntity().getId()).build())
                .transactionInvitationStatusType(transactionInvitationEntity.getTransactionInvitationStatusType())
                .acceptedTimestamp(transactionInvitationEntity.getAcceptedTimestamp())
                .createdTimetamp(transactionInvitationEntity.getCreatedTimestamp())
                .updatedTimestamp(transactionInvitationEntity.getUpdatedTimestamp())
                .build();
    }
    public Transaction fromTransactionEntity(TransactionEntity transactionEntity) {
        Agent agent = Agent.builder().build();
        Client client = Client.builder().build();
        if(Objects.isNull(transactionEntity.getAgentEntity()))agent = Agent.builder().id(transactionEntity.getAgentEntity().getId()).build();
        if(Objects.isNull(transactionEntity.getClientEntity()))client = Client.builder().id(transactionEntity.getClientEntity().getId()).build();
        return Transaction.builder()
                .id(transactionEntity.getId())
                .client(client)
                .agent(agent)
                .createdTimetamp(transactionEntity.getCreatedTimestamp())
                .updatedTimestamp(transactionEntity.getUpdatedTimestamp())
                .build();
    }
    public HomeCriteria fromHomeCriteriaEntity(HomeCriteriaEntity homeCriteriaEntity){
        return HomeCriteria.builder()
                .id(homeCriteriaEntity.getId())
                .transaction(fromTransactionEntity(homeCriteriaEntity.getTransactionEntity()))
                .createdTimetamp(homeCriteriaEntity.getCreatedTimestamp())
                .updatedTimestamp(homeCriteriaEntity.getUpdatedTimestamp())
                .build();

    }
    public PreApproval fromPreApprovalCriteriaEntity(PreApprovalEntity preApprovalEntity){
        return PreApproval.builder()
                .id(preApprovalEntity.getId())
                .transaction(fromTransactionEntity(preApprovalEntity.getTransactionEntity()))
                .createdTimetamp(preApprovalEntity.getCreatedTimestamp())
                .updatedTimestamp(preApprovalEntity.getUpdatedTimestamp())
                .build();
    }
    public AcceptedOffer fromAcceptedOfferEntity(AcceptedOfferEntity acceptedOfferEntity){
        return AcceptedOffer.builder()
                .id(acceptedOfferEntity.getId())
                .transaction(fromTransactionEntity(acceptedOfferEntity.getTransactionEntity()))
                .createdTimetamp(acceptedOfferEntity.getCreatedTimestamp())
                .updatedTimestamp(acceptedOfferEntity.getUpdatedTimestamp())
                .build();
    }
    public HomeInspection fromHomeInspectionEntity(HomeInspectionEntity homeInspectionEntity){
        return HomeInspection.builder()
                .id(homeInspectionEntity.getId())
                .transaction(fromTransactionEntity(homeInspectionEntity.getTransactionEntity()))
                .createdTimetamp(homeInspectionEntity.getCreatedTimestamp())
                .updatedTimestamp(homeInspectionEntity.getUpdatedTimestamp())
                .build();
    }
}
