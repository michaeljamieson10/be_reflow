package com.neighbor.service;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionInvitationStatusType;
import com.neighbor.model.Agent;
import com.neighbor.model.Client;
import com.neighbor.model.TransactionInvitation;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
import com.neighbor.persistence.entity.TransactionInvitationEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.repository.TransactionInvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionInvitationServiceImpl implements TransactionInvitationService {
    private final TransactionInvitationRepository transactionInvitationRepository;
    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final GetEntity getEntity;

    @Autowired
    public TransactionInvitationServiceImpl(TransactionInvitationRepository transactionInvitationRepository,
                                            PermissionsValidator permissionsValidator,
                                            AuthenticatedUserResolver authenticatedUserResolver,
                                            GetEntity getEntity){
        this.transactionInvitationRepository = transactionInvitationRepository;
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
    }
    @Override
    public TransactionInvitation createNewTransactionInvitation(TransactionInvitation transactionInvitation) {
        permissionsValidator.validateAgentOrSystemAdmin(authenticatedUserResolver.user());
        ClientEntity clientEntity = getEntity.getClientEntity(transactionInvitation.getClient());
        AgentEntity agentEntity = getEntity.getAgentEntity(transactionInvitation.getAgent());
        TransactionInvitationEntity transactionInvitationEntity = new TransactionInvitationEntity();
        transactionInvitationEntity.setAgentEntity(agentEntity);
        transactionInvitationEntity.setClientEntity(clientEntity);
        transactionInvitationEntity.setTransactionInvitationStatusType(TransactionInvitationStatusType.pending);
        transactionInvitationRepository.save(transactionInvitationEntity);

        return fromEntity(transactionInvitationEntity);
    }

    private TransactionInvitation fromEntity(TransactionInvitationEntity transactionInvitationEntity) {
        return TransactionInvitation.builder()
                .id(transactionInvitationEntity.getId())
                .client(Client.builder().id(transactionInvitationEntity.getClientEntity().getId()).build())
                .agent(Agent.builder().id(transactionInvitationEntity.getAgentEntity().getId()).build())
                .transactionInvitationStatusType(transactionInvitationEntity.getTransactionInvitationStatusType())
                .acceptedTimestamp(transactionInvitationEntity.getAcceptedTimestamp())
                .createdTimetamp(transactionInvitationEntity.getCreatedTimestamp())
                .updatedTimestamp(transactionInvitationEntity.getUpdatedTimestamp())
                .build();
    }

}
