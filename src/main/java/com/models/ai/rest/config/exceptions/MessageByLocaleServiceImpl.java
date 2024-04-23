package com.models.ai.rest.config.exceptions;

import com.models.ai.domain.utils.MessageByLocaleService;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

    private MessageSource messageSource;

    public MessageByLocaleServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String id, Locale locale) {
        return messageSource.getMessage(id, null, locale);
    }

    @Override
    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id, null, locale);
    }

    @Override
    public String getMessage(String id, Locale locale, String... args) {
        return messageSource.getMessage(id, args, locale);
    }

    @Override
    public String getMessage(String id, String... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id, args, locale);
    }
}
