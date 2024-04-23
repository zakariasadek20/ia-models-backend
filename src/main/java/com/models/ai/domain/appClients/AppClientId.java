package com.models.ai.domain.appClients;

import com.models.ai.domain.base.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Embeddable
@EqualsAndHashCode(callSuper = false)
public class AppClientId extends Identity {

    @Column(name = "id")
    private String value;

    public AppClientId() {
        super();
        this.value = UUID.randomUUID().toString();
    }

    private AppClientId(String value) {
        super();
        this.value = value;
    }

    public static AppClientId from(String value) {
        return new AppClientId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
