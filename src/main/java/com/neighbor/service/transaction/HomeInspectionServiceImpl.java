package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.EntityAlreadyExistException;
import com.neighbor.model.transaction.HomeCriteria;
import com.neighbor.model.transaction.HomeInspection;
import com.neighbor.persistence.entity.transaction.AcceptedOfferEntity;
import com.neighbor.persistence.entity.transaction.HomeCriteriaEntity;
import com.neighbor.persistence.entity.transaction.HomeInspectionEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.transaction.HomeInspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;


@Service
public class HomeInspectionServiceImpl implements HomeInspectionService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final HomeInspectionRepository homeInspectionRepository;

    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    @Autowired
    public HomeInspectionServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            HomeInspectionRepository homeInspectionRepository,
            FromEntity fromEntity,
            GetEntity getEntity
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.homeInspectionRepository = homeInspectionRepository;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
    }

    @Override
    public HomeInspection createHomeInspection(HomeInspection homeInspection) {
//        permissionsValidator.validateAgentOrSystemAdminOrClient(authenticatedUserResolver.user());
        TransactionEntity transactionEntity = getEntity.getTransactionEntity(homeInspection.getTransaction());

        HomeInspectionEntity homeInspectionEntityCheckIfExist = homeInspectionRepository.findByTransactionEntity(transactionEntity);
        if(Objects.nonNull(homeInspectionEntityCheckIfExist)) throw new EntityAlreadyExistException(HomeInspection.class,transactionEntity.getId());


        HomeInspectionEntity homeInspectionEntity = new HomeInspectionEntity();
        homeInspectionEntity.setTransactionEntity(transactionEntity);
        homeInspectionEntity.setHomeInspectionStatusType(homeInspection.getHomeInspectionStatusType());
        if(Objects.nonNull(homeInspection.getScheduledDateTimeMilli()))homeInspectionEntity.setScheduledDateTime(new Timestamp(homeInspection.getScheduledDateTimeMilli()));
        homeInspectionEntity.setTransactionStatusType(TransactionStatusType.completed);
        homeInspectionRepository.save(homeInspectionEntity);

        return fromEntity.fromHomeInspectionEntity(homeInspectionEntity);

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
