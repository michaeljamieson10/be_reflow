package com.neighbor.persistence.repository.transaction;

import com.neighbor.persistence.entity.transaction.ClosingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClosingRepository extends JpaRepository<ClosingEntity, Integer> {
}
