package com.models.ai.domain.users.usersprofiles;

import com.models.ai.domain.base.Identity;
import com.models.ai.domain.users.UserId;
import com.models.ai.domain.users.profiles.ProfileId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Embeddable
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserProfileId extends Identity {

    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "value", column = @Column(name = "user_id"))})
    private final UserId userId;

    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "value", column = @Column(name = "profile_id"))})
    private final ProfileId profileId;

    public UserProfileId() {
        userId = null;
        profileId = null;
    }
}
