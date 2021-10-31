package com.neighbor.graphql.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    Order order;
    public OrderResponse(Order order) {
        this.order = order;
    }
}
