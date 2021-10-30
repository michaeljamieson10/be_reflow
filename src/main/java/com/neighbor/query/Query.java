package com.neighbor.query;



import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.neighbor.model.User;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    public String firstQuery(){
        return "first query";
    }
    public String secondQuery(){
        return "second query";
    }
    public String fullName (String firstName, String lastName){
        return firstName + " " +lastName;
    }

    public String fullNameUser (User user){
        return user.getFirstName() + " " + user.getLastName();
//                + "Address street: " + user.getDefaultAddress().getStreet();
    }
}
