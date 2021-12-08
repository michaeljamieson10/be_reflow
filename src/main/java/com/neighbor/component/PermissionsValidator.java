package com.neighbor.component;

import com.neighbor.exception.AccessDeniedException;
import com.neighbor.model.Agent;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import com.neighbor.persistence.repository.AgentRepository;
import com.neighbor.persistence.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PermissionsValidator {

    private AuthenticatedUserResolver userResolver;
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public PermissionsValidator(
            AuthenticatedUserResolver userResolver,
            AgentRepository agentRepository,
            ClientRepository clientRepository
    ) {
        this.userResolver = userResolver;
        this.agentRepository = agentRepository;
        this.clientRepository = clientRepository;
    }

    public void validateSystemAdministrator(UserEntity callingUserEntity){
        if(!callingUserEntity.isSystemAdministrator()) throw new AccessDeniedException();
    }

    public void validateAgent(UserEntity callingUserEntity){
        AgentEntity agentEntity = agentRepository.findByUserEntity(callingUserEntity);
        if(Objects.isNull(agentEntity)) throw new AccessDeniedException();
    }
    public void validateAgentOrSystemAdmin(UserEntity callingUserEntity){
//        AgentEntity agentEntity = agentRepository.findByUserEntity(callingUserEntity);
        if(!callingUserEntity.isSystemAdministrator() || Objects.isNull(agentRepository.findByUserEntity(callingUserEntity))) throw new AccessDeniedException();
    }
    public void validateAgentOrSystemAdminOrClient(UserEntity callingUserEntity){
        if(!callingUserEntity.isSystemAdministrator() ||
                Objects.isNull(agentRepository.findByUserEntity(callingUserEntity)) ||
                Objects.isNull(clientRepository.findByUserEntity(callingUserEntity))
        )
            throw new
                    AccessDeniedException();
    }
    public void validateCorrectUserEntity(UserEntity userEntity1, UserEntity userEntity2){
        Integer userId1 = userEntity1.getId();
        Integer userId2 = userEntity2.getId();
        if(!userId1.equals(userId2)) throw new AccessDeniedException();
    }

}
