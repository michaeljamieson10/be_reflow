package com.neighbor.persistence.entity.transaction;

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
@Table(name = "accepted_offer")
public class AcceptedOfferEntity{

    @Id
    @GeneratedValue(strategy=   GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JoinColumn(name="transaction_id")
    @OneToOne(optional = false)
    private TransactionEntity transactionEntity;

    @NonNull
    @Column(name = "address")
    private String address;

    @NonNull
    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @NonNull
    @Column(name = "property_taxes")
    private BigDecimal propertyTaxes;

    @NonNull
    @JoinColumn(name="down_payment")
    private BigDecimal downPayment;

    @Column(name="status", columnDefinition = "ENUM('not_started', 'in_progress', 'completed')")
    @Enumerated(EnumType.STRING)
    private TransactionStatusType transactionStatusType = TransactionStatusType.in_progress;

    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Timestamp createdTimestamp;


    @Column(name = "updated_timestamp")
    @UpdateTimestamp
    private Timestamp updatedTimestamp;




}
