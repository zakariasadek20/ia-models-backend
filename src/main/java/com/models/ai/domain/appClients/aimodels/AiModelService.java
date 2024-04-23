package com.models.ai.domain.appClients.aimodels;

import com.models.ai.domain.appClients.aimodels.commands.AiModelAddCommand;
import com.models.ai.domain.appClients.aimodels.exceptions.AiModelAlreadyExistException;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AiModelService {

    private final AIModelProvider aiModelProvider;

    public AiModel createAppClient(AiModelAddCommand command) {

        verifyAiModelDoesNotExist(command);

        AiModel aiModel = AiModel.from(command);
        aiModel = aiModelProvider.save(aiModel);

        return aiModel;
    }

    private void verifyAiModelDoesNotExist(AiModelAddCommand command) {

        Optional<AiModel> checkAiModel = aiModelProvider.getByName(command.getName());
        if (checkAiModel.isPresent()) {
            throw new AiModelAlreadyExistException();
        }
        if (!Objects.isNull(command.getCommandLine())) {
            checkAiModel = aiModelProvider.getByCommandLine(command.getCommandLine());
            if (checkAiModel.isPresent()) {
                throw new AiModelAlreadyExistException();
            }
        }
    }
}
