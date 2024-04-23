package com.models.ai.rest.referentiel;

import com.models.ai.rest.referentiel.adapters.StaticDataAdapter;
import com.models.ai.rest.referentiel.adapters.StaticDataDTO;
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
public class StaticDataController {

    private final StaticDataAdapter staticDataAdapter;

    @GetMapping(path = ResourcePaths.StaticData.ENDPOINT_API_STATIC_DATA)
    public ResponseEntity<ApiResponse<StaticDataDTO>> getAll() {
        log.info("REST request to get Static Data");

        StaticDataDTO staticDataDTO = staticDataAdapter.getAll();

        return ResponseEntity.ok(ApiResponse.ok(staticDataDTO));
    }
}
