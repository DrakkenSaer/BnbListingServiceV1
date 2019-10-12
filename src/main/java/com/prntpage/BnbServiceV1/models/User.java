package com.prntpage.BnbServiceV1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@Document
public class User {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
}
