package com.neighbor.component;

import com.neighbor.exception.AccessDeniedException;
import com.neighbor.model.Agent;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PermissionsValidator {

    private AuthenticatedUserResolver userResolver;
    private final AgentRepository agentRepository;

    @Autowired
    public PermissionsValidator(
            AuthenticatedUserResolver userResolver,
            AgentRepository agentRepository
    ) {
        this.userResolver = userResolver;
        this.agentRepository = agentRepository;
    }

    public void validateSystemAdministrator(UserEntity callingUserEntity){
        if(!callingUserEntity.isSystemAdministrator()) throw new AccessDeniedException();
    }

    public void validateAgent(UserEntity callingUserEntity){
        AgentEntity agentEntity = agentRepository.findByUserEntity(callingUserEntity);
        if(Objects.isNull(agentEntity)) throw new AccessDeniedException();
    }
    public void validateAgentOrSystemAdmin(UserEntity callingUserEntity){
        if(!callingUserEntity.isSystemAdministrator() ||
                Objects.isNull(agentRepository.findByUserEntity(callingUserEntity)))
            throw new
                    AccessDeniedException();
    }

}
