package com.neighbor.service.Transaction;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.persistence.repository.ClientRepository;
import com.neighbor.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreApprovalServiceImpl implements PreApprovalService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    @Autowired
    public PreApprovalServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            UserRepository userRepository,
            ClientRepository clientRepository,
            FromEntity fromEntity,
            GetEntity getEntity
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
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
