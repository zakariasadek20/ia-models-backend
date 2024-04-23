package com.models.ai.rest.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public final class ApiResponse<T> {

    private String code;
    private String message;

    @JsonProperty("meta_data")
    private ApiResponseMetaData metaData;

    private final T data;

    private ApiResponse() {
        this.data = null;
    }

    private ApiResponse(T body, ApiResponseMetaData metaData) {
        this.data = body;
        this.metaData = metaData;
    }

    private ApiResponse(T body) {
        this.data = body;
    }

    private ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse<Object> ok() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> ok(T body) {
        return new ApiResponse<>(body);
    }

    public static <T> ApiResponse<T> ok(T body, ApiResponseMetaData metaData) {
        return new ApiResponse<>(body, metaData);
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static <T> ApiResponse<T> error(String code, String message, T body) {
        return new ApiResponse<>(code, message, body);
    }
}
