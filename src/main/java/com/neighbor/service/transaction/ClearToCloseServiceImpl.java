package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.EntityAlreadyExistException;
import com.neighbor.model.transaction.Appraisal;
import com.neighbor.model.transaction.ClearToClose;
import com.neighbor.persistence.entity.transaction.AppraisalEntity;
import com.neighbor.persistence.entity.transaction.ClearToCloseEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.transaction.ClearToCloseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClearToCloseServiceImpl implements ClearToCloseService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    private final ClearToCloseRepository clearToCloseRepository;

    @Autowired
    public ClearToCloseServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            FromEntity fromEntity,
            GetEntity getEntity,
            ClearToCloseRepository clearToCloseRepository
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.clearToCloseRepository = clearToCloseRepository;
    }

    @Override
    public ClearToClose createNewClearToClose(ClearToClose clearToClose) {

        TransactionEntity transactionEntity = getEntity.getTransactionEntityCheckREA_AndClient(clearToClose.getTransaction());

        ClearToCloseEntity clearToCloseEntityCheckIfExist = clearToCloseRepository.findByTransactionEntity(transactionEntity);
        if(Objects.nonNull(clearToCloseEntityCheckIfExist)) throw new EntityAlreadyExistException(ClearToClose.class,transactionEntity.getId());

        ClearToCloseEntity clearToCloseEntity = new ClearToCloseEntity();
        clearToCloseEntity.setTransactionEntity(transactionEntity);
        clearToCloseEntity.setClearToCloseStatusType(clearToClose.getClearToCloseStatusType());
        clearToCloseEntity.setTransactionStatusType(TransactionStatusType.completed);
        clearToCloseRepository.save(clearToCloseEntity);

        return ClearToClose.builder().build();
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
