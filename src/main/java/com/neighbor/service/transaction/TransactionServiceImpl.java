package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionInvitationStatusType;
import com.neighbor.model.transaction.Transaction;
import com.neighbor.model.transaction.TransactionInvitation;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.entity.transaction.TransactionInvitationEntity;
import com.neighbor.persistence.repository.transaction.TransactionInvitationRepository;
import com.neighbor.persistence.repository.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final GetEntity getEntity;
    private final FromEntity fromEntity;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  PermissionsValidator permissionsValidator,
                                  AuthenticatedUserResolver authenticatedUserResolver,
                                  GetEntity getEntity,
                                  FromEntity fromEntity){
        this.transactionRepository = transactionRepository;
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
    }
    @Override
    public Transaction createNewTransaction(Transaction transaction) {
//        permissionsValidator.validateAgentOrSystemAdmin(authenticatedUserResolver.user());
//        ClientEntity clientEntity = getEntity.getClientEntity(transaction.getClient());
        UserEntity userEntity = getEntity.getUserEntity(transaction.get);
        AgentEntity agentEntity = getEntity.getAgentEntity(transaction.getAgent());
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAgentEntity(agentEntity);
        transactionRepository.save(transactionEntity);

        return fromEntity.fromTransactionEntity(transactionEntity);
    }



}
