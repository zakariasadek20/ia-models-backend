package com.models.ai.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMetaCriteria {
    @JsonProperty("with_count")
    private Boolean withCount;

    @JsonProperty("page")
    private int page;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("sort_field")
    private String sortField;

    @JsonProperty("sort_direction")
    private String sortDirection;
}
