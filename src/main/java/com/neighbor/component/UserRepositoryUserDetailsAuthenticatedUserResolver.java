package com.neighbor.component;

import com.neighbor.exception.InvalidTokenException;
import com.neighbor.persistence.entity.UserEntity;
import com.neighbor.service.UserRepositoryUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class UserRepositoryUserDetailsAuthenticatedUserResolver implements AuthenticatedUserResolver {

    @Override
    public final UserEntity user() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication;
        if (nonNull((authentication = securityContext.getAuthentication()))
                && authentication.isAuthenticated()
                && (authentication.getPrincipal() instanceof UserRepositoryUserDetails)) {
                return ((UserRepositoryUserDetails) authentication.getPrincipal()).getUserEntity();
        } else {
            throw new InvalidTokenException("Access token is either missing or invalid.");
        }
    }

}
