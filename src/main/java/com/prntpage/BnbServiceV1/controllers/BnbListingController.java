package com.prntpage.BnbServiceV1.controllers;

import com.prntpage.BnbServiceV1.models.BnbListing;
import com.prntpage.BnbServiceV1.repositories.BnbListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/bnbListings")
public class BnbListingController {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private BnbListingRepository bnbListingRepository;

    @GetMapping()
    public Flux<BnbListing> list() {
        return bnbListingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BnbListing>> getById(@PathVariable final UUID id) {
        return bnbListingRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Mono<BnbListing> create(@Valid @RequestBody BnbListing bnbListing) {
        return bnbListingRepository.save(bnbListing);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<BnbListing>> update(
            @PathVariable final UUID id,
            @Valid @RequestBody BnbListing bnbListing
    ) {
        return bnbListingRepository.findById(id)
                .flatMap(existingBnbListing -> {
                    existingBnbListing.update(bnbListing);
                    return bnbListingRepository.save(existingBnbListing);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable final UUID id) {
        return bnbListingRepository.findById(id)
                .flatMap(existingBnbListing ->
                        bnbListingRepository.delete(existingBnbListing)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    // Streaming

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BnbListing> streamAll() {
        return bnbListingRepository.findAll();
    }
}
