package com.models.ai.domain.users.Commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpCommand {
    @NotNull
    @JsonProperty(value = "login")
    private String login;

    @NotNull
    @JsonProperty("password")
    private String password;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    @JsonProperty("nom_ar")
    private String nomAr;

    @NotNull
    @JsonProperty("prenom")
    private String prenom;

    @NotNull
    @JsonProperty("prenom_ar")
    private String prenomAr;

    @NotNull
    @JsonProperty("indicatif_pays")
    private String indicatifPays;

    @NotNull
    @JsonProperty("telephone")
    private String telephone;

    @NotNull
    @JsonProperty("email")
    private String email;

    @JsonProperty("nationalite")
    private String nationalite;

    @JsonProperty("cin_passeport")
    private String cinPasseport;

    @JsonProperty("mre")
    private String mre;

    @JsonProperty("pays_residence")
    private String paysResidence;

    @JsonProperty("province_residence")
    private String provinceResidence;

    @JsonProperty("region_residence")
    private String regionResidence;

    @JsonProperty("commune_residence")
    private String communeResidence;

    @JsonProperty("adresse_residence")
    private String adresseResidence;
}
