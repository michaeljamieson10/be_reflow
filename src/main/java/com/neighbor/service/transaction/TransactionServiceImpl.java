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
        Agent agent = null; Client client = null;
        if(Objects.nonNull(agentEntity)) agent = Agent.builder().id(transactionEntity.getAgentEntity().getId()).build();
        if(Objects.nonNull(clientEntity)) client = Client.builder().id(transactionEntity.getClientEntity().getId()).build();
        return Transaction.builder()
                .id(transactionEntity.getId())
                .agent(agent)
                .client(client)
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

        HomeCriteria homeCriteria = getHomeCriteriaByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(homeCriteria.getTransactionStatusType(),transactionsComplete);

        PreApproval preApproval = getPreApprovalByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(preApproval.getTransactionStatusType(),transactionsComplete);

        AcceptedOffer acceptedOffer = getAcceptedOfferByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(acceptedOffer.getTransactionStatusType(),transactionsComplete);

        HomeInspection homeInspection = getHomeInspectionByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(homeInspection.getTransactionStatusType(),transactionsComplete);

        ContractsSigned contractsSigned = getContractsSignedByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(contractsSigned.getTransactionStatusType(),transactionsComplete);

        Appraisal appraisal = getAppraisalByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(appraisal.getTransactionStatusType(),transactionsComplete);

        LoanCommitment loanCommitment = getLoanCommitmentByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(loanCommitment.getTransactionStatusType(),transactionsComplete);

        HomeownersInsurance homeownersInsurance = getHomeownersInsuranceByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(homeownersInsurance.getTransactionStatusType(),transactionsComplete);

        ClearToClose clearToClose = getClearToCloseByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(clearToClose.getTransactionStatusType(),transactionsComplete);

        FinalWalkthrough finalWalkthrough = getFinalWalkthroughByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(finalWalkthrough.getTransactionStatusType(),transactionsComplete);

        Closing closing = getClosingByTransactionEntity(transactionEntity);
        transactionsComplete = getTransactionTypeAndIncrement(closing.getTransactionStatusType(),transactionsComplete);

        Agent agent = Agent.builder().build();
        if(Objects.nonNull(transactionEntity.getAgentEntity())) agent = Agent.builder().id(transactionEntity.getAgentEntity().getId()).build();
        Client client = Client.builder().build();

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
                .contractsSigned(contractsSigned)
                .appraisal(appraisal)
                .loanCommitment(loanCommitment)
                .homeownersInsurance(homeownersInsurance)
                .clearToClose(clearToClose)
                .finalWalkthrough(finalWalkthrough)
                .closing(closing)
                .transactionsComplete(transactionsComplete)
                .createdTimetamp(transactionEntity.getCreatedTimestamp())
                .updatedTimestamp(transactionEntity.getUpdatedTimestamp())
                .build();

//        return fromTransactionEntity(transactionEntity);
    }

    private Closing getClosingByTransactionEntity(TransactionEntity transactionEntity) {
        ClosingEntity closingEntity = closingRepository.findByTransactionEntity(transactionEntity);
        Closing closing = Closing.builder().build();
        if(Objects.nonNull(closingEntity)){
            closing = Closing.builder().id(closingEntity.getId()).transactionStatusType(closingEntity.getTransactionStatusType()).build();
        }
        return closing;
    }

    private FinalWalkthrough getFinalWalkthroughByTransactionEntity(TransactionEntity transactionEntity) {
        FinalWalkthroughEntity finalWalkthroughEntity = finalWalkthroughRepository.findByTransactionEntity(transactionEntity);
        FinalWalkthrough finalWalkthrough = FinalWalkthrough.builder().build();
        if(Objects.nonNull(finalWalkthroughEntity)){
            finalWalkthrough = FinalWalkthrough.builder().id(finalWalkthroughEntity.getId()).transactionStatusType(finalWalkthroughEntity.getTransactionStatusType()).build();
        }
        return finalWalkthrough;
    }

    private ClearToClose getClearToCloseByTransactionEntity(TransactionEntity transactionEntity) {
        ClearToCloseEntity clearToCloseEntity = clearToCloseRepository.findByTransactionEntity(transactionEntity);
        ClearToClose clearToClose = ClearToClose.builder().build();
        if(Objects.nonNull(clearToCloseEntity)){
            clearToClose = ClearToClose.builder().id(clearToCloseEntity.getId()).transactionStatusType(clearToCloseEntity.getTransactionStatusType()).build();
        }
        return clearToClose;
    }

    private HomeownersInsurance getHomeownersInsuranceByTransactionEntity(TransactionEntity transactionEntity) {
        HomeownersInsuranceEntity homeownersInsuranceEntity = homeownersInsuranceRepository.findByTransactionEntity(transactionEntity);
        HomeownersInsurance homeownersInsurance = HomeownersInsurance.builder().build();
        if(Objects.nonNull(homeownersInsuranceEntity)){
            homeownersInsurance = HomeownersInsurance.builder().id(homeownersInsuranceEntity.getId()).transactionStatusType(homeownersInsuranceEntity.getTransactionStatusType()).build();
        }
        return homeownersInsurance;

    }

    private LoanCommitment getLoanCommitmentByTransactionEntity(TransactionEntity transactionEntity) {
        LoanCommitmentEntity loanCommitmentEntity = loanCommitmentRepository.findByTransactionEntity(transactionEntity);
        LoanCommitment loanCommitment = LoanCommitment.builder().build();;
        if(Objects.nonNull(loanCommitmentEntity)){
            loanCommitment = LoanCommitment.builder().id(loanCommitmentEntity.getId()).transactionStatusType(loanCommitmentEntity.getTransactionStatusType()).build();
        }
        return loanCommitment;
    }

    private Appraisal getAppraisalByTransactionEntity(TransactionEntity transactionEntity) {
        AppraisalEntity appraisalEntity = appraisalRepository.findByTransactionEntity(transactionEntity);
        Appraisal appraisal = Appraisal.builder().build();;
        if(Objects.nonNull(appraisalEntity)){
            appraisal = Appraisal.builder().id(appraisalEntity.getId()).transactionStatusType(appraisalEntity.getTransactionStatusType()).build();
        }
        return appraisal;
    }

    private ContractsSigned getContractsSignedByTransactionEntity(TransactionEntity transactionEntity) {
        ContractsSignedEntity contractsSignedEntity = contractsSignedRepository.findByTransactionEntity(transactionEntity);
        ContractsSigned contractsSigned = ContractsSigned.builder().build();;
        if(Objects.nonNull(contractsSignedEntity)){
            contractsSigned = ContractsSigned.builder().id(contractsSignedEntity.getId()).transactionStatusType(contractsSignedEntity.getTransactionStatusType()).build();
        }
        return contractsSigned;
    }

    private HomeInspection getHomeInspectionByTransactionEntity(TransactionEntity transactionEntity) {
        HomeInspectionEntity homeInspectionEntity = homeInspectionRepository.findByTransactionEntity(transactionEntity);
        HomeInspection homeInspection = HomeInspection.builder().build();;
        if(Objects.nonNull(homeInspectionEntity)){
            homeInspection = HomeInspection.builder().id(homeInspectionEntity.getId()).transactionStatusType(homeInspectionEntity.getTransactionStatusType()).build();
        }
        return homeInspection;
    }

    private AcceptedOffer getAcceptedOfferByTransactionEntity(TransactionEntity transactionEntity) {
        AcceptedOfferEntity acceptedOfferEntity = acceptedOfferRepository.findByTransactionEntity(transactionEntity);
        AcceptedOffer acceptedOffer = AcceptedOffer.builder().build();;
        if(Objects.nonNull(acceptedOfferEntity)){
            acceptedOffer = AcceptedOffer.builder().id(acceptedOfferEntity.getId()).transactionStatusType(acceptedOfferEntity.getTransactionStatusType()).build();
        }
        return acceptedOffer;
    }

    private PreApproval getPreApprovalByTransactionEntity(TransactionEntity transactionEntity) {
        PreApprovalEntity preApprovalEntity = preApprovalRepository.findByTransactionEntity(transactionEntity);
        PreApproval preApproval = PreApproval.builder().build();
        if(Objects.nonNull(preApprovalEntity)){
            preApproval = PreApproval.builder().id(preApprovalEntity.getId()).transactionStatusType(preApprovalEntity.getTransactionStatusType()).build();
        }
        return preApproval;
    }

    private HomeCriteria getHomeCriteriaByTransactionEntity(TransactionEntity transactionEntity) {
        HomeCriteriaEntity homeCriteriaEntity = homeCriteriaRepository.findByTransactionEntity(transactionEntity);
        HomeCriteria homeCriteria = HomeCriteria.builder().build();
        if(Objects.nonNull(homeCriteriaEntity)){
            homeCriteria = HomeCriteria.builder().id(homeCriteriaEntity.getId()).transactionStatusType(homeCriteriaEntity.getTransactionStatusType()).build();
        }
        return homeCriteria;
    }

    private void returnPreApprovalAndCheckIfComplete(TransactionEntity transactionEntity, int transactionsComplete) {
    }

    private int getTransactionTypeAndIncrement(TransactionStatusType transactionStatusType, int transactionsComplete) {
        if(Objects.nonNull(transactionStatusType) && transactionStatusType.equals(TransactionStatusType.completed)){  transactionsComplete = transactionsComplete + 1;}
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
