package com.neighbor.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=   GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String email;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

}
