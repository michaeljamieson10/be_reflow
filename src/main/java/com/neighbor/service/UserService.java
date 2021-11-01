package com.neighbor.service;

import com.neighbor.model.User;
import com.neighbor.persistence.entity.UserEntity;

public interface UserService {
    UserEntity getUser(int id);

}
