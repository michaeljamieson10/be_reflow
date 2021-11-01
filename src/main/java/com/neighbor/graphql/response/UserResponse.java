package com.neighbor.graphql.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.model.User;
import com.neighbor.persistence.entity.UserEntity;
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
    private UserEntity userEntity;

    private List<OrderResponse> orderResponseList;

    public UserResponse (UserEntity userEntity) {
//        this.user = user;
        this.userEntity = userEntity;
        this.id = userEntity.getId();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.email = userEntity.getEmail();


    }
}
