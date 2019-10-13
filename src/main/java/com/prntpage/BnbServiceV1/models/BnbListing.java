package com.prntpage.BnbServiceV1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BnbListing implements BaseEntity {

    @Id
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private String address;

    @NotNull
    private String listType;
    private String buildingType;

    @NotNull
    private Double price;
    private Double cleaningFee;
    private Double totalBathrooms;
    private Integer totalBedrooms;
    private List<BnbRoom> bedrooms = new ArrayList<>();
    private Integer maxGuests;
    private Double checkIn; // 24.00HR format
    private Double checkOut; // 24.00HR format
    private String checkInType; // Keypad, host, etc
    // Breakfast, massage, etc
    private List<String> services = new ArrayList<>();
    private List<String> stayAmenities = new ArrayList<>();
    private List<String> accessibilityAmenities = new ArrayList<>();
    private List<String> safetyAmenities = new ArrayList<>();
    private List<BnbUserRating> ratings = new ArrayList<>();

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @Version
    private Long version;

    public BnbListing(
            UUID userId,
            String title,
            String description,
            String address,
            String listType,
            String buildingType,
            Double price,
            Double cleaningFee,
            Double totalBathrooms,
            Integer totalBedrooms,
            List<BnbRoom> bedrooms,
            Integer maxGuests,
            Double checkIn,
            Double checkOut,
            String checkInType,
            List<String> services,
            List<String> stayAmenities,
            List<String> accessibilityAmenities,
            List<String> safetyAmenities,
            List<BnbUserRating> ratings
    ) {
        this.setUserId(userId);
        this.setTitle(title);
        this.setDescription(description);
        this.setAddress(address);
        this.setListType(listType);
        this.setBuildingType(buildingType);
        this.setPrice(price);
        this.setCleaningFee(cleaningFee);
        this.setTotalBathrooms(totalBathrooms);
        this.setTotalBedrooms(totalBedrooms);
        this.setBedrooms(bedrooms);
        this.setMaxGuests(maxGuests);
        this.setCheckIn(checkIn);
        this.setCheckOut(checkOut);
        this.setCheckInType(checkInType);
        this.setServices(services);
        this.setStayAmenities(stayAmenities);
        this.setAccessibilityAmenities(accessibilityAmenities);
        this.setSafetyAmenities(safetyAmenities);
        this.setRatings(ratings);
    }

    @Override
    public boolean isNew() {
        return this.getId() == null;
    }

    public void update(BnbListing bnbListing) {
        if (bnbListing.getTitle() != null) {
            this.setTitle(bnbListing.getTitle());
        }
        if (bnbListing.getAddress() != null) {
            this.setAddress(bnbListing.getAddress());
        }
        if (bnbListing.getListType() != null) {
            this.setListType(bnbListing.getListType());
        }
        if (bnbListing.getBuildingType() != null) {
            this.setBuildingType(bnbListing.getBuildingType());
        }
        if (bnbListing.getPrice() != null) {
            this.setPrice(bnbListing.getPrice());
        }
        if (bnbListing.getCleaningFee() != null) {
            this.setCleaningFee(bnbListing.getCleaningFee());
        }
        if (bnbListing.getTotalBathrooms() != null) {
            this.setTotalBathrooms(bnbListing.getTotalBathrooms());
        }
        if (bnbListing.getTotalBedrooms() != null) {
            this.setTotalBedrooms(bnbListing.getTotalBedrooms());
        }
        if (bnbListing.getBedrooms() != null) {
            this.setBedrooms(bnbListing.getBedrooms());
        }
        if (bnbListing.getMaxGuests() != null) {
            this.setMaxGuests(bnbListing.getMaxGuests());
        }
        if (bnbListing.getCheckIn() != null) {
            this.setCheckIn(bnbListing.getCheckIn());
        }
        if (bnbListing.getCheckOut() != null) {
            this.setCheckOut(bnbListing.getCheckOut());
        }
        if (bnbListing.getCheckInType() != null) {
            this.setCheckInType(bnbListing.getCheckInType());
        }
        if (bnbListing.getServices() != null) {
            this.setServices(bnbListing.getServices());
        }
        if (bnbListing.getStayAmenities() != null) {
            this.setStayAmenities(bnbListing.getStayAmenities());
        }
        if (bnbListing.getAccessibilityAmenities() != null) {
            this.setAccessibilityAmenities(bnbListing.getAccessibilityAmenities());
        }
        if (bnbListing.getSafetyAmenities() != null) {
            this.setSafetyAmenities(bnbListing.getSafetyAmenities());
        }
    }
}
