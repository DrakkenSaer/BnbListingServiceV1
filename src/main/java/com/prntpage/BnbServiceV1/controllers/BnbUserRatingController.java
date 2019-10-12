package com.prntpage.BnbServiceV1.controllers;

import com.prntpage.BnbServiceV1.models.BnbUserRating;
import com.prntpage.BnbServiceV1.repositories.BnbUserRatingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/bnbListings/{bnbId}/ratings")
public class BnbUserRatingController {

    private BnbUserRatingRepository bnbUserRatingRepository;

    public BnbUserRatingController(BnbUserRatingRepository bnbUserRatingRepository) {
        this.bnbUserRatingRepository = bnbUserRatingRepository;
    }

    @GetMapping()
    public Flux<BnbUserRating> list() {
        return bnbUserRatingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<BnbUserRating> getById(@PathVariable final UUID id) {
        return bnbUserRatingRepository.findById(id);
    }
}
