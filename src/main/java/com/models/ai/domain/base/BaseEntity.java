package com.models.ai.domain.base;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity<T extends Identity> implements Serializable {

    @EmbeddedId
    protected T id;
}
