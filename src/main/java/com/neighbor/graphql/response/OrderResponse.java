package com.neighbor.graphql.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.model.Order;
import com.neighbor.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    Order order;
    int id;
    String name;
    User orderedUser;

    public OrderResponse(Order order) {
        this.order = order;
        this.id = order.getId();
        this.name = order.getName();
        this.orderedUser = order.getOrderedUser();
    }
}
