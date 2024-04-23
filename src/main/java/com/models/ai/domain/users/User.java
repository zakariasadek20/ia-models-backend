package com.models.ai.domain.users;

import com.google.common.base.Strings;
import com.models.ai.domain.base.BaseAuditedEntity;
import com.models.ai.domain.users.Commands.SignUpCommand;
import com.models.ai.domain.users.Commands.UserAddCommand;
import com.models.ai.domain.users.Commands.UserUpdateCommand;
import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.domain.users.profiles.ProfileId;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@EqualsAndHashCode(
        callSuper = true,
        of = {})
public class User extends BaseAuditedEntity<UserId> implements UserDetails {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "password_expiry_date")
    private LocalDateTime passwordExpiryDate;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserType type;

    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "value", column = @Column(name = "current_profile"))})
    private ProfileId currentProfileId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name_ar")
    private String firstNameAr;

    @Column(name = "last_name_ar")
    private String lastNameAr;

    @Column(name = "email")
    private String email;

    @Column(name = "nationalite")
    private String nationalite;

    @Column(name = "indicatif_pays")
    private String indicatifPays;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "piece_identite")
    private String pieceIdentite;

    @Column(name = "mre")
    private String mre;

    @Column(name = "adresse_residence")
    private String adresseResidence;

    @Column(name = "pays_residence")
    private String paysResidence;

    @Column(name = "province_residence")
    private String provinceResidence;

    @Column(name = "region_residence")
    private String regionResidence;

    @Column(name = "commune_residence")
    private String communeResidence;

    @Transient
    private List<Profile> profiles;

    private User() {
        this.id = new UserId();
    }

    public static User from(UserAddCommand command, String password, LocalDateTime passwordExpiryDate) {
        User user = new User();
        user.login = command.getLogin().toLowerCase();
        user.password = password;
        user.passwordExpiryDate = passwordExpiryDate;
        user.type = UserType.AGENT;
        user.status = UserStatus.INITIATED;
        user.firstName = command.getFirstName();
        user.firstNameAr = command.getFirstNameAr();
        user.lastName = command.getLastName();
        user.lastNameAr = command.getLastNameAr();
        user.email = command.getEmail();
        user.nationalite = command.getNationalite();
        user.indicatifPays = command.getIndicatifPays();
        user.telephone = command.getTelephone();
        user.pieceIdentite = command.getCinPasseport();
        user.adresseResidence = command.getAdresseResidence();
        user.paysResidence = command.getPaysResidence();
        user.regionResidence = command.getRegionResidence();
        user.provinceResidence = command.getProvinceResidence();
        user.communeResidence = command.getCommuneResidence();
        return user;
    }

    public static User from(SignUpCommand command, String password, LocalDateTime passwordExpiryDate) {
        User user = new User();
        user.login = command.getEmail().toLowerCase();
        user.password = password;
        user.passwordExpiryDate = passwordExpiryDate;
        user.type = UserType.AGENT;
        user.status = UserStatus.INITIATED;
        user.currentProfileId = ProfileId.from("1000");
        user.firstName = command.getPrenom();
        user.lastName = command.getNom();
        user.email = command.getEmail().toLowerCase();
        user.indicatifPays = command.getIndicatifPays();
        user.telephone = command.getTelephone();
        user.pieceIdentite = command.getCinPasseport();
        user.mre = command.getMre();
        user.adresseResidence = command.getAdresseResidence();

        user.firstNameAr = command.getPrenomAr();
        user.lastNameAr = command.getNomAr();
        user.nationalite = command.getNationalite();
        user.paysResidence = command.getPaysResidence();
        user.regionResidence = command.getRegionResidence();
        user.provinceResidence = command.getProvinceResidence();
        user.communeResidence = command.getCommuneResidence();

        return user;
    }

    public void update(UserUpdateCommand command) {
        this.firstName = checkString(command.getFirstName(), this.firstName);
        this.lastName = checkString(command.getLastName(), this.lastName);
        this.email = checkString(command.getEmail(), this.email).toLowerCase();
        this.indicatifPays = checkString(command.getIndicatifPays(), this.indicatifPays);
        this.telephone = checkString(command.getTelephone(), this.telephone);
        this.pieceIdentite = checkString(command.getCinPasseport(), this.pieceIdentite);
        this.mre = checkString(command.getMre(), this.mre);
        this.adresseResidence = checkString(command.getAdresseResidence(), this.adresseResidence);

        this.firstNameAr = checkString(command.getFirstNameAr(), this.firstNameAr);
        this.lastNameAr = checkString(command.getLastNameAr(), this.lastNameAr);
        this.nationalite = checkString(command.getNationalite(), this.nationalite);
        this.paysResidence = checkString(command.getPaysResidence(), this.paysResidence);
        this.regionResidence = checkString(command.getRegionResidence(), this.regionResidence);
        this.provinceResidence = checkString(command.getProvinceResidence(), this.provinceResidence);
        this.communeResidence = checkString(command.getCommuneResidence(), this.communeResidence);
    }

    private String checkString(String newString, String oldString) {
        if (!Strings.isNullOrEmpty(newString)) {
            return newString;
        }
        return oldString;
    }

    public void resetPassword(String password, LocalDateTime passwordExpiryDate) {
        this.password = password;
        this.passwordExpiryDate = passwordExpiryDate;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public void setValid() {
        this.status = UserStatus.VALID;
    }

    public void setInitiated() {
        this.status = UserStatus.INITIATED;
    }

    public void setStatus(String code) {
        this.status = UserStatus.valueOf(code);
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public void setCurrentProfileId(ProfileId currentProfileId) {
        this.currentProfileId = currentProfileId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
