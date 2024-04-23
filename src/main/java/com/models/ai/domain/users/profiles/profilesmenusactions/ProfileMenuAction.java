package com.models.ai.domain.users.profiles.profilesmenusactions;

import com.models.ai.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "Profile_Menu_Action")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProfileMenuAction extends BaseEntity<ProfileMenuActionId> {

    public ProfileMenuAction(ProfileMenuActionId profileMenuActionId) {
        this.id = profileMenuActionId;
    }
}
