package com.neighbor.restservice.persistence.entity;

import com.neighbor.model.Address;
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

//    private int id;
//    private String email;
//    private String password;
//    private String firstName;
//    private String lastName;
//    private Address address;
//    @NonNull
//    @Column(name = "phone")
//    private String phoneNumber;
//
//    @Column(name = "date_of_birth")
//    private Timestamp dateOfBirth;
//
//    @Column(name = "is_phone_validated")
//    private boolean phoneNumberValidated;
//
//    @Column(name = "signup_zipcode")
//    private String signupZipCode;
//
//    @Column(name = "is_system_administrator")
//    private boolean systemAdministrator;
//
//    @Column
//    private boolean enabled;

}
