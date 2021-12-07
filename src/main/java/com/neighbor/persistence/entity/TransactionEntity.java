package com.neighbor.persistence.entity;

import com.neighbor.enums.TransactionInvitationStatusType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy=   GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="client_id")
    @ManyToOne(optional = true)
    private ClientEntity clientEntity;

    @NonNull
    @JoinColumn(name="agent_id")
    @OneToOne(optional = false)
    private AgentEntity agentEntity;

    @Column(name="transaction_invitation", columnDefinition = "ENUM('pending', 'accepted')")
    @Enumerated(EnumType.STRING)
    private TransactionInvitationStatusType transactionInvitationStatusType = TransactionInvitationStatusType.pending;

    @Column(name = "accepted_timestamp")
    private Timestamp acceptedTimestamp;

    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Timestamp createdTimestamp;


    @Column(name = "updated_timestamp")
    @UpdateTimestamp
    private Timestamp updatedTimestamp;




}
