package com.prntpage.BnbServiceV1.repositories.custom;

import com.prntpage.BnbServiceV1.models.BaseEntity;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;
import java.util.UUID;

public class UUIDMongoRepositoryImpl<T extends BaseEntity> extends SimpleMongoRepository<T, Long> implements UUIDMongoRepository<T> {

    UUIDMongoRepositoryImpl(
            MongoEntityInformation<T, Long> entityInformation,
            MongoOperations mongoOperations
    ) {
        super(entityInformation, mongoOperations);
    }

    @Override
    public <S extends T> S insert(S entity) {
        generateId(entity);
        return super.insert(entity);
    }

    @Override
    public <S extends T> List<S> insert(Iterable<S> entities) {
        for (S entity : entities) {
            generateId(entity);
        }
        return super.insert(entities);
    }

    @Override
    public <S extends T> S save(S entity) {
        generateId(entity);
        return super.save(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            generateId(entity);
        }
        return super.saveAll(entities);
    }

    protected <S extends T> void generateId(S entity) {
        if (!entity.isNew()) {
            return;
        }
        UUID id = UUID.randomUUID();
        entity.setId(id);
    }
}