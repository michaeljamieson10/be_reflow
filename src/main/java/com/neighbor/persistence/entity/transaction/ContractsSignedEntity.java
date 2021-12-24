package com.neighbor.persistence.entity.transaction;

import com.neighbor.enums.ContractsSignedStatusType;
import com.neighbor.enums.TransactionInvitationStatusType;
import com.neighbor.enums.TransactionStatusType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contracts_signed")
public class ContractsSignedEntity {

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

    @Column(name="buyer_status", columnDefinition = "ENUM('pending','accepted')")
    @Enumerated(EnumType.STRING)
    private ContractsSignedStatusType buyerStatus;

    @Column(name="seller_status", columnDefinition = "ENUM('pending','accepted')")
    @Enumerated(EnumType.STRING)
    private ContractsSignedStatusType sellerStatus;

    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Timestamp createdTimestamp;


    @Column(name = "updated_timestamp")
    @UpdateTimestamp
    private Timestamp updatedTimestamp;




}
