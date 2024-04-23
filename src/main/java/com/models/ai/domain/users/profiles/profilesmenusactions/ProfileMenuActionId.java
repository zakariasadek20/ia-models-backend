package com.models.ai.domain.users.profiles.profilesmenusactions;

import com.models.ai.domain.base.Identity;
import com.models.ai.domain.users.menus.menusactions.MenuActionId;
import com.models.ai.domain.users.profiles.ProfileId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Embeddable
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProfileMenuActionId extends Identity {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "profile_id"))})
    private final ProfileId profileId;

    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "value", column = @Column(name = "menu_action_id"))})
    private final MenuActionId menuActionId;

    public ProfileMenuActionId() {
        profileId = null;
        menuActionId = null;
    }
}
