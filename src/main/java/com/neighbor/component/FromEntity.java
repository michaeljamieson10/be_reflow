package com.neighbor.component;

import com.neighbor.exception.AgentNotFoundException;
import com.neighbor.exception.ClientNotFoundException;
import com.neighbor.model.Agent;
import com.neighbor.model.Client;
import com.neighbor.model.TransactionInvitation;
import com.neighbor.model.User;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
import com.neighbor.persistence.entity.TransactionInvitationEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.repository.AgentRepository;
import com.neighbor.persistence.repository.ClientRepository;
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
        Client client = null;
        if(Objects.isNull(transactionInvitationEntity.getClientEntity())) Client.builder().id(transactionInvitationEntity.getClientEntity().getId()).build()
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
}
