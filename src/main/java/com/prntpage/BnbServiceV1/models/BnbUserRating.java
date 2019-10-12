package com.prntpage.BnbServiceV1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document
public class BnbUserRating {

    @Id
    private UUID id;
    private User user;
    private BnbListing listing;
    private Double rating;
    private String comment;
    private Date datetime = new Date();
}
