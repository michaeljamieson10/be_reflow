package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.model.transaction.HomeCriteria;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.transaction.HomeCriteriaEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.ClientRepository;
import com.neighbor.persistence.repository.UserRepository;
import com.neighbor.persistence.repository.transaction.HomeCriteriaRepository;
import com.neighbor.persistence.repository.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeCriteriaServiceImpl implements HomeCriteriaService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final HomeCriteriaRepository homeCriteriaRepository;
    private final TransactionRepository transactionRepository;
    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    @Autowired
    public HomeCriteriaServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            UserRepository userRepository,
            ClientRepository clientRepository,
            HomeCriteriaRepository homeCriteriaRepository,
            TransactionRepository transactionRepository,
            FromEntity fromEntity,
            GetEntity getEntity
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.homeCriteriaRepository = homeCriteriaRepository;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public HomeCriteria createNewHomeCriteria(HomeCriteria homeCriteria) {
        TransactionEntity transactionEntity = getEntity.getTransactionEntity(homeCriteria.getTransaction());
        AgentEntity agentEntity = getEntity.getAgentEntity(homeCriteria.getTransaction().getAgent());
//        permissionsValidator.validateCorrectUserEntity(transactionEntity.getAgentEntity().getUserEntity(), agentEntity.getUserEntity());
        HomeCriteriaEntity homeCriteriaEntity = new HomeCriteriaEntity();
        homeCriteriaEntity.setTransactionEntity(transactionEntity);
        homeCriteriaRepository.save(homeCriteriaEntity);
        return fromEntity.fromHomeCriteriaEntity(homeCriteriaEntity);
    }

}