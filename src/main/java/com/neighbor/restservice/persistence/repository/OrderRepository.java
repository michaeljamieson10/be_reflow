package com.neighbor.restservice.persistence.repository;

import com.neighbor.restservice.persistence.entity.OrderEntity;
import com.neighbor.restservice.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<OrderRepository, Integer>, JpaSpecificationExecutor<OrderEntity> {

}
