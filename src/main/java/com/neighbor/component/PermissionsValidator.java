package com.neighbor.component;

import com.neighbor.exception.AccessDeniedException;
import com.neighbor.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PermissionsValidator {

    private AuthenticatedUserResolver userResolver;

    @Autowired
    public PermissionsValidator(AuthenticatedUserResolver userResolver) {

        this.userResolver = userResolver;
    }

    public void validateSystemAdministrator(UserEntity callingUserEntity){
        if(!callingUserEntity.isSystemAdministrator()) throw new AccessDeniedException();
    }

}
