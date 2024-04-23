package com.models.ai.domain.users;

import com.models.ai.domain.users.Commands.SignUpCommand;
import com.models.ai.domain.users.exceptions.UserAlreadyExistsException;
import com.models.ai.domain.users.usersprofiles.UserProfileService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SignUpService {
    private final UserProvider userProvider;
    private final UserProfileService userProfileService;

    public User signUp(SignUpCommand command) {

        verifyUserDoesntExist(command);
        User user = createUser(command);
        user = userProvider.save(user);

        userProfileService.assignProfileToUser(user, UserConstants.ProfileConstants.USER);

        return user;
    }

    private User createUser(SignUpCommand command) {
        String password = command.getPassword();
        LocalDateTime passwordExpiryDate = LocalDateTime.now().plusDays(365L);
        return User.from(command, password, passwordExpiryDate);
    }

    private void verifyUserDoesntExist(SignUpCommand command) {
        //        Optional<User> userOptional = userProvider.getByEmail(command.getEmail());
        //        if (userOptional.isPresent()) {
        //            throw new UserAlreadyExistsException();
        //        }

        Optional<User> userOptional = userProvider.getByTelephone(command.getTelephone());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        userOptional = userProvider.getByPieceIdentite(command.getCinPasseport());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException();
        }
    }
}
