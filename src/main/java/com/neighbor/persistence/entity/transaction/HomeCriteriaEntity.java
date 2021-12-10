package com.neighbor.persistence.entity.transaction;

import com.neighbor.enums.HomeCriteriaStatusType;
import com.neighbor.enums.PriceByHundredIncrementType;
import com.neighbor.enums.RoomAmountType;
import com.neighbor.enums.TransactionInvitationStatusType;
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
@Table(name = "home_criteria")
public class HomeCriteriaEntity {

    @Id
    @GeneratedValue(strategy=   GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JoinColumn(name="transaction_id")
    @OneToOne(optional = false)
    private TransactionEntity transactionEntity;

    @Column(name="status", columnDefinition = "ENUM('not_started', 'in_progress', 'completed')")
    @Enumerated(EnumType.STRING)
    private HomeCriteriaStatusType homeCriteriaStatusType = HomeCriteriaStatusType.in_progress;

    @Column(name="price_min", columnDefinition = "ENUM('one_hundred','two_hundred','three_hundred','four_hundred','five_hundred','six_hundred','seven_hundred','eight_hundred','nine_hundred','one_million')")
    @Enumerated(EnumType.STRING)
    private PriceByHundredIncrementType minimumPrice;

    @Column(name="price_max", columnDefinition = "ENUM('one_hundred','two_hundred','three_hundred','four_hundred','five_hundred','six_hundred','seven_hundred','eight_hundred','nine_hundred','one_million')")
    @Enumerated(EnumType.STRING)
    private PriceByHundredIncrementType maximumPrice;

    @Column(name="beds", columnDefinition = "ENUM('one', 'two', 'three', 'four', 'five')")
    @Enumerated(EnumType.STRING)
    private RoomAmountType amountOfBeds;

    @Column(name="bath", columnDefinition = "ENUM('one', 'two', 'three', 'four', 'five')")
    @Enumerated(EnumType.STRING)
    private RoomAmountType amountOfBaths;

    @Column(name = "house")
    private boolean house;

    @Column(name = "multifamily")
    private boolean multifamily;

    @Column(name = "condo_coop")
    private boolean condocoop;

    @Column(name = "townhome")
    private boolean townhome;

    @Column(name = "basement")
    private boolean basement;

    @Column(name = "central_air")
    private boolean centralair;

    @Column(name = "pool")
    private boolean pool;

    @Column(name = "waterfront")
    private boolean waterfront;

    @NonNull
    @Column(name = "city_one")
    private String cityOne;

    @NonNull
    @Column(name = "city_two")
    private String cityTwo;

    @NonNull
    @Column(name = "city_three")
    private String cityThree;

    @NonNull
    @Column(name = "city_four")
    private String cityFour;

    @NonNull
    @Column(name = "city_five")
    private String cityFive;

    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Timestamp createdTimestamp;


    @Column(name = "updated_timestamp")
    @UpdateTimestamp
    private Timestamp updatedTimestamp;




}
