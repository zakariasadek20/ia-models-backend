package com.models.ai.rest.appClients.aimodels;

import com.models.ai.domain.appClients.aimodels.AIModelProvider;
import com.models.ai.domain.appClients.aimodels.AiModel;
import com.models.ai.domain.appClients.aimodels.AiModelId;
import com.models.ai.domain.appClients.aimodels.AiModelRunnerService;
import com.models.ai.domain.appClients.aimodels.aiimage.AiImageProvider;
import com.models.ai.rest.appClients.aimodels.adapters.AiModelDTO;
import com.models.ai.rest.utils.ResourcePaths;
import java.io.ByteArrayOutputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AiModelController {

    private final AIModelProvider aiModelProvider;
    private final AiModelRunnerService aiModelRunnerService;
    private final AiImageProvider aiImageProvider;

    @GetMapping(path = ResourcePaths.AiModels.ENDPOINT_API_AI_MODELS)
    public ResponseEntity<List<AiModelDTO>> getAllAiModel() {
        log.debug("REST request to get All ai model");

        List<AiModel> aiModels = aiModelProvider.getAll();

        List<AiModelDTO> aiModelDTOS = AiModelDTO.from(aiModels);

        return ResponseEntity.ok(aiModelDTOS);
    }

    @PostMapping(path = ResourcePaths.AiModels.ENDPOINT_API_AI_MODELS_RUN_ONE_AI)
    public ResponseEntity<ByteArrayResource> downloadFile(
            @PathVariable(ResourcePaths.AiModels.PathVariales.AI_MODEL_AI) String aiModelId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("options") String options) {

        ByteArrayOutputStream outputStream = aiModelRunnerService.RunAiModel(AiModelId.from(aiModelId), options, file);

        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");

        headers.add("X_FILE_EXTENSION", FilenameUtils.getExtension(file.getOriginalFilename()));

        //        String encodedFilename =
        //                UriUtils.encode(document.getFileId(), StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //        String disposition = (download ? "attachment" : "inline") +
        //                "; filename=\"" + filename + "\"" +
        //                "; filename*=UTF-8''" + encodedFilename;

        //        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename*=UTF-8''" + encodedFilename);
        //        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + encodedFilename);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                //                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
