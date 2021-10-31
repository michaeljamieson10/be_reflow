package com.neighbor.graphql.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    //this is for internal use do not put into schema
    private User user;

    private List<OrderResponse> orderResponseList;

    public UserResponse (User user) {
        this.user = user;
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();


    }
}
