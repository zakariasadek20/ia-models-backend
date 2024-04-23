package com.models.ai.domain.base;

import com.models.ai.domain.common.exceptions.DomainException;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public abstract class Identity implements Serializable {

    protected Identity() {
        this.validate();
    }

    protected void validate() throws DomainException {}
}
