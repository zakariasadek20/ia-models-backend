package com.models.ai.rest.users.adapters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.ai.domain.users.User;
import com.models.ai.domain.users.UserId;
import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.rest.users.profiles.adapters.ProfileDetailDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("login")
    private String login;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("operateur")
    private String operateur;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("updated_by")
    private String updatedBy;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("status")
    private UserStatutDTO status;

    @JsonProperty("password_status")
    private String passwordStatus;

    @JsonProperty("type")
    private String type;

    @JsonProperty("nouveau_utilisateur")
    private boolean nouveauUtilisateur;

    @JsonProperty("profiles")
    private List<ProfileDetailDTO> profiles;

    @JsonProperty("last_access")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastAccess;

    @JsonProperty("prenom")
    private String prenom;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("email")
    private String email;

    @JsonProperty("indicatif_pays")
    private String indicatifPays;

    @JsonProperty("piece_identite")
    private String pieceIdentite;

    @JsonProperty("adresse_residence")
    private String adresseResidence;

    public void addProfiles(List<ProfileDetailDTO> profileDetailDTOS) {
        this.profiles = new ArrayList<>(profileDetailDTOS);
    }

    static UserDTO from(User user, List<Profile> profileList, UserStatutDTO status) {
        return builder()
                .id(user.getId().getValue())
                .login(user.getLogin())
                .passwordStatus(getPasswordStatus(user))
                .createdBy(user.getCreatedBy().map(UserId::getValue).orElse(null))
                .createdAt(user.getCreatedAt().orElse(null))
                .updatedBy(user.getUpdatedBy().map(UserId::getValue).orElse(null))
                .updatedAt(user.getUpdatedAt().map(LocalDateTime::toString).orElse(null))
                .status(status)
                .type(user.getType().getCode())
                .profiles(Optional.ofNullable(
                                profileList.stream().map(ProfileDetailDTO::from).collect(Collectors.toList()))
                        .orElse(null))
                .lastAccess(Optional.ofNullable(user.getLastAccess()).orElse(null))
                .prenom(user.getFirstName())
                .nom(user.getLastName())
                .email(user.getEmail())
                .telephone(user.getTelephone())
                .indicatifPays(user.getIndicatifPays())
                .createdBy(user.getCreatedBy().map(UserId::getValue).orElse(null))
                .updatedAt(user.getUpdatedAt().map(LocalDateTime::toString).orElse(null))
                .updatedBy(user.getUpdatedBy().map(UserId::getValue).orElse(null))
                .pieceIdentite(user.getPieceIdentite())
                .adresseResidence(user.getAdresseResidence())
                .build();
    }

    private static String getPasswordStatus(User user) {
        LocalDateTime infinity = LocalDateTime.now().plusYears(9999);

        LocalDateTime passwordExpiryDate =
                Optional.ofNullable(user.getPasswordExpiryDate()).orElse(infinity);

        return passwordExpiryDate.isAfter(LocalDateTime.now()) ? "VALID" : "EXPIRED";
    }
}
