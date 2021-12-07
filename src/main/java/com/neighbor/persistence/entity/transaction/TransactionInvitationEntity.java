package com.neighbor.persistence.entity.transaction;

import com.neighbor.enums.TransactionInvitationStatusType;
import com.neighbor.persistence.entity.AgentEntity;
import com.neighbor.persistence.entity.ClientEntity;
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
@Table(name = "transaction_invitation")
public class TransactionInvitationEntity {

    @Id
    @GeneratedValue(strategy=   GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JoinColumn(name="client_id")
    @ManyToOne(optional = true)
    private ClientEntity clientEntity;

    @NonNull
    @JoinColumn(name="agent_id")
    @OneToOne(optional = false)
    private AgentEntity agentEntity;

    @NonNull
    @JoinColumn(name="transaction_id")
    @OneToOne(optional = false)
    private TransactionEntity transactionEntity;

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
