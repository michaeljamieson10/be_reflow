package com.neighbor.persistence.entity.transaction;

import com.neighbor.enums.HomeInspectionStatusType;
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
@Table(name = "home_inspection")
public class HomeInspectionEntity {

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

    @Column(name="inspection_status", columnDefinition = "ENUM('complete_moving_to_contracts', 'complete_not_moving_to_contracts', 'no_inspection')")
    @Enumerated(EnumType.STRING)
    private HomeInspectionStatusType homeInspectionStatusType;

    @Column(name = "scheduled_date_time")
    private Timestamp scheduledDateTime;

    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Timestamp createdTimestamp;

    @Column(name = "updated_timestamp")
    @UpdateTimestamp
    private Timestamp updatedTimestamp;




}
