package com.prntpage.BnbServiceV1.controllers;

import com.prntpage.BnbServiceV1.models.BnbListing;
import com.prntpage.BnbServiceV1.models.BnbRoom;
import com.prntpage.BnbServiceV1.repositories.BnbListingRepository;
import com.prntpage.BnbServiceV1.utils.JwtTokenUtil;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BnbListingControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BnbListingRepository bnbListingRepository;

    private final BnbListing bnbListingObj = new BnbListing(
            UUID.randomUUID(),
            "Title",
            "Description",
            "Address",
            "ListType",
            "BuildingType",
            100.50,
            10.0,
            4.0,
            10,
            List.of(new BnbRoom("Queen", "Bunkbed")),
            10,
            9.25,
            10.50,
            "Keypad",
            List.of("Breakfast"),
            List.of("A/C", "TV"),
            List.of("Stairs"),
            List.of(),
            List.of()
    );

    @Test
    public void testCreate() {
        final String jwtToken = jwtTokenUtil.generateJwtBuilder("test:test");

        webTestClient.post().uri("/bnbListings")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(bnbListingObj), BnbListing.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.title").isEqualTo("Title");
    }

    @Test
    public void testGetAll() {
        final String jwtToken = jwtTokenUtil.generateJwtBuilder("test:test");

        webTestClient.get().uri("/bnbListings")
                .header("Authorization", "Bearer " + jwtToken)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(BnbListing.class);
    }

    @Test
    public void testGetById() {
        final String jwtToken = jwtTokenUtil.generateJwtBuilder("test:test");
        final BnbListing bnbListing = bnbListingRepository.save(bnbListingObj).block();

        webTestClient.get()
                .uri("/bnbListings/{id}", Collections.singletonMap("id", bnbListing.getId()))
                .header("Authorization", "Bearer " + jwtToken)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testUpdate() {
        final String jwtToken = jwtTokenUtil.generateJwtBuilder("test:test");
        final BnbListing bnbListing = bnbListingRepository.save(bnbListingObj).block();
        final BnbListing newBnbListing = new BnbListing(
                UUID.randomUUID(),
                "New Title",
                null,
                "New Address",
                "ListType",
                null,
                10.0,
                null,
                0.0,
                null,
                List.of(new BnbRoom("Queen", "Bunkbed"), new BnbRoom("Twin", "Bunkbed")),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        webTestClient.put()
                .uri("/bnbListings/{id}", Collections.singletonMap("id", bnbListing.getId()))
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newBnbListing), BnbListing.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.title").isEqualTo(newBnbListing.getTitle())
                .jsonPath("$.address").isEqualTo(newBnbListing.getAddress())
                .jsonPath("$.totalBathrooms").isEqualTo(newBnbListing.getTotalBathrooms());

    }

    @Test
    public void testDelete() {
        final String jwtToken = jwtTokenUtil.generateJwtBuilder("test:test");
        final BnbListing bnbListing = bnbListingRepository.save(bnbListingObj).block();

        webTestClient.delete()
                .uri("/bnbListings/{id}", Collections.singletonMap("id",  bnbListing.getId()))
                .header("Authorization", "Bearer " + jwtToken)
                .exchange()
                .expectStatus().isOk();
    }
}
