package com.neighbor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.persistence.entity.AgentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Agent {
    int id;
    User user;
    Boolean isActive;
    public static Agent fromAgentEntity(AgentEntity agentEntity) {
        return Agent.builder()
                .id(agentEntity.getId())
                .isActive(agentEntity.isActive())
                .user(User.builder().id(agentEntity.getUserEntity().getId()).build())
                .build();
    }

}
