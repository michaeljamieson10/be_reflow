package com.neighbor.component;
import com.neighbor.persistence.entity.UserEntity;

public interface AuthenticatedUserResolver {

    UserEntity user();

}
