package com.models.ai.domain.users;

import com.models.ai.domain.users.Commands.UserForgotPasswordCommand;
import com.models.ai.domain.users.Commands.UserPasswordChangeCommand;
import com.models.ai.domain.users.exceptions.UserNotFoundException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserPasswordService {

    UserProvider userProvider;

    public User forgotPassword(UserForgotPasswordCommand command) {

        User user = userProvider.getByLogin(command.getLogin()).orElseThrow(UserNotFoundException::new);

        String randomPassword = RandomStringUtils.random(8, 0, 0, true, true, (char[]) null, new SecureRandom());
        String password = randomPassword;
        user.resetPassword(password, LocalDateTime.now().minusDays(10));

        userProvider.save(user);

        return user;
    }

    public User changePassword(UserPasswordChangeCommand command) {
        //        User user =
        // userProvider.getBy(UserId.from(command.getUserId())).orElseThrow(UserNotFoundException::new);
        //
        //        if (!pwdEncoder.matches(command.getOldPassword(), user.getPassword())) {
        //            throw new InvalidPasswordException();
        //        }
        //
        //        if (!Objects.equals(command.getNewPassword(), command.getNewPasswordConfirmation())) {
        //            throw new InvalidPasswordException();
        //        }
        //
        //        String password = pwdEncoder.encode(command.getNewPassword());
        //        LocalDateTime passwordExpiryDate =
        //            LocalDateTime.now().plusDays(serviceProperties.getParams().getPasswordDuration());
        //
        //        LocalDateTime passwordExpiryDate = LocalDateTime.now().plusDays(365);
        //        user.resetPassword(password, passwordExpiryDate);
        //        userProvider.save(user);

        return null;
    }
}
