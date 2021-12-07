package com.neighbor.service.Transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionInvitationStatusType;
import com.neighbor.model.transaction.TransactionInvitation;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
import com.neighbor.persistence.entity.transaction.TransactionInvitationEntity;
import com.neighbor.persistence.repository.transaction.TransactionInvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionInvitationServiceImpl implements TransactionInvitationService {
    private final TransactionInvitationRepository transactionInvitationRepository;
    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final GetEntity getEntity;
    private final FromEntity fromEntity;

    @Autowired
    public TransactionInvitationServiceImpl(TransactionInvitationRepository transactionInvitationRepository,
                                            PermissionsValidator permissionsValidator,
                                            AuthenticatedUserResolver authenticatedUserResolver,
                                            GetEntity getEntity,
                                            FromEntity fromEntity){
        this.transactionInvitationRepository = transactionInvitationRepository;
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
    }
    @Override
    public TransactionInvitation createNewTransactionInvitation(TransactionInvitation transactionInvitation) {
        permissionsValidator.validateAgentOrSystemAdmin(authenticatedUserResolver.user());
        ClientEntity clientEntity = getEntity.getClientEntity(transactionInvitation.getClient());
        AgentEntity agentEntity = getEntity.getAgentEntity(transactionInvitation.getAgent());
        TransactionInvitationEntity transactionInvitationEntity = new TransactionInvitationEntity();
        //TODO: Some sort of transaction name before getting any other detail
        transactionInvitationEntity.setAgentEntity(agentEntity);
        transactionInvitationEntity.setClientEntity(clientEntity);
        transactionInvitationEntity.setTransactionInvitationStatusType(TransactionInvitationStatusType.pending);
        transactionInvitationRepository.save(transactionInvitationEntity);

        return fromEntity.fromTransactionInvitationEntity(transactionInvitationEntity);
    }



}
