package com.prntpage.BnbServiceV1.repositories;

import com.prntpage.BnbServiceV1.models.BnbListing;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface BnbListingRepository extends ReactiveMongoRepository<BnbListing, UUID> {
    Flux<BnbListing> findByUserId(UUID userId);
}
