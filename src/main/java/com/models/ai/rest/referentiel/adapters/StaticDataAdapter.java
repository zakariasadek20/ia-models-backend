package com.models.ai.rest.referentiel.adapters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StaticDataAdapter {

    @Cacheable(cacheNames = "STATIC_DATA")
    public StaticDataDTO getAll() {

        return null;
    }
}
