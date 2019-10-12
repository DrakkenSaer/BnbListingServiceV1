package com.prntpage.BnbServiceV1.repositories;

import com.prntpage.BnbServiceV1.models.BnbUserRating;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BnbUserRatingRepository extends ReactiveMongoRepository<BnbUserRating, UUID> { }
