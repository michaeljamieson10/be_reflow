package com.neighbor.component;

import com.neighbor.exception.AgentNotFoundException;
import com.neighbor.exception.ClientNotFoundException;
import com.neighbor.exception.NotFoundException;
import com.neighbor.exception.UserNotFoundException;
import com.neighbor.model.Agent;
import com.neighbor.model.Client;
import com.neighbor.model.User;
import com.neighbor.model.transaction.*;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.entity.transaction.*;
import com.neighbor.persistence.repository.AgentRepository;
import com.neighbor.persistence.repository.ClientRepository;
import com.neighbor.persistence.repository.UserRepository;
import com.neighbor.persistence.repository.transaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GetEntity {
    private final AuthenticatedUserResolver userResolver;
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
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

    @Autowired
    public GetEntity(
            AuthenticatedUserResolver userResolver,
            AgentRepository agentRepository,
            ClientRepository clientRepository,
            UserRepository userRepository,
            TransactionRepository transactionRepository,
            HomeCriteriaRepository homeCriteriaRepository,
            PreApprovalRepository preApprovalRepository,
            AcceptedOfferRepository acceptedOfferRepository,
            HomeInspectionRepository homeInspectionRepository,
            ContractsSignedRepository contractsSignedRepository,
            AppraisalRepository appraisalRepository,
            LoanCommitmentRepository loanCommitmentRepository,
            HomeownersInsuranceRepository homeownersInsuranceRepository,
            ClearToCloseRepository clearToCloseRepository,
            FinalWalkthroughRepository finalWalkthroughRepository
    ) {
        this.userResolver = userResolver;
        this.agentRepository = agentRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
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

    }

    public ClientEntity getClientEntity(Client client) {
        if(Objects.isNull(client) || Objects.isNull(client.getId())) throw new ClientNotFoundException(0);
        ClientEntity clientEntity = clientRepository.findById(client.getId()).orElse(null);
        if(Objects.isNull(clientEntity)) throw new ClientNotFoundException(client.getId());
        return clientEntity;
    }
    public AgentEntity getAgentEntity(Agent agent) {
        if(Objects.isNull(agent) || Objects.isNull(agent.getId())) throw new AgentNotFoundException(0);
        AgentEntity agentEntity = agentRepository.findById(agent.getId()).orElse(null);
        if(Objects.isNull(agentEntity)) throw new AgentNotFoundException(agent.getId());
        return agentEntity;
    }
    public UserEntity getUserEntity(User user) {
        if(Objects.isNull(user) || Objects.isNull(user.getId())) throw new UserNotFoundException(0);
        UserEntity userEntity = userRepository.findById(user.getId()).orElse(null);
        if(Objects.isNull(userEntity)) throw new UserNotFoundException(user.getId());
        return userEntity;
    }
    public TransactionEntity getTransactionEntity(Transaction transaction) {
        if(Objects.isNull(transaction) || Objects.isNull(transaction.getId())) throw new NotFoundException("Transaction",0);
        TransactionEntity transactionEntity = transactionRepository.findById(transaction.getId()).orElse(null);
        if(Objects.isNull(transactionEntity)) throw new NotFoundException("Transaction",transaction.getId());
        return transactionEntity;
    }
    public HomeCriteriaEntity getHomeCriteriaEntity(HomeCriteria homeCriteria) {
        if(Objects.isNull(homeCriteria) || Objects.isNull(homeCriteria.getId())) throw new NotFoundException("Home Criteria",0);
        HomeCriteriaEntity homeCriteriaEntity = homeCriteriaRepository.findById(homeCriteria.getId()).orElse(null);
        if(Objects.isNull(homeCriteriaEntity)) throw new NotFoundException("Home Criteria",homeCriteria.getId());
        return homeCriteriaEntity;
    }
    public PreApprovalEntity getPreApprovalEntity(PreApproval preApproval){
        if(Objects.isNull(preApproval) || Objects.isNull(preApproval.getId())) throw new NotFoundException("PreApproval",0);
        PreApprovalEntity preApprovalEntity = preApprovalRepository.findById(preApproval.getId()).orElse(null);
        if(Objects.isNull(preApprovalEntity)) throw new NotFoundException("PreApproval", preApproval.getId());
        return preApprovalEntity;
    }
    public AcceptedOfferEntity getAcceptedOfferEntity(AcceptedOffer acceptedOffer){
        if(Objects.isNull(acceptedOffer) || Objects.isNull(acceptedOffer.getId()))throw new NotFoundException("accepted offer",0);
        AcceptedOfferEntity acceptedOfferEntity = acceptedOfferRepository.findById(acceptedOffer.getId()).orElse(null);
        if(Objects.isNull(acceptedOfferEntity)) throw new NotFoundException("Accepted Offer", acceptedOffer.getId());
        return acceptedOfferEntity;
    }
    public HomeInspectionEntity getHomeInspectionEntity(HomeInspection homeInspection){
        if(Objects.isNull(homeInspection) || Objects.isNull(homeInspection.getId())) throw new NotFoundException("home inspection",0);
        HomeInspectionEntity homeInspectionEntity = homeInspectionRepository.findById(homeInspection.getId()).orElse(null);
        if(Objects.isNull(homeInspectionEntity)) throw new NotFoundException("home inspection", homeInspection.getId());
        return homeInspectionEntity;
    }

}
