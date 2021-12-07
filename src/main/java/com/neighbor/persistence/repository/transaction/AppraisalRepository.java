package com.neighbor.persistence.repository.transaction;

import com.neighbor.persistence.entity.transaction.AppraisalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AppraisalRepository extends JpaRepository<AppraisalEntity, Integer> {
}
