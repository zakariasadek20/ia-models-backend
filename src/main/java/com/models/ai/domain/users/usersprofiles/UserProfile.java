package com.models.ai.domain.users.usersprofiles;

import com.models.ai.domain.base.BaseAuditedEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "Users_Profiles")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserProfile extends BaseAuditedEntity<UserProfileId> {

    public UserProfile(UserProfileId userProfileId) {
        this.id = userProfileId;
    }
}
