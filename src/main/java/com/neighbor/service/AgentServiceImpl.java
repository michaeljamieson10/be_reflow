package com.neighbor.service;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.exception.EntityMissingParametersException;
import com.neighbor.exception.UserNotFoundException;
import com.neighbor.model.Agent;
import com.neighbor.model.User;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.repository.AgentRepository;
import com.neighbor.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AgentServiceImpl implements AgentService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final UserRepository userRepository;
    private final AgentRepository agentRepository;
    private final GetEntity getEntity;
    private final FromEntity fromEntity;

    @Autowired
    public AgentServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            UserRepository userRepository,
            AgentRepository agentRepository,
            GetEntity getEntity,
            FromEntity fromEntity
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
    }
    @Override
    public Agent createNewAgent(Agent agent) {
//        permissionsValidator.validateSystemAdministrator(authenticatedUserResolver.user());
        if(Objects.isNull(agent.getUser())) throw new EntityMissingParametersException(Agent.class, "user");
        UserEntity userEntity = getEntity.getUserEntity(agent.getUser());
        AgentEntity agentEntity = new AgentEntity();
        agentEntity.setUserEntity(userEntity);
        agentEntity.setActive(true);
        agentRepository.save(agentEntity);
        return fromEntity.fromAgentEntity(agentEntity);
    }

}
