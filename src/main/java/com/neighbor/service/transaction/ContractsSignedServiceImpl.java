package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.EntityAlreadyExistException;
import com.neighbor.model.transaction.ClearToClose;
import com.neighbor.model.transaction.ContractsSigned;
import com.neighbor.persistence.entity.transaction.ClearToCloseEntity;
import com.neighbor.persistence.entity.transaction.ContractsSignedEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.transaction.ContractsSignedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ContractsSignedServiceImpl implements ContractsSignedService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final ContractsSignedRepository contractsSignedRepository;

    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    @Autowired
    public ContractsSignedServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            FromEntity fromEntity,
            GetEntity getEntity,
            ContractsSignedRepository contractsSignedRepository
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.contractsSignedRepository = contractsSignedRepository;
    }

    @Override
    public ContractsSigned createNewContractsSigned(ContractsSigned contractsSigned) {

        TransactionEntity transactionEntity = getEntity.getTransactionEntity(contractsSigned.getTransaction());

        ContractsSignedEntity contractsSignedEntityCheckIfExist = contractsSignedRepository.findByTransactionEntity(transactionEntity);
        if(Objects.nonNull(contractsSignedEntityCheckIfExist)) throw new EntityAlreadyExistException(ContractsSigned.class,transactionEntity.getId());

        ContractsSignedEntity contractsSignedEntity = new ContractsSignedEntity();
        contractsSignedEntity.setTransactionEntity(transactionEntity);
        contractsSignedEntity.setBuyerStatus(contractsSigned.getBuyerStatus());
        contractsSignedEntity.setSellerStatus(contractsSigned.getSellerStatus());
        contractsSignedEntity.setTransactionStatusType(TransactionStatusType.completed);
        contractsSignedEntity = contractsSignedRepository.save(contractsSignedEntity);
        return ContractsSigned.builder().buyerStatus(contractsSignedEntity.getBuyerStatus()).sellerStatus(contractsSigned.getSellerStatus()).build();
    }

//    @Override
//    public Client get(Client client) {
//        permissionsValidator.validateAgentOrSystemAdmin(authenticatedUserResolver.user());
//        ClientEntity clientEntity = getEntity.getClientEntity(client);
//        return fromEntity.fromClientEntity(clientEntity);
//    }




}
