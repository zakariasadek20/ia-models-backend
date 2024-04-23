package com.models.ai.domain.users.profiles;

import com.models.ai.domain.base.BaseEntity;
import com.models.ai.domain.users.profiles.commands.ProfileAddCommand;
import com.models.ai.domain.users.profiles.commands.ProfileUpdateCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "Profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@EqualsAndHashCode(
        callSuper = true,
        of = {})
public class Profile extends BaseEntity<ProfileId> {

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "niveau")
    private Long niveau;

    public Profile() {
        this.id = new ProfileId();
    }

    public static Profile from(ProfileAddCommand profileAddCommand) {
        Profile profile = new Profile();
        profile.code = profileAddCommand.getCode();
        profile.description = profileAddCommand.getDescription();
        profile.niveau = profileAddCommand.getNiveau();

        return profile;
    }

    public void update(ProfileUpdateCommand command) {
        this.description = command.getDescription();
        this.niveau = command.getNiveau();
    }
}
