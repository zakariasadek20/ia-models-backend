package com.models.ai.domain.utils;

import java.math.BigDecimal;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NumberUtils {

    public static BigDecimal parseBigDecimal(String value) {
        if (Objects.isNull(value)) return null;
        try {
            return new BigDecimal(value);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
