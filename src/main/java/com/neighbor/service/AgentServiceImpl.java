package com.neighbor.service;

import com.neighbor.component.AuthenticatedUserResolver;
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

    @Autowired
    public AgentServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            UserRepository userRepository,
            AgentRepository agentRepository
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
    }
    @Override
    public Agent createNewAgent(Agent agent) {
        permissionsValidator.validateSystemAdministrator(authenticatedUserResolver.user());
        if(Objects.isNull(agent.getUser())) throw new EntityMissingParametersException(Agent.class, "user");
        UserEntity userEntity = userRepository.findById(agent.getUser().getId()).orElse(null);
        if(Objects.isNull(userEntity)) throw new UserNotFoundException("");
        AgentEntity agentEntity = new AgentEntity();
        agentEntity.setUserEntity(userEntity);
        agentEntity.setActive(true);
        agentRepository.save(agentEntity);
        return fromEntity(agentEntity);
    }

    private Agent fromEntity(AgentEntity agentEntity) {
        return Agent.builder()
                .id(agentEntity.getId())
                .isActive(agentEntity.isActive())
                .user(User.builder().id(agentEntity.getUserEntity().getId()).build())
                .build();
    }
}
