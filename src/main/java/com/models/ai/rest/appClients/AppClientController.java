package com.models.ai.rest.appClients;

import com.models.ai.domain.appClients.AppClient;
import com.models.ai.domain.appClients.AppClientProvider;
import com.models.ai.rest.appClients.adapters.AppClientDTO;
import com.models.ai.rest.utils.ResourcePaths;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AppClientController {

    private final AppClientProvider appClientProvider;

    @GetMapping(path = ResourcePaths.AppClients.ENDPOINT_API_APP_CLIENTS)
    public ResponseEntity<List<AppClientDTO>> getApps() {
        log.debug("REST request to get All apps");

        List<AppClient> appClients = appClientProvider.getAll();

        List<AppClientDTO> appClientDTOS = AppClientDTO.from(appClients);

        return ResponseEntity.ok(appClientDTOS);
    }
}
