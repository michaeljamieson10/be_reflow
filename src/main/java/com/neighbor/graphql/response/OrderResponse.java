package com.neighbor.graphql.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.model.Order;
import com.neighbor.model.User;
import com.neighbor.persistence.entity.OrderEntity;
import com.neighbor.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    OrderEntity orderEntity;
    int id;
    String name;
    UserEntity orderedUser;

    public OrderResponse(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
        this.id = orderEntity.getId();
        this.name = orderEntity.getName();
        this.orderedUser = orderEntity.getUserEntity();
    }
}
