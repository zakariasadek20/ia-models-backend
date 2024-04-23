package com.models.ai.rest.users.menus.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
public class MenuDTO {
    protected String id;
    protected String code;
    protected String description;
    protected String i18nCode;
    protected String icon;
    protected String link;
    protected String parent;
    protected long level;

    @JsonProperty("order")
    protected long order;

    @JsonProperty("global_order")
    protected String globalOrder;

    @JsonProperty("visible")
    protected Boolean visible;
}
