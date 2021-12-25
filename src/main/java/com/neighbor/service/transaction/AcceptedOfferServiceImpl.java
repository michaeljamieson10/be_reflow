package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.EntityAlreadyExistException;
import com.neighbor.model.transaction.AcceptedOffer;
import com.neighbor.model.transaction.Closing;
import com.neighbor.persistence.entity.transaction.AcceptedOfferEntity;
import com.neighbor.persistence.entity.transaction.ClosingEntity;
import com.neighbor.persistence.entity.transaction.PreApprovalEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.transaction.AcceptedOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AcceptedOfferServiceImpl implements AcceptedOfferService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final AcceptedOfferRepository acceptedOfferRepository;
    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    @Autowired
    public AcceptedOfferServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            AcceptedOfferRepository acceptedOfferRepository,
            FromEntity fromEntity,
            GetEntity getEntity
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.acceptedOfferRepository = acceptedOfferRepository;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
    }

    @Override
    public AcceptedOffer createAcceptedOffer(AcceptedOffer acceptedOffer) {
//        permissionsValidator.validateAgentOrSystemAdminOrClient(authenticatedUserResolver.user());
        TransactionEntity transactionEntity = getEntity.getTransactionEntity(acceptedOffer.getTransaction());


        AcceptedOfferEntity acceptedOfferEntityCheckIfExist = acceptedOfferRepository.findByTransactionEntity(transactionEntity);
        if(Objects.nonNull(acceptedOfferEntityCheckIfExist)) throw new EntityAlreadyExistException(AcceptedOffer.class,transactionEntity.getId());
//        PreApprovalEntity preApprovalEntity = new PreApprovalEntity();
//        preApprovalEntity.setTransactionEntity(transactionEntity);
//        acceptedOfferRepository.save(preApprovalEntity);
//        return fromEntity.fromPreApprovalCriteriaEntity(preApprovalEntity);
        AcceptedOfferEntity acceptedOfferEntity = new AcceptedOfferEntity();
        acceptedOfferEntity.setTransactionEntity(transactionEntity);
        acceptedOfferEntity.setAddress(acceptedOffer.getAddress());
        acceptedOfferEntity.setPurchasePrice(acceptedOffer.getPurchasePrice());
        acceptedOfferEntity.setPropertyTaxes(acceptedOffer.getPropertyTaxes());
        acceptedOfferEntity.setDownPayment(acceptedOffer.getDownPayment());
        acceptedOfferEntity.setTransactionStatusType(TransactionStatusType.completed);
        acceptedOfferRepository.save(acceptedOfferEntity);

        return fromEntity.fromAcceptedOfferEntity(acceptedOfferEntity);

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
