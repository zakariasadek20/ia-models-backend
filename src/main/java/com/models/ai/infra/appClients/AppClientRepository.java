package com.models.ai.infra.appClients;

import com.models.ai.domain.appClients.AppClient;
import com.models.ai.domain.appClients.AppClientId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppClientRepository extends JpaRepository<AppClient, AppClientId> {

    Optional<AppClient> findOneByName(String name);

    Optional<AppClient> findOneByHost(String host);

    List<AppClient> findAllByStore(String store);
}
