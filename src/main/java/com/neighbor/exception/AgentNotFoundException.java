package com.neighbor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AgentNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Agent with id '%s' was not found!";

    private final int id;

    public AgentNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, id);
    }
}
