package com.models.ai.rest.referentiel.adapters;

import com.models.ai.domain.referentiel.entitylabels.EntityLabel;
import com.models.ai.domain.referentiel.entitylabels.EntityLabelProvider;
import com.models.ai.domain.referentiel.entitylabels.EntityLabelType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class InternalStaticDataAdapter {

    EntityLabelProvider entityLabelProvider;

    // @Cacheable(cacheNames = "INTERNAL_STATIC_DATA")
    public InternalStaticDataDTO getAll() {
        return null;
    }

    List<EntityLabel> getLabels(String id, EntityLabelType entityLabelType) {
        List<EntityLabel> labels = entityLabelProvider.getBy(entityLabelType);
        Map<String, List<EntityLabel>> labelsMap =
                labels.stream().collect(Collectors.groupingBy(EntityLabel::getEntityId));

        return labelsMap.getOrDefault(id, Collections.emptyList());
    }
}
