package com.neighbor.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbor.enums.PriceByHundredIncrementType;
import com.neighbor.enums.RoomAmountType;
import com.neighbor.enums.TransactionStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeCriteria {
    private int id;
    private Transaction transaction;
    private TransactionStatusType transactionStatusType;
    private PriceByHundredIncrementType minPrice;
    private PriceByHundredIncrementType maxPrice;
    private RoomAmountType amountOfBed;
    private RoomAmountType amountOfBath;
    private Boolean house;
    private Boolean multifamily;
    private Boolean condocoop;
    private Boolean townhome;
    private Boolean basement;
    private Boolean centralair;
    private Boolean pool;
    private Boolean waterfront;
    private String cityOne;
    private String cityTwo;
    private String cityThree;
    private String cityFour;
    private String cityFive;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;

}
