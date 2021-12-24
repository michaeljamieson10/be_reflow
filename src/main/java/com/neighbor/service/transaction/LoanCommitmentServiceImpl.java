package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.model.transaction.LoanCommitment;
import com.neighbor.persistence.entity.transaction.LoanCommitmentEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.transaction.LoanCommitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanCommitmentServiceImpl implements LoanCommitmentService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    private final LoanCommitmentRepository loanCommitmentRepository;

    @Autowired
    public LoanCommitmentServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            FromEntity fromEntity,
            GetEntity getEntity,
            LoanCommitmentRepository loanCommitmentRepository
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.loanCommitmentRepository = loanCommitmentRepository;
    }

    @Override
    public LoanCommitment createNewLoanCommitment(LoanCommitment loanCommitment) {
        TransactionEntity transactionEntity = getEntity.getTransactionEntity(loanCommitment.getTransaction());
        LoanCommitmentEntity loanCommitmentEntity = new LoanCommitmentEntity();
        loanCommitmentEntity.setTransactionEntity(transactionEntity);
        loanCommitmentEntity.setLoanCommitmentType(loanCommitment.getLoanCommitmentType());
        loanCommitmentEntity.setTransactionStatusType(TransactionStatusType.completed);
        loanCommitmentRepository.save(loanCommitmentEntity);

        return LoanCommitment.builder().loanCommitmentType(loanCommitmentEntity.getLoanCommitmentType()).build();
    }

//    @Override
//    public Client get(Client client) {
//        permissionsValidator.validateAgentOrSystemAdmin(authenticatedUserResolver.user());
//        ClientEntity clientEntity = getEntity.getClientEntity(client);
//        return fromEntity.fromClientEntity(clientEntity);
//    }

//    @Override
//    public Client createNewClient(Client client) {
////        permissionsValidator.validateAgentOrSystemAdmin(authenticatedUserResolver.user());
//        if(Objects.isNull(client.getUser())) throw new EntityMissingParametersException(Client.class, "user");
//        UserEntity userEntity = getEntity.getUserEntity(client.getUser());
//        ClientEntity clientEntity = new ClientEntity();
//        clientEntity.setUserEntity(userEntity);
//        clientEntity.setActive(true);
//        clientRepository.save(clientEntity);
//        return fromEntity.fromClientEntity(clientEntity);
//    }



}
