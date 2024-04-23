package com.models.ai.domain.users.Commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddCommand {
    @NotNull
    @JsonProperty(value = "login")
    private String login;

    @NotNull
    @JsonProperty(value = "profile")
    private String profile;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "first_name_ar")
    private String firstNameAr;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "last_name_ar")
    private String lastNameAr;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "indicatif_pays")
    private String indicatifPays;

    @JsonProperty(value = "nationalite")
    private String nationalite;

    @JsonProperty(value = "telephone")
    private String telephone;

    @JsonProperty(value = "cin_passeport")
    private String cinPasseport;

    @JsonProperty(value = "adresse_residence")
    private String adresseResidence;

    @JsonProperty("pays_residence")
    private String paysResidence;

    @JsonProperty("province_residence")
    private String provinceResidence;

    @JsonProperty("region_residence")
    private String regionResidence;

    @JsonProperty("commune_residence")
    private String communeResidence;
}
