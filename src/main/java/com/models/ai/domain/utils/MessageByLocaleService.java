package com.models.ai.domain.utils;

import java.util.Locale;

public interface MessageByLocaleService {

    String getMessage(String id, Locale local);

    String getMessage(String id, Locale local, String... args);

    String getMessage(String id);

    String getMessage(String id, String... args);
}
