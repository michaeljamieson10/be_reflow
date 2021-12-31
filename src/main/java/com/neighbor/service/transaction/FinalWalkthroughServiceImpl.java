package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.EntityAlreadyExistException;
import com.neighbor.model.transaction.ContractsSigned;
import com.neighbor.model.transaction.FinalWalkthrough;
import com.neighbor.persistence.entity.transaction.ContractsSignedEntity;
import com.neighbor.persistence.entity.transaction.FinalWalkthroughEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.transaction.FinalWalkthroughRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FinalWalkthroughServiceImpl implements FinalWalkthroughService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    private final FinalWalkthroughRepository finalWalkthroughRepository;

    @Autowired
    public FinalWalkthroughServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            FromEntity fromEntity,
            GetEntity getEntity,
            FinalWalkthroughRepository finalWalkthroughRepository
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.finalWalkthroughRepository = finalWalkthroughRepository;
    }

    @Override
    public FinalWalkthrough createNewFinalWalkthrough(FinalWalkthrough finalWalkthrough) {
        TransactionEntity transactionEntity = getEntity.getTransactionEntityCheckREA_AndClient(finalWalkthrough.getTransaction());

        FinalWalkthroughEntity finalWalkthroughEntityCheckIfExist = finalWalkthroughRepository.findByTransactionEntity(transactionEntity);
        if(Objects.nonNull(finalWalkthroughEntityCheckIfExist)) throw new EntityAlreadyExistException(FinalWalkthrough.class,transactionEntity.getId());

        FinalWalkthroughEntity finalWalkthroughEntity = new FinalWalkthroughEntity();
        finalWalkthroughEntity.setTransactionEntity(transactionEntity);
        finalWalkthroughEntity.setFinalWalkthroughStatusType(finalWalkthrough.getFinalWalkthroughStatusType());
        finalWalkthroughEntity.setTransactionStatusType(TransactionStatusType.completed);
        finalWalkthroughRepository.save(finalWalkthroughEntity);

        return FinalWalkthrough.builder().build();
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
