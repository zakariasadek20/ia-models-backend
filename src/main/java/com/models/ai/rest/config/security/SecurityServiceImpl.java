package com.models.ai.rest.config.security;

import com.models.ai.domain.users.SecurityService;
import com.models.ai.domain.users.User;
import com.models.ai.domain.users.exceptions.UserAuthenticatedException;
import com.models.ai.domain.users.profiles.ProfileId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "jwt")
public class SecurityServiceImpl implements SecurityService {

    //    private final UserProfileProvider userProfileProvider;
    //    private final UserInfoProvider userInfoProvider;

    private Optional<User> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    private User extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof CustomUser) {
            CustomUser currentUser = (CustomUser) authentication.getPrincipal();
            return currentUser.getUser();
        }
        return null;
    }

    @Override
    public User getAuthenticatedUser() {
        User user = getCurrentUserLogin().orElseThrow(UserAuthenticatedException::new);
        //        List<Profile> profiles = userProfileProvider.getBy(user.getId());
        //        user.setProfiles(profiles);
        //
        //        UserInfo userInfo =
        //
        // userInfoProvider.getBy(user.getId()).orElseThrow(UserNotFoundException::new);
        //        user.setUserInfo(userInfo);
        return user;
    }

    @Override
    public ProfileId getCurrentProfileIdForAuthenticatedUser() {
        User user = getAuthenticatedUser();
        return user.getCurrentProfileId();
    }
}
