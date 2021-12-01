package com.neighbor.service;


import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.repository.UserRepository;
import com.neighbor.persistence.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository,
                                  UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (isNull(userEntity)) {
            throw new UsernameNotFoundException(String.format("Email '%s' does not exist.", email));
        } else {
            return new UserRepositoryUserDetails(userEntity, userRoleRepository.findByUserEmail(userEntity.getEmail()));
        }
    }
}
