package com.neighbor.persistence.repository;

import com.neighbor.persistence.entity.HomeownersInsuranceEntity;
import com.neighbor.persistence.entity.TransactionInvitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HomeownersInsuranceRepository extends JpaRepository<HomeownersInsuranceEntity, Integer> {
}
