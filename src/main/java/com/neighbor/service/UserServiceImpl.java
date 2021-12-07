package com.neighbor.service;

import com.neighbor.component.AuthenticatedUserResolver;
import com.neighbor.component.FromEntity;
import com.neighbor.component.GetEntity;
import com.neighbor.exception.EmailAlreadyExistsException;
import com.neighbor.exception.EntityMissingParametersException;
import com.neighbor.model.User;
import com.neighbor.model.UserRegistration;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.persistence.entity.UserRoleEntity;
import com.neighbor.persistence.repository.UserRepository;
import com.neighbor.persistence.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utilities.UserRoles;

import java.util.Objects;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final AuthenticatedUserResolver authenticatedUserResolver;
    private final PasswordEncoder passwordEncoder;
    private final GetEntity getEntity;
    private final FromEntity fromEntity;


    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            AuthenticatedUserResolver authenticatedUserResolver,
            PasswordEncoder passwordEncoder,
            UserRoleRepository userRoleRepository,
            GetEntity getEntity,
            FromEntity fromEntity

            ){
        this.authenticatedUserResolver = authenticatedUserResolver;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.getEntity = getEntity;
        this.fromEntity = fromEntity;
    }

    @Override
    public User get() {
        UserEntity userEntity = authenticatedUserResolver.user();
        return fromEntity.fromUserEntity(userRepository.findById(userEntity.getId()).orElse(null));
    }

    @Override
    @Transactional
    public User createNewUser(UserRegistration userRegistration) {
        User user = userRegistration.getUser();
        if(Objects.isNull(user)) throw new EntityMissingParametersException(UserRegistration.class, "user");
        if(Objects.isNull(user.getEmail())) throw new EntityMissingParametersException(User.class, "email");
        return createUser(userRegistration);
    }
    @Transactional
    protected User createUser(UserRegistration userRegistration) {
        User user = userRegistration.getUser();
        String email = user.getEmail().trim();
        String phoneNumber = user.getPhoneNumber();
        if (ofNullable(userRepository.findByEmail((email))).isPresent()) {
            throw new EmailAlreadyExistsException(email);
        }
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            userEntity.setBCryptEncodedPassword(passwordEncoder.encode(user.getPassword()));
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setPhoneNumber(user.getPhoneNumber());
            userEntity.setEnabled(true);
            userEntity.setSystemAdministrator(false);
            userRepository.save(userEntity);

            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserEmail(email);
            userRoleEntity.setRole(UserRoles.USER_ROLE);
            userRoleRepository.save(userRoleEntity);


        return fromEntity.fromUserEntity(userEntity);

    }



}
