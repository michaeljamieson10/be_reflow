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
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy=   GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="client_id")
    @ManyToOne(optional = true)
    private ClientEntity clientEntity;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @JoinColumn(name="agent_id")
    @OneToOne(optional = false)
    private AgentEntity agentEntity;


    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Timestamp createdTimestamp;


    @Column(name = "updated_timestamp")
    @UpdateTimestamp
    private Timestamp updatedTimestamp;




}
