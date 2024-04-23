package com.models.ai.domain.users;

import com.models.ai.domain.users.Commands.UserAddCommand;
import com.models.ai.domain.users.Commands.UserUpdateCommand;
import com.models.ai.domain.users.Commands.UserUpdateStatus;

public interface UserService {

    User createUser(UserAddCommand command);

    User updateUser(UserUpdateCommand userUpdateCommand);

    void updateStatut(String userId, UserUpdateStatus command);
}
