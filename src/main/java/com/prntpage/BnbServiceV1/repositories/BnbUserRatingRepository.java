package com.prntpage.BnbServiceV1.repositories;

import com.prntpage.BnbServiceV1.models.BnbUserRating;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface BnbUserRatingRepository extends ReactiveMongoRepository<BnbUserRating, UUID> { }
