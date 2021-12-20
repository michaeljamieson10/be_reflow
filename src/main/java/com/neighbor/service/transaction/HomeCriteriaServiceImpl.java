package com.neighbor.service.transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.enums.TransactionStatusType;
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

import java.util.Objects;

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
//        AgentEntity agentEntity = getEntity.getAgentEntity(homeCriteria.getTransaction().getAgent());
//        permissionsValidator.validateCorrectUserEntity(transactionEntity.getAgentEntity().getUserEntity(), agentEntity.getUserEntity());
        //TODO: Check if already exist throw error
        HomeCriteriaEntity homeCriteriaEntity = new HomeCriteriaEntity();
        homeCriteriaEntity.setTransactionEntity(transactionEntity);

        if(Objects.nonNull(homeCriteria.getMinPrice())) homeCriteriaEntity.setMinimumPrice(homeCriteria.getMinPrice());
        if(Objects.nonNull(homeCriteria.getMaxPrice())) homeCriteriaEntity.setMaximumPrice(homeCriteria.getMaxPrice());
        if(Objects.nonNull(homeCriteria.getAmountOfBed())) homeCriteriaEntity.setAmountOfBeds(homeCriteria.getAmountOfBed());
        if(Objects.nonNull(homeCriteria.getAmountOfBath())) homeCriteriaEntity.setAmountOfBaths(homeCriteria.getAmountOfBath());
        if(Objects.nonNull(homeCriteria.getHouse())) homeCriteriaEntity.setHouse(homeCriteria.getHouse());
        if(Objects.nonNull(homeCriteria.getMultifamily())) homeCriteriaEntity.setMultifamily(homeCriteria.getMultifamily());
        if(Objects.nonNull(homeCriteria.getMultifamily())) homeCriteriaEntity.setMultifamily(homeCriteria.getMultifamily());
        if(Objects.nonNull(homeCriteria.getCondocoop())) homeCriteriaEntity.setCondocoop(homeCriteria.getCondocoop());
        if(Objects.nonNull(homeCriteria.getTownhome())) homeCriteriaEntity.setTownhome(homeCriteria.getTownhome());
        if(Objects.nonNull(homeCriteria.getBasement())) homeCriteriaEntity.setBasement(homeCriteria.getBasement());
        if(Objects.nonNull(homeCriteria.getCentralair())) homeCriteriaEntity.setCentralair(homeCriteria.getCentralair());
        if(Objects.nonNull(homeCriteria.getPool())) homeCriteriaEntity.setPool(homeCriteria.getPool());
        if(Objects.nonNull(homeCriteria.getWaterfront())) homeCriteriaEntity.setWaterfront(homeCriteria.getWaterfront());
        if(Objects.nonNull(homeCriteria.getCityOne())) homeCriteriaEntity.setCityOne(homeCriteria.getCityOne());
        if(Objects.nonNull(homeCriteria.getCityTwo())) homeCriteriaEntity.setCityTwo(homeCriteria.getCityTwo());
        if(Objects.nonNull(homeCriteria.getCityThree())) homeCriteriaEntity.setCityThree(homeCriteria.getCityThree());
        if(Objects.nonNull(homeCriteria.getCityFour())) homeCriteriaEntity.setCityFour(homeCriteria.getCityFour());
        if(Objects.nonNull(homeCriteria.getCityFive())) homeCriteriaEntity.setCityFive(homeCriteria.getCityFive());


        homeCriteriaEntity.setTransactionStatusType(TransactionStatusType.completed);//todo:remove this later on
        homeCriteriaRepository.save(homeCriteriaEntity);
        return fromEntity.fromHomeCriteriaEntity(homeCriteriaEntity);
    }

}
