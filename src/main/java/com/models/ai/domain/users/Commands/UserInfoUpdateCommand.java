package com.models.ai.domain.users.Commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoUpdateCommand {

    @JsonProperty(value = "user_id")
    private String userId;

    @JsonProperty(value = "indicatif_pays")
    private String indicatifPays;

    @JsonProperty(value = "telephone")
    private String telephone;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("prenom")
    private String prenom;

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
