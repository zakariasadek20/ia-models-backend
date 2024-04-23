package com.models.ai.infra.users;

import com.models.ai.domain.users.User;
import com.models.ai.domain.users.UserProvider;
import com.models.ai.domain.users.UserStatus;
import com.models.ai.domain.users.profiles.ProfileUtils;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserJob {

    private final UserProvider userProvider;

    @Scheduled(cron = "0 0 8 * * ?")
    public void updateStatutUsers() {

        log.info("Begin update Statut users after 6 month de inactive");
        List<User> users = userProvider.getAll().stream()
                .filter(u -> ProfileUtils.isUser(u.getCurrentProfileId())
                        && Period.of(0, 6, 0).equals(u.getLastAccess().toLocalDate()))
                .collect(Collectors.toList());

        users.forEach(u -> u.setStatus(UserStatus.DEACTIVATED.getCode()));
        userProvider.saveAll(users);
        log.info("Begin update Statut users after 6 mois de inactive");
    }
}
