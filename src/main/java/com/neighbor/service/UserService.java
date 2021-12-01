package com.neighbor.service;

import com.neighbor.model.User;
import com.neighbor.model.UserRegistration;
import com.neighbor.persistence.entity.UserEntity;

public interface UserService {
    User get();

    User createNewUser(UserRegistration userRegistration);
}
