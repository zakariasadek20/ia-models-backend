package com.models.ai.domain.users;

import com.models.ai.domain.base.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Embeddable
@EqualsAndHashCode(callSuper = false)
public class UserId extends Identity {

    @Column(name = "id")
    private String value;

    public UserId() {
        super();
        this.value = UUID.randomUUID().toString();
    }

    private UserId(String value) {
        super();
        this.value = value;
    }

    public static UserId from(String value) {
        return new UserId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
