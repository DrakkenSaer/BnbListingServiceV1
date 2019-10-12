package com.prntpage.BnbServiceV1.controllers;

import com.prntpage.BnbServiceV1.models.BnbUserRating;
import com.prntpage.BnbServiceV1.repositories.BnbUserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/bnbListings/{bnbId}/ratings")
public class BnbUserRatingController {

    @Autowired
    private BnbUserRatingRepository bnbUserRatingRepository;

    @GetMapping()
    public Flux<BnbUserRating> list() {
        return bnbUserRatingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<BnbUserRating> getById(@PathVariable final UUID id) {
        return bnbUserRatingRepository.findById(id);
    }

    @PostMapping()
    public Mono<BnbUserRating> create(@Valid @RequestBody BnbUserRating bnbUserRating) {
        return bnbUserRatingRepository.save(bnbUserRating);
    }


    // Streaming

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BnbUserRating> streamAll() {
        return bnbUserRatingRepository.findAll();
    }
}
