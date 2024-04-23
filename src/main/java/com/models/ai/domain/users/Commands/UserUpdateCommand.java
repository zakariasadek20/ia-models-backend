package com.models.ai.domain.users.Commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateCommand {

    @JsonProperty(value = "user_id")
    private String userId;

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

    @JsonProperty(value = "telephone")
    private String telephone;

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

    @JsonProperty(value = "partenaire_crtta_id")
    private String partenaireCRTTAId;
}
