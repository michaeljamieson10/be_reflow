package com.neighbor.persistence.entity;

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
@Table(name = "delivery_service_provider_manager", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","delivery_service_provider_id"})
})
public class AgentEntity {

    @Id
    @GeneratedValue(strategy=   GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JoinColumn(name = "user_id")
    @ManyToOne(optional = false)
    private UserEntity userEntity;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Timestamp createdTimestamp;

    @Column(name = "updated_timestamp")
    @UpdateTimestamp
    private Timestamp updatedTimestamp;

}
