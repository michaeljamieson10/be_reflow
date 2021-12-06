package com.neighbor.service;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.exception.EntityMissingParametersException;
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
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            UserRepository userRepository,
            ClientRepository clientRepository
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Client get(Client client) {
        permissionsValidator.validateAgentOrSystemAdmin(authenticatedUserResolver.user());
        ClientEntity clientEntity = clientRepository.findById(client.getId()).orElse(null);
        return fromEntity(clientEntity);
    }

    @Override
    public Client createNewClient(Client client) {
//        permissionsValidator.validateAgentOrSystemAdmin(authenticatedUserResolver.user());
        UserEntity userEntity = getUserEntity(client.getUser());
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setUserEntity(userEntity);
        clientEntity.setActive(true);
        clientRepository.save(clientEntity);
        return fromEntity(clientEntity);
    }

    private Client fromEntity(ClientEntity clientEntity) {
        return Client.builder()
                .id(clientEntity.getId())
                .isActive(clientEntity.isActive())
                .user(User.builder().id(clientEntity.getUserEntity().getId()).build())
                .build();
    }

    private UserEntity getUserEntity(User user) {
        if(Objects.isNull(user)) throw new EntityMissingParametersException(Client.class, "user");
        UserEntity userEntity = userRepository.findById(user.getId()).orElse(null);
        if(Objects.isNull(userEntity)) throw new UserNotFoundException("");
        return userEntity;
    }

}
