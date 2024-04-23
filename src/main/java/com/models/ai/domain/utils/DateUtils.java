package com.models.ai.domain.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static LocalDate parseDate(String date, String dateFormat) {
        try {
            return Optional.ofNullable(date)
                    .map(d -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                Optional.ofNullable(dateFormat).orElse(DATE_FORMAT), Locale.FRENCH);
                        return LocalDate.parse(date, formatter);
                    })
                    .orElse(null);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static LocalDate parseDate(String date) {
        return parseDate(date, DATE_FORMAT);
    }

    public static String formatDate(LocalDate date, String dateFormat) {
        try {
            return Optional.ofNullable(date)
                    .map(d -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                Optional.ofNullable(dateFormat).orElse(DATE_FORMAT), Locale.FRENCH);
                        return formatter.format(date);
                    })
                    .orElse(null);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String formatDate(LocalDate date) {
        return formatDate(date, DATE_FORMAT);
    }

    public static String formatDateTime(LocalDateTime dateTime, String dateTimeFormat) {
        try {
            return Optional.ofNullable(dateTime)
                    .map(d -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                Optional.ofNullable(dateTimeFormat).orElse(DATETIME_FORMAT), Locale.FRENCH);
                        return formatter.format(dateTime);
                    })
                    .orElse(null);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DATETIME_FORMAT);
    }
}
