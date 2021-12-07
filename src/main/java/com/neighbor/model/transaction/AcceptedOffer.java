package com.neighbor.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcceptedOffer {
    private int id;
    private Transaction transaction;
    private Timestamp createdTimetamp;
    private Timestamp updatedTimestamp;
}
