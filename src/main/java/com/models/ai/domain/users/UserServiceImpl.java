package com.models.ai.domain.users;

import com.models.ai.domain.users.Commands.UserAddCommand;
import com.models.ai.domain.users.Commands.UserUpdateCommand;
import com.models.ai.domain.users.Commands.UserUpdateStatus;
import com.models.ai.domain.users.exceptions.PhoneAlreadyUsedException;
import com.models.ai.domain.users.exceptions.UserAlreadyExistsException;
import com.models.ai.domain.users.exceptions.UserNotFoundException;
import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.domain.users.profiles.ProfileId;
import com.models.ai.domain.users.profiles.ProfileProvider;
import com.models.ai.domain.users.usersprofiles.UserProfileService;
import com.models.ai.domain.users.usersprofiles.exceptions.ProfileNotFoundException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserProvider userProvider;
    private final UserProfileService userProfileService;
    private final ProfileProvider profileProvider;

    @Override
    public User createUser(UserAddCommand command) {
        verifyUserDoesNotExist(command);

        String randomPassword = RandomStringUtils.random(8, 0, 0, true, true, (char[]) null, new SecureRandom());
        // String password = pwdEncoder.encode(randomPassword);
        String password = randomPassword;

        /*String randomPassword = Optional.ofNullable(command.getPassword()).orElse("password");

        String password = pwdEncoder.encode(randomPassword);*/

        LocalDateTime passwordExpiryDate = LocalDateTime.now().minusDays(10);

        User user = User.from(command, password, passwordExpiryDate);
        user.setValid();

        Profile profile = profileProvider
                .getById(ProfileId.from(command.getProfile()))
                .orElseThrow(ProfileNotFoundException::new);

        user.setCurrentProfileId(profile.getId());

        user.resetPassword(password, LocalDateTime.now().minusDays(10));

        user = userProvider.save(user);

        userProfileService.assignProfileToUser(user, profile.getId());

        /*SmsEnvelop smsEnvelop = SmsEnvelop.builder()
                .userId(user.getId())
                .message(String.format(
                        "Merci d'utiliser votre adresse e-mail pour vous connecter et ce mot de passe temporaire : %s",
                        randomPassword))
                .telephone(userInfo.getTelephone())
                .indicatifPays(userInfo.getIndicatifPays())
                .build();

        smsSender.send(smsEnvelop);*/

        /*if (Objects.nonNull(user.getCodeCoresspondant())) {
            smsEnvelop = SmsEnvelop.builder()
                    .userId(user.getId())
                    .message(String.format(
                            "Voici votre code de correspondant partenaire : %s . Assurez-vous de l'inclure lors de la soumission d'une demande de subvention par un investisseur.",
                            user.getCodeCoresspondant()))
                    .telephone(userInfo.getTelephone())
                    .indicatifPays(userInfo.getIndicatifPays())
                    .build();
            smsSender.send(smsEnvelop);
        }*/

        return user;
    }

    @Override
    public User updateUser(UserUpdateCommand command) {
        // User authenticatedUser = securityService.getAuthenticatedUser();
        UserId userId = UserId.from(command.getUserId());

        // checkIfUserHasAuthorizationToUpdate(authenticatedUser, command);
        User user = verifyUserExists(userId);
        if (Objects.nonNull(command.getEmail()) && !command.getEmail().equals(user.getLogin())) {
            //            Optional<User> checkUser = userProvider.getByEmail(command.getEmail());
            //            if (checkUser.isPresent()) {
            //                throw new UserAlreadyExistsException();
            //            }
            //            user.setLogin(command.getEmail());
        }

        String correspondantCode = null;
        Profile profile = null;
        if (Objects.nonNull(command.getProfile())) {
            profile = profileProvider
                    .getById(ProfileId.from(command.getProfile()))
                    .orElseThrow(ProfileNotFoundException::new);
        }

        if (Objects.nonNull(profile)) {
            user.setCurrentProfileId(profile.getId());
        }

        user = userProvider.save(user);

        updateProfile(user, command);

        return user;
    }

    private void updateProfile(User user, UserUpdateCommand userUpdateCommand) {
        if (Objects.nonNull(userUpdateCommand.getProfile())) {
            userProfileService.assignProfileToUser(user, ProfileId.from(userUpdateCommand.getProfile()));
        }
    }

    private User updateUserInfo(User user, UserUpdateCommand command) {
        Optional<User> optionalUserInfo = userProvider.getBy(user.getId());
        optionalUserInfo.ifPresent(u -> {
            verifyUserDoesNotExist(command, u.getId());
            if (Objects.nonNull(command.getTelephone())) {
                if (!u.getTelephone().equals(command.getTelephone())) {
                    user.setInitiated();
                    userProvider.save(user);
                }
            }
            u.update(command);
            userProvider.save(u);
        });
        return optionalUserInfo.orElseThrow(UserNotFoundException::new);
    }

    private User verifyUserExists(UserId userId) {
        Optional<User> optionalUser = userProvider.getBy(userId);
        return optionalUser.orElseThrow(UserNotFoundException::new);
    }

    private void verifyUserDoesNotExist(UserAddCommand command) {
        //        Optional<User> userOptional = userProvider.getByEmail(command.getEmail());
        //        if (userOptional.isPresent()) {
        //            throw new UserAlreadyExistsException();
        //        }

        Optional<User> userOptional = userProvider.getByTelephone(command.getTelephone());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (!Objects.isNull(command.getCinPasseport())) {
            userOptional = userProvider.getByPieceIdentite(command.getCinPasseport());
            if (userOptional.isPresent()) {
                throw new UserAlreadyExistsException();
            }
        }
    }

    private void verifyUserDoesNotExist(UserUpdateCommand command, UserId userId) {
        Optional<User> userOptional = null;

        userOptional = userProvider.getByTelephone(command.getTelephone());
        if (userOptional.isPresent()) {
            if (!userId.equals(userOptional.get().getId())) {
                throw new PhoneAlreadyUsedException();
            }
        }
    }

    public void updateStatut(String userId, UserUpdateStatus command) {

        UserId user_Id = UserId.from(userId);

        User user = verifyUserExists(user_Id);

        user.setStatus(command.getStatut().toUpperCase());

        userProvider.save(user);
    }
}
