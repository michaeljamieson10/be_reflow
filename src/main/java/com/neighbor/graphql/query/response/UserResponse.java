package com.neighbor.graphql.query.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public UserResponse (User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();


    }
}
