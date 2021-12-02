package com.neighbor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException {
    public static final String MESSAGE = "User '%s' was not found!";

    private final String userName;

    public UserNotFoundException(String userName) {
        this.userName = userName;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, userName);
    }
}
