package com.models.ai.rest.referentiel;

import com.models.ai.rest.referentiel.adapters.InternalStaticDataAdapter;
import com.models.ai.rest.referentiel.adapters.InternalStaticDataDTO;
import com.models.ai.rest.utils.ApiResponse;
import com.models.ai.rest.utils.ResourcePaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InternalStaticDataController {

    private final InternalStaticDataAdapter internalStaticDataAdapter;

    @GetMapping(path = ResourcePaths.StaticData.ENDPOINT_API_INTERNAL_STATIC_DATA)
    public ResponseEntity<ApiResponse<InternalStaticDataDTO>> getAll() {
        log.info("REST request to get Internal Static Data");

        InternalStaticDataDTO internalStaticDataDTO = internalStaticDataAdapter.getAll();

        return ResponseEntity.ok(ApiResponse.ok(internalStaticDataDTO));
    }
}
