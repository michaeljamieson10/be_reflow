package com.neighbor.persistence.repository.transaction;

import com.neighbor.persistence.entity.transaction.LoanCommitmentEntity;
import com.neighbor.persistence.entity.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LoanCommitmentRepository extends JpaRepository<LoanCommitmentEntity, Integer> {
    LoanCommitmentEntity findByTransactionEntity(TransactionEntity transactionEntity);
}
