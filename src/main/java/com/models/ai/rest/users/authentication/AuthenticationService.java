package com.models.ai.rest.users.authentication;

import com.models.ai.domain.users.User;
import com.models.ai.domain.users.UserProvider;
import com.models.ai.domain.users.UserStatus;
import com.models.ai.domain.users.exceptions.UserDeletedException;
import com.models.ai.domain.users.exceptions.UserNotFoundException;
import com.models.ai.rest.config.security.CustomUser;
import com.models.ai.rest.config.security.SecurityUtils;
import com.models.ai.rest.config.security.jwt.TokenProvider;
import com.models.ai.rest.users.adapters.JWTTokenDTO;
import com.models.ai.rest.users.adapters.LoginCommand;
import com.models.ai.rest.users.adapters.UserAdapter;
import com.models.ai.rest.users.adapters.UserDTO;
import java.time.LocalDateTime;
import java.util.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "jwt")
public class AuthenticationService {
    AuthenticationManagerBuilder authenticationManagerBuilder;
    TokenProvider tokenProvider;
    UserProvider userProvider;
    //    UserInfoProvider userInfoProvider;
    UserAdapter userAdapter;
    //    OtpService otpService;

    public JWTTokenDTO authenticate(LoginCommand loginCommand) {

        User user = userProvider
                .getByLogin(loginCommand.getUsername())
                .orElseThrow(() -> new InsufficientAuthenticationException(""));

        if (user.getStatus() == UserStatus.INITIATED) {
            //            UserInfo userInfo =
            // userInfoProvider.getBy(user.getId()).orElseThrow(UserNotFoundException::new);

            Map<String, String> data = new HashMap<>();
            data.put("email", user.getEmail());
            data.put("telephone", user.getTelephone());

            //            otpService.generateAndSendOtp(user);

        } else if (Arrays.asList(UserStatus.DELETED, UserStatus.DEACTIVATED).contains(user.getStatus())) {
            throw new UserDeletedException();
        }

        Authentication authentication = getAuthentication(user, loginCommand);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwt = getJwt(loginCommand, authentication);
        user.setLastAccess(LocalDateTime.now());
        user = userProvider.save(user);

        UserDTO userDTO = getUserDTO(user);

        return new JWTTokenDTO(jwt, userDTO);
    }

    public UserDTO getUserDTO(User user) {
        UserDTO userDTO =
                SecurityUtils.getCurrentUserLogin().map(this::getUserWithMenu).orElseThrow(UserNotFoundException::new);
        //        if (ProfileUtils.isUser(user.getCurrentProfileId())) {
        //            List<DemandeSubvention> demandeSubventions =
        // demandeSubventionProvider.getBy(UserId.from(userDTO.getId()));
        //            userDTO.setNouveauUtilisateur(CollectionUtils.isEmpty(demandeSubventions));
        //        }
        return userDTO;
    }

    private UserDTO getUserWithMenu(String login) {
        Optional<User> user = userProvider.getByLogin(login);
        return user.map(userAdapter::adaptUserToDtoWithMenu).orElse(null);
    }

    private String getJwt(LoginCommand loginCommand, Authentication authentication) {
        boolean rememberMe = loginCommand.getRememberMe() != null && loginCommand.getRememberMe();
        return tokenProvider.createToken(authentication, rememberMe);
    }

    private Authentication getAuthentication(User user, LoginCommand command) {

        Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

        CustomUser principal = new CustomUser(command.getUsername(), user.getPassword(), authorities, user);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(principal, command.getPassword(), authorities);

        try {
            return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            log.error("", e);
            throw e;
        }
    }
}
