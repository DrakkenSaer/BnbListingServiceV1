package com.prntpage.BnbServiceV1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document
public class BnbListing {

    @Id
    private UUID id;
    private UUID userId;
    private String title;
    private String description;
    private String address;
    private String listType;
    private String buildingType;
    private Double price;
    private String payFrequency;
    private Double cleaningFee;
    private Double totalBathrooms;
    private Integer totalBedrooms;
    private List<BnbRoom> bedrooms;
    private Integer maxGuests;
    private Double checkIn; // 24.00HR format
    private Double checkOut; // 24.00HR format
    private String checkInType; // Keypad, host, etc
    private List<String> services; // Breakfast, massage, etc
    private List<String> stayAmenities;
    private List<String> accessibilityAmenities;
    private List<String> safetyAmenities;
    private List<BnbUserRating> ratings;

    public void update(BnbListing bnbListing) {
        if(title != null) {
            this.setTitle(bnbListing.getTitle());
        }
    }
}
