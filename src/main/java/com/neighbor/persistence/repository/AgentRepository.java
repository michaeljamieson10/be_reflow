package com.neighbor.persistence.repository;

import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AgentRepository extends JpaRepository<AgentEntity, Integer> {
    AgentEntity findByUserEntity(UserEntity userEntity);
}

