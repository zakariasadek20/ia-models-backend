package com.models.ai.domain.users.profiles;

import com.models.ai.domain.base.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Embeddable
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProfileId extends Identity {

    @Column(name = "id")
    private final String value;

    public ProfileId() {
        super();
        this.value = UUID.randomUUID().toString();
    }

    private ProfileId(String value) {
        super();
        this.value = value;
    }

    public static ProfileId from(String value) {
        return new ProfileId(value);
    }
}
