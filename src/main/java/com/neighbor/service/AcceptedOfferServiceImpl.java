package com.neighbor.service;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.component.PermissionsValidator;
import com.neighbor.persistence.repository.ClientRepository;
import com.neighbor.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcceptedOfferServiceImpl implements AcceptedOfferService {

    private final PermissionsValidator permissionsValidator;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    private final FromEntity fromEntity;
    private final GetEntity getEntity;

    @Autowired
    public AcceptedOfferServiceImpl(
            PermissionsValidator permissionsValidator,
            AuthenticatedUserResolver authenticatedUserResolver,
            FromEntity fromEntity,
            GetEntity getEntity
            ){
        this.permissionsValidator = permissionsValidator;
        this.authenticatedUserResolver = authenticatedUserResolver;
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
