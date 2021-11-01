package com.neighbor.service;

import com.neighbor.model.User;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(int id) {
        UserEntity userEntity = userRepository.findById(id);
        return User.builder().id(userEntity.getId()).email(userEntity.getEmail()).firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).build();
    }
}
