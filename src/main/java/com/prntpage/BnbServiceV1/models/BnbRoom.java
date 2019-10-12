package com.prntpage.BnbServiceV1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class BnbRoom {
    private String bedSize; // Queen, single, twin
    private String bedType; // Mattress, couch
}
