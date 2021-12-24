package com.neighbor.persistence.repository.transaction;

import com.neighbor.persistence.entity.transaction.HomeInspectionEntity;
import com.neighbor.persistence.entity.transaction.PreApprovalEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HomeInspectionRepository extends JpaRepository<HomeInspectionEntity, Integer> {
    HomeInspectionEntity findByTransactionEntity(TransactionEntity transactionEntity);
}
