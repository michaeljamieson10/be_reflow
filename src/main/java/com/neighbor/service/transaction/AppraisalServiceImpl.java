package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.EntityAlreadyExistException;
import com.neighbor.model.transaction.AcceptedOffer;
import com.neighbor.model.transaction.Appraisal;
import com.neighbor.model.transaction.Transaction;
import com.neighbor.persistence.entity.transaction.AcceptedOfferEntity;
import com.neighbor.persistence.entity.transaction.AppraisalEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.transaction.AppraisalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
public class AppraisalServiceImpl implements AppraisalService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    private final AppraisalRepository appraisalRepository;

    @Autowired
    public AppraisalServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            FromEntity fromEntity,
            GetEntity getEntity,
            AppraisalRepository appraisalRepository
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.appraisalRepository = appraisalRepository;
    }

    @Override
    public Appraisal createNewAppraisal(Appraisal appraisal) {

        TransactionEntity transactionEntity = getEntity.getTransactionEntityCheckREA_AndClient(appraisal.getTransaction());

        AppraisalEntity appraisalEntityCheckIfExist = appraisalRepository.findByTransactionEntity(transactionEntity);
        if(Objects.nonNull(appraisalEntityCheckIfExist)) throw new EntityAlreadyExistException(Appraisal.class,transactionEntity.getId());

        AppraisalEntity appraisalEntity = new AppraisalEntity();
        appraisalEntity.setTransactionEntity(transactionEntity);
        appraisalEntity.setAppraisedDateTime(new Timestamp(appraisal.getDateTimeMilli()));
        appraisalEntity.setAppraisedValue(appraisal.getAppraisedValue());
        appraisalEntity.setTransactionStatusType(TransactionStatusType.completed);
        appraisalEntity = appraisalRepository.save(appraisalEntity);

        return Appraisal.builder().transaction(Transaction.builder().id(transactionEntity.getId()).build()).appraisedDateTime(appraisalEntity.getAppraisedDateTime()).build();
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
