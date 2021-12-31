package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.EntityAlreadyExistException;
import com.neighbor.model.transaction.HomeInspection;
import com.neighbor.model.transaction.HomeownersInsurance;
import com.neighbor.persistence.entity.transaction.HomeInspectionEntity;
import com.neighbor.persistence.entity.transaction.HomeownersInsuranceEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.transaction.HomeownersInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HomeownersInsuranceServiceImpl implements HomeownersInsuranceService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    private final HomeownersInsuranceRepository homeownersInsuranceRepository;

    @Autowired
    public HomeownersInsuranceServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            FromEntity fromEntity,
            GetEntity getEntity,
            HomeownersInsuranceRepository homeownersInsuranceRepository
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.homeownersInsuranceRepository = homeownersInsuranceRepository;
    }

    @Override
    public HomeownersInsurance createHomeownersInsurance(HomeownersInsurance homeInspection) {
        TransactionEntity transactionEntity = getEntity.getTransactionEntityCheckREA_AndClient(homeInspection.getTransaction());

        HomeownersInsuranceEntity homeownersInsuranceEntityCheckIfExist = homeownersInsuranceRepository.findByTransactionEntity(transactionEntity);
        if(Objects.nonNull(homeownersInsuranceEntityCheckIfExist)) throw new EntityAlreadyExistException(HomeownersInsurance.class,transactionEntity.getId());

        HomeownersInsuranceEntity homeownersInsuranceEntity = new HomeownersInsuranceEntity();
        homeownersInsuranceEntity.setTransactionEntity(transactionEntity);
        homeownersInsuranceEntity.setHomeOwnersInsuranceStatusType(homeInspection.getHomeOwnersInsuranceStatusType());
        homeownersInsuranceEntity.setTransactionStatusType(TransactionStatusType.completed);
        homeownersInsuranceRepository.save(homeownersInsuranceEntity);
        return HomeownersInsurance.builder().build();
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
