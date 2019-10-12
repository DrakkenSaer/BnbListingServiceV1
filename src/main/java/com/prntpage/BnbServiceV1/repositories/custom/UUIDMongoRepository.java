package com.prntpage.BnbServiceV1.repositories.custom;

import com.prntpage.BnbServiceV1.models.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UUIDMongoRepository<T extends BaseEntity> extends MongoRepository<T, Long> { }