package com.models.ai.domain.appClients;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AppClientProvider {

    Optional<AppClient> getByName(String name);

    Optional<AppClient> getBy(AppClientId appClientId);

    Optional<AppClient> getByHost(String host);

    List<AppClient> getByStore(String store);

    AppClient save(AppClient appClient);

    List<AppClient> saveAll(List<AppClient> appClients);

    List<AppClient> getBy(Collection<AppClientId> appClientIds);

    List<AppClient> getAll();
}
