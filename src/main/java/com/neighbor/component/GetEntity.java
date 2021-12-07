package com.neighbor.component;

import com.neighbor.exception.AgentNotFoundException;
import com.neighbor.exception.ClientNotFoundException;
import com.neighbor.exception.UserNotFoundException;
import com.neighbor.model.Agent;
import com.neighbor.model.Client;
import com.neighbor.model.User;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.repository.AgentRepository;
import com.neighbor.persistence.repository.ClientRepository;
import com.neighbor.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GetEntity {
    private final AuthenticatedUserResolver userResolver;
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Autowired
    public GetEntity(
            AuthenticatedUserResolver userResolver,
            AgentRepository agentRepository,
            ClientRepository clientRepository,
            UserRepository userRepository
    ) {
        this.userResolver = userResolver;
        this.agentRepository = agentRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    public ClientEntity getClientEntity(Client client) {
        if(Objects.isNull(client) || Objects.isNull(client.getId())) throw new ClientNotFoundException(0);
        ClientEntity clientEntity = clientRepository.findById(client.getId()).orElse(null);
        if(Objects.isNull(clientEntity)) throw new ClientNotFoundException(client.getId());
        return clientEntity;
    }

    public AgentEntity getAgentEntity(Agent agent) {
        if(Objects.isNull(agent) || Objects.isNull(agent.getId())) throw new AgentNotFoundException(0);
        AgentEntity agentEntity = agentRepository.findById(agent.getId()).orElse(null);
        if(Objects.isNull(agentEntity)) throw new AgentNotFoundException(agent.getId());
        return agentEntity;
    }
    public UserEntity getUserEntity(User user) {
        if(Objects.isNull(user) || Objects.isNull(user.getId())) throw new UserNotFoundException(0);
        UserEntity userEntity = userRepository.findById(user.getId()).orElse(null);
        if(Objects.isNull(userEntity)) throw new UserNotFoundException(user.getId());
        return userEntity;
    }

}
