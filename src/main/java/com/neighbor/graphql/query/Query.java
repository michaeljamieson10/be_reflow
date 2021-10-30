package com.neighbor.graphql.query;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.neighbor.graphql.query.request.UserRequest;
import com.neighbor.graphql.query.response.UserResponse;
import com.neighbor.model.User;
import com.neighbor.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    UserService userService;

    public String firstQuery(){
        return "first query";
    }
    public String secondQuery(){
        return "second query";
    }
    public String fullName (String firstName, String lastName){
        return firstName + " " +lastName;
    }

    public String fullNameUser (UserRequest userRequest){
        return userRequest.getFirstName() + " " + userRequest.getLastName();
    }
    public UserResponse user (int id){
        return  new UserResponse(userService.getUser(id));
    }
}
