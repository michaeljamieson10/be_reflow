package com.neighbor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Client with id '%s' was not found!";

    private final int id;

    public ClientNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, id);
    }
}
