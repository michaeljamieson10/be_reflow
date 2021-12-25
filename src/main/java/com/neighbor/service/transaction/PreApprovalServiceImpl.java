package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
import com.neighbor.exception.EntityAlreadyExistException;
import com.neighbor.model.transaction.LoanCommitment;
import com.neighbor.model.transaction.PreApproval;
import com.neighbor.persistence.entity.transaction.LoanCommitmentEntity;
import com.neighbor.persistence.entity.transaction.PreApprovalEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.ClientRepository;
import com.neighbor.persistence.repository.UserRepository;
import com.neighbor.persistence.repository.transaction.PreApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PreApprovalServiceImpl implements PreApprovalService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PreApprovalRepository preApprovalRepository;
    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    @Autowired
    public PreApprovalServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            UserRepository userRepository,
            ClientRepository clientRepository,
            PreApprovalRepository preApprovalRepository,
            FromEntity fromEntity,
            GetEntity getEntity
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.preApprovalRepository = preApprovalRepository;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
    }

    @Override
    public PreApproval createNewPreApproval(PreApproval preApproval) {
//        permissionsValidator.validateAgentOrSystemAdminOrClient(authenticatedUserResolver.user());
        TransactionEntity transactionEntity = getEntity.getTransactionEntity(preApproval.getTransaction());

        PreApprovalEntity preApprovalEntityCheckIfExist = preApprovalRepository.findByTransactionEntity(transactionEntity);
        if(Objects.nonNull(preApprovalEntityCheckIfExist)) throw new EntityAlreadyExistException(PreApproval.class,transactionEntity.getId());

        PreApprovalEntity preApprovalEntity = new PreApprovalEntity();
        preApprovalEntity.setTransactionEntity(transactionEntity);
        preApprovalEntity.setMaxPurchasePrice(preApproval.getMaxPurchasePrice());
        preApprovalEntity.setMaxLoanAmount(preApproval.getMaxLoanAmount());
        preApprovalEntity.setMaxTaxes(preApproval.getMaxPropertyTaxes());
        preApprovalEntity.setDownPayment(preApproval.getDownPayment());
        preApprovalEntity.setLoanType(preApproval.getLoanType());
        preApprovalEntity.setTransactionStatusType(TransactionStatusType.completed);
        preApprovalRepository.save(preApprovalEntity);
        return fromEntity.fromPreApprovalCriteriaEntity(preApprovalEntity);
    }

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
