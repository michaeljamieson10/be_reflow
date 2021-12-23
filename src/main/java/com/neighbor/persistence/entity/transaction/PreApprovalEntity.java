package com.neighbor.persistence.entity.transaction;

import com.neighbor.enums.LoanType;
import com.neighbor.enums.TransactionStatusType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pre_approval")
public class PreApprovalEntity{

    @Id
    @GeneratedValue(strategy=   GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JoinColumn(name="transaction_id")
    @OneToOne(optional = false)
    private TransactionEntity transactionEntity;

    @Column(name="status", columnDefinition = "ENUM('not_started', 'in_progress', 'completed')")
    @Enumerated(EnumType.STRING)
    private TransactionStatusType transactionStatusType = TransactionStatusType.in_progress;

    @NonNull
    @JoinColumn(name="max_purchase_price")
    private BigDecimal maxPurchasePrice;

    @NonNull
    @JoinColumn(name="max_loan_amount")
    private BigDecimal maxLoanAmount;

    @NonNull
    @JoinColumn(name="max_taxes")
    private BigDecimal maxTaxes;

    @NonNull
    @JoinColumn(name="down_payment")
    private BigDecimal downPayment;

    @Column(name="loan_type", columnDefinition = "ENUM('conventional', 'FHA', 'VA', 'USDA')")
    @Enumerated(EnumType.STRING)
    private LoanType loanType;


    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Timestamp createdTimestamp;


    @Column(name = "updated_timestamp")
    @UpdateTimestamp
    private Timestamp updatedTimestamp;




}
