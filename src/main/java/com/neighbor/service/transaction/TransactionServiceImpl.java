package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.TransactionNotFoundException;
import com.neighbor.model.Agent;
import com.neighbor.model.Client;
import com.neighbor.model.transaction.*;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.entity.transaction.*;
import com.neighbor.persistence.repository.AgentRepository;
import com.neighbor.persistence.repository.ClientRepository;
import com.neighbor.persistence.repository.transaction.*;
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
    private final HomeCriteriaRepository homeCriteriaRepository;
    private final PreApprovalRepository preApprovalRepository;
    private final AcceptedOfferRepository acceptedOfferRepository;
    private final HomeInspectionRepository homeInspectionRepository;
    private final ContractsSignedRepository contractsSignedRepository;
    private final AppraisalRepository appraisalRepository;
    private final LoanCommitmentRepository loanCommitmentRepository;
    private final HomeownersInsuranceRepository homeownersInsuranceRepository;
    private final ClearToCloseRepository clearToCloseRepository;
    private final FinalWalkthroughRepository finalWalkthroughRepository;
    private final ClosingRepository closingRepository;

    private final GetEntity getEntity;
    private final FromEntity fromEntity;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  PermissionsValidator permissionsValidator,
                                  AuthenticatedUserResolver authenticatedUserResolver,
                                  AgentRepository agentRepository,
                                  ClientRepository clientRepository,
                                  HomeCriteriaRepository homeCriteriaRepository,
                                  PreApprovalRepository preApprovalRepository,
                                  AcceptedOfferRepository acceptedOfferRepository,
                                  HomeInspectionRepository homeInspectionRepository,
                                  ContractsSignedRepository contractsSignedRepository,
                                  AppraisalRepository appraisalRepository,
                                  LoanCommitmentRepository loanCommitmentRepository,
                                  HomeownersInsuranceRepository homeownersInsuranceRepository,
                                  ClearToCloseRepository clearToCloseRepository,
                                  FinalWalkthroughRepository finalWalkthroughRepository,
                                  ClosingRepository closingRepository,
                                  GetEntity getEntity,
                                  FromEntity fromEntity){
        this.transactionRepository = transactionRepository;
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.agentRepository = agentRepository;
        this.clientRepository = clientRepository;
        this.homeCriteriaRepository = homeCriteriaRepository;
        this.preApprovalRepository = preApprovalRepository;
        this.acceptedOfferRepository = acceptedOfferRepository;
        this.homeInspectionRepository = homeInspectionRepository;
        this.contractsSignedRepository = contractsSignedRepository;
        this.appraisalRepository = appraisalRepository;
        this.loanCommitmentRepository = loanCommitmentRepository;
        this.homeownersInsuranceRepository = homeownersInsuranceRepository;
        this.clearToCloseRepository = clearToCloseRepository;
        this.finalWalkthroughRepository = finalWalkthroughRepository;
        this.closingRepository = closingRepository;

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

    @Override
    public List<Transaction> getTransactionsListByUserToken() {
        UserEntity userEntity = authenticatedUserResolver.user();
        AgentEntity agentEntity = agentRepository.findByUserEntity(userEntity);
        List<TransactionEntity> transactionEntityList = null;
        if(Objects.nonNull(agentEntity)){
            transactionEntityList = transactionRepository.findTransactionEntityByAgentEntity(agentEntity);
        }else{
            ClientEntity clientEntity = clientRepository.findByUserEntity(userEntity);
            transactionEntityList = transactionRepository.findTransactionEntityByClientEntity(clientEntity);
        }
        return fromTransactionList(transactionEntityList);
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        UserEntity userEntity = authenticatedUserResolver.user();
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId).orElse(null);
        return fromTransactionEntity(transactionEntity);
    }


    @Override
    public Transaction getTransactionFlowsComplete(int transactionId) {
        UserEntity userEntity = authenticatedUserResolver.user();
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId).orElse(null);
        if(Objects.isNull(transactionEntity)) throw new TransactionNotFoundException(transactionId);

        return fromTransactionEntity(transactionEntity);
    }

    @Override
    public Transaction getTransactionFlows(int transactionId) {
        int transactionsComplete = 0;
        UserEntity userEntity = authenticatedUserResolver.user();
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId).orElse(null);
        if(Objects.isNull(transactionEntity)) throw new TransactionNotFoundException(transactionId);

        HomeCriteriaEntity homeCriteriaEntity = homeCriteriaRepository.findByTransactionEntity(transactionEntity);
        HomeCriteria homeCriteria = HomeCriteria.builder().build();
        if(Objects.nonNull(homeCriteriaEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(homeCriteriaEntity.getTransactionStatusType(),transactionsComplete);
            homeCriteria = HomeCriteria.builder().id(homeCriteriaEntity.getId()).transactionStatusType(homeCriteriaEntity.getTransactionStatusType()).build();
        }

//        returnPreApprovalAndCheckIfComplete(transactionEntity,transactionsComplete);
        PreApprovalEntity preApprovalEntity = preApprovalRepository.findByTransactionEntity(transactionEntity);
        PreApproval preApproval = PreApproval.builder().build();
        if(Objects.nonNull(preApprovalEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(preApprovalEntity.getTransactionStatusType(),transactionsComplete);
            preApproval = PreApproval.builder().id(preApprovalEntity.getId()).transactionStatusType(preApprovalEntity.getTransactionStatusType()).build();
        }

        AcceptedOfferEntity acceptedOfferEntity = acceptedOfferRepository.findByTransactionEntity(transactionEntity);
        AcceptedOffer acceptedOffer = AcceptedOffer.builder().build();;
        if(Objects.nonNull(acceptedOfferEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(acceptedOfferEntity.getTransactionStatusType(),transactionsComplete);
            acceptedOffer = AcceptedOffer.builder().id(acceptedOfferEntity.getId()).transactionStatusType(acceptedOfferEntity.getTransactionStatusType()).build();
        }

        HomeInspectionEntity homeInspectionEntity = homeInspectionRepository.findByTransactionEntity(transactionEntity);
        HomeInspection homeInspection = HomeInspection.builder().build();;
        if(Objects.nonNull(homeInspectionEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(homeInspectionEntity.getTransactionStatusType(),transactionsComplete);
            homeInspection = HomeInspection.builder().id(homeInspectionEntity.getId()).transactionStatusType(homeInspectionEntity.getTransactionStatusType()).build();
        }

        ContractsSignedEntity contractsSignedEntity = contractsSignedRepository.findByTransactionEntity(transactionEntity);
        ContractsSigned contractsSigned = ContractsSigned.builder().build();;
        if(Objects.nonNull(contractsSignedEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(contractsSignedEntity.getTransactionStatusType(),transactionsComplete);
            contractsSigned = ContractsSigned.builder().id(contractsSignedEntity.getId()).transactionStatusType(contractsSignedEntity.getTransactionStatusType()).build();
        }


        AppraisalEntity appraisalEntity = appraisalRepository.findByTransactionEntity(transactionEntity);
        Appraisal appraisal = Appraisal.builder().build();;
        if(Objects.nonNull(appraisalEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(appraisalEntity.getTransactionStatusType(),transactionsComplete);
            appraisal = Appraisal.builder().id(appraisalEntity.getId()).transactionStatusType(appraisalEntity.getTransactionStatusType()).build();
        }

        LoanCommitmentEntity loanCommitmentEntity = loanCommitmentRepository.findByTransactionEntity(transactionEntity);
        LoanCommitment loanCommitment = LoanCommitment.builder().build();;
        if(Objects.nonNull(loanCommitmentEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(homeInspectionEntity.getTransactionStatusType(),transactionsComplete);
            loanCommitment = LoanCommitment.builder().id(loanCommitmentEntity.getId()).transactionStatusType(loanCommitmentEntity.getTransactionStatusType()).build();
        }

        HomeownersInsuranceEntity homeownersInsuranceEntity = homeownersInsuranceRepository.findByTransactionEntity(transactionEntity);
        HomeownersInsurance homeownersInsurance = HomeownersInsurance.builder().build();
        if(Objects.nonNull(homeownersInsuranceEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(homeownersInsuranceEntity.getTransactionStatusType(),transactionsComplete);
            homeownersInsurance = HomeownersInsurance.builder().id(homeownersInsuranceEntity.getId()).transactionStatusType(homeownersInsuranceEntity.getTransactionStatusType()).build();
        }

        ClearToCloseEntity clearToCloseEntity = clearToCloseRepository.findByTransactionEntity(transactionEntity);
        ClearToClose clearToClose = ClearToClose.builder().build();
        if(Objects.nonNull(clearToCloseEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(homeownersInsuranceEntity.getTransactionStatusType(),transactionsComplete);
            clearToClose = ClearToClose.builder().id(clearToCloseEntity.getId()).transactionStatusType(clearToCloseEntity.getTransactionStatusType()).build();
        }

        FinalWalkthroughEntity finalWalkthroughEntity = finalWalkthroughRepository.findByTransactionEntity(transactionEntity);
        FinalWalkthrough finalWalkthrough = FinalWalkthrough.builder().build();
        if(Objects.nonNull(finalWalkthroughEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(finalWalkthroughEntity.getTransactionStatusType(),transactionsComplete);
            finalWalkthrough = FinalWalkthrough.builder().id(finalWalkthroughEntity.getId()).transactionStatusType(finalWalkthroughEntity.getTransactionStatusType()).build();
        }

        ClosingEntity closingEntity = closingRepository.findByTransactionEntity(transactionEntity);
        Closing closing = Closing.builder().build();
        if(Objects.nonNull(closingEntity)){
            transactionsComplete = getTransactionTypeAndIncrement(closingEntity.getTransactionStatusType(),transactionsComplete);
            closing = Closing.builder().id(closingEntity.getId()).transactionStatusType(closingEntity.getTransactionStatusType()).build();
        }
        //TODO: FIND A WAY TO CHANGE THE COLOR FOR COMPLETED OR IN PROGRESS
        //you actually just need to do this with transaction complet.
        //then pass it on the transaciton object status type


        Agent agent = Agent.builder().build();
        if(Objects.nonNull(transactionEntity.getAgentEntity())) agent = Agent.builder().id(transactionEntity.getAgentEntity().getId()).build();
        Client client = Client.builder().build();
//        ClientEntity clientEntity = transactionEntity.getClientEntity();
        return Transaction.builder()
                .id(transactionEntity.getId())
                .firstName(transactionEntity.getFirstName())
                .lastName(transactionEntity.getLastName())
                .client(client)
                .agent(agent)
                .homeCriteria(homeCriteria)
                .preApproval(preApproval)
                .acceptedOffer(acceptedOffer)
                .homeInspection(homeInspection)
                .appraisal(appraisal)
                .loanCommitment(loanCommitment)
                .homeownersInsurance(homeownersInsurance)
                .clearToClose(clearToClose)
                .finalWalkthrough(finalWalkthrough)
                .transactionsComplete(transactionsComplete)
                .createdTimetamp(transactionEntity.getCreatedTimestamp())
                .updatedTimestamp(transactionEntity.getUpdatedTimestamp())
                .build();

//        return fromTransactionEntity(transactionEntity);
    }

    private void returnPreApprovalAndCheckIfComplete(TransactionEntity transactionEntity, int transactionsComplete) {
    }

    private int getTransactionTypeAndIncrement(TransactionStatusType transactionStatusType, int transactionsComplete) {
        if(transactionStatusType.equals(TransactionStatusType.completed)){  transactionsComplete = transactionsComplete + 1;}
        return transactionsComplete;
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
        if(Objects.nonNull(transactionEntity.getAgentEntity())) agent = Agent.builder().id(transactionEntity.getAgentEntity().getId()).build();
        Client client = Client.builder().build();
//        ClientEntity clientEntity = transactionEntity.getClientEntity();
        return Transaction.builder()
                .id(transactionEntity.getId())
                .firstName(transactionEntity.getFirstName())
                .lastName(transactionEntity.getLastName())
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
