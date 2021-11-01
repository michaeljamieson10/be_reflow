package com.neighbor.graphql.resolver;


import com.coxautodev.graphql.tools.GraphQLResolver;
import com.neighbor.graphql.response.OrderResponse;
import com.neighbor.graphql.response.UserResponse;
import com.neighbor.model.Order;
import com.neighbor.persistence.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserResponseResolver implements GraphQLResolver<UserResponse> {
    public List<OrderResponse> getOrders (UserResponse userResponse){
        //graphql will call this automaticall you will not call this call
        List<OrderResponse> orders = new ArrayList<OrderResponse>();

        if(userResponse.getUserEntity().getOrderEntities() != null){
            for(OrderEntity orderEntity: userResponse.getUserEntity().getOrderEntities()){
                orders.add(new OrderResponse(orderEntity));
            }
        }

        return orders;
    }

}
