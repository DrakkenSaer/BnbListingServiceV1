package com.prntpage.BnbServiceV1.models;

import org.springframework.data.domain.Persistable;

import java.util.UUID;

public interface BaseEntity extends Persistable<UUID> {
    UUID getId();
    void setId(UUID id);
}
