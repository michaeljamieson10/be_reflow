package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.EntityAlreadyExistException;
import com.neighbor.model.transaction.Closing;
import com.neighbor.persistence.entity.transaction.ClosingEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.transaction.ClosingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClosingServiceImpl implements ClosingService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    private final ClosingRepository closingRepository;

    @Autowired
    public ClosingServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            FromEntity fromEntity,
            GetEntity getEntity,
            ClosingRepository closingRepository
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.closingRepository = closingRepository;
    }

    @Override
    public Closing createNewClosing(Closing closing) {
        TransactionEntity transactionEntity = getEntity.getTransactionEntityCheckREA_AndClient(closing.getTransaction());

        ClosingEntity closingEntityCheckIfExist = closingRepository.findByTransactionEntity(transactionEntity);
        if(Objects.nonNull(closingEntityCheckIfExist)) throw new EntityAlreadyExistException(Closing.class,transactionEntity.getId());

        ClosingEntity closingEntity = new ClosingEntity();
        closingEntity.setClosingStatusType(closing.getClosingStatusType());
        closingEntity.setTransactionStatusType(TransactionStatusType.completed);
        closingEntity.setTransactionEntity(transactionEntity);
        closingRepository.save(closingEntity);
        return Closing.builder().build();
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
