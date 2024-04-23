package com.models.ai.domain.appClients;

import com.models.ai.domain.appClients.commands.AppClientsAddCommand;
import com.models.ai.domain.appClients.exceptions.AppClientsAlreadyExistException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AppClientService {

    private final AppClientProvider appClientProvider;

    public AppClient createAppClient(AppClientsAddCommand command) {

        Optional<AppClient> checkAppClients = appClientProvider.getByName(command.getName());
        if (checkAppClients.isPresent()) {
            throw new AppClientsAlreadyExistException();
        }
        AppClient appClient = AppClient.from(command);
        appClient = appClientProvider.save(appClient);

        return appClient;
    }
}
