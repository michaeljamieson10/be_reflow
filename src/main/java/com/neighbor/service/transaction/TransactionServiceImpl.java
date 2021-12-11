package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionInvitationStatusType;
import com.neighbor.model.Agent;
import com.neighbor.model.Client;
import com.neighbor.model.transaction.Transaction;
import com.neighbor.model.transaction.TransactionInvitation;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.entity.transaction.TransactionInvitationEntity;
import com.neighbor.persistence.repository.AgentRepository;
import com.neighbor.persistence.repository.ClientRepository;
import com.neighbor.persistence.repository.transaction.TransactionInvitationRepository;
import com.neighbor.persistence.repository.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;
    private final GetEntity getEntity;
    private final FromEntity fromEntity;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  PermissionsValidator permissionsValidator,
                                  AuthenticatedUserResolver authenticatedUserResolver,
                                  AgentRepository agentRepository,
                                  ClientRepository clientRepository,
                                  GetEntity getEntity,
                                  FromEntity fromEntity){
        this.transactionRepository = transactionRepository;
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.agentRepository = agentRepository;
        this.clientRepository = clientRepository;
    }
    @Override
    public Transaction createNewTransaction(Transaction transaction) {
//        permissionsValidator.validateAgentOrSystemAdmin(authenticatedUserResolver.user());
//        ClientEntity clientEntity = getEntity.getClientEntity(transaction.getClient());
//        UserEntity userEntity = getEntity.getUserEntity(transaction.g);
//        AgentEntity agentEntity = getEntity.getAgentEntityByUserEntity_Id(transaction.getAgent().getId());
//        AgentEntity agentEntity = getEntity.getAgentEntity(transaction.getAgent());
//        AgentEntity agentEntity = getEntity.getAgentEntityByUserEntity(transaction.getAgent());
        UserEntity userEntity = authenticatedUserResolver.user();
//        ClientEntity clientEntity = clientRepository.findByUserEntity(userEntity);
        AgentEntity agentEntity = agentRepository.findByUserEntity(userEntity);
        ClientEntity clientEntity = clientRepository.findByUserEntity(userEntity);

        TransactionEntity transactionEntity = new TransactionEntity();

        if(Objects.nonNull(agentEntity))transactionEntity.setAgentEntity(agentEntity);
        if(Objects.nonNull(clientEntity))transactionEntity.setClientEntity(clientEntity);


        transactionEntity.setFirstName(transaction.getFirstName());
        transactionEntity.setLastName(transaction.getLastName());
        transactionEntity = transactionRepository.save(transactionEntity);

//        return fromEntity.fromTransactionEntity(transactionEntity,transactionEntity.getAgentEntity());
        return Transaction.builder()
                .id(transactionEntity.getId())
                .agent(Agent.builder().id(transactionEntity.getAgentEntity().getId()).build())
                .firstName(transactionEntity.getFirstName())
                .lastName(transactionEntity.getLastName())
                .createdTimetamp(transactionEntity.getCreatedTimestamp())
                .updatedTimestamp(transactionEntity.getUpdatedTimestamp())
                .build();
    }

    @Override
    public List<Transaction> getTransactionsListByAgent(int agentId) {
        UserEntity userEntity = authenticatedUserResolver.user();
//        AgentEntity agentEntity = agentRepository.findByUserEntity(userEntity);
        AgentEntity agentEntity= agentRepository.findById(agentId).orElse(null);

        List<TransactionEntity> transactionEntityList = transactionRepository.findTransactionEntityByAgentEntity(agentEntity);

//        ClientEntity clientEntity = clientRepository.findByUserEntity(userEntity);

        return fromTransactionList(transactionEntityList);
    }

    public Transaction fromTransactionEntity(TransactionEntity transactionEntity,AgentEntity agentEntity, ClientEntity clientEntity) {
        Agent agent = Agent.builder().build();
        Client client = Client.builder().build();
//        ClientEntity clientEntity = transactionEntity.getClientEntity();
        if(Objects.isNull(agentEntity)){agent = Agent.builder().id(transactionEntity.getAgentEntity().getId()).build();}
        if(Objects.isNull(clientEntity)){client = Client.builder().id(transactionEntity.getClientEntity().getId()).build();}
        return Transaction.builder()
                .id(transactionEntity.getId())
                .client(client)
                .agent(agent)
                .createdTimetamp(transactionEntity.getCreatedTimestamp())
                .updatedTimestamp(transactionEntity.getUpdatedTimestamp())
                .build();
    }
    public Transaction fromTransactionEntity(TransactionEntity transactionEntity) {
        Agent agent = Agent.builder().build();
        Client client = Client.builder().build();
//        ClientEntity clientEntity = transactionEntity.getClientEntity();
        return Transaction.builder()
                .id(transactionEntity.getId())
                .client(client)
                .agent(agent)
                .createdTimetamp(transactionEntity.getCreatedTimestamp())
                .updatedTimestamp(transactionEntity.getUpdatedTimestamp())
                .build();
    }
    public List<Transaction> fromTransactionList( List<TransactionEntity> transactionEntityList){
        return transactionEntityList.stream().map(this::fromTransactionEntity).collect(Collectors.toList());
    };

}
