package com.models.ai.domain.users;

import com.models.ai.domain.users.Commands.UserSearchCriteria;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    Optional<User> getByLogin(String login);

    Optional<User> getBy(UserId userId);

    User save(User user);

    List<User> saveAll(List<User> user);

    List<User> getBy(Collection<UserId> userIds);

    List<User> getBy(UserSearchCriteria userSearchCriteria);

    Long countBy(UserSearchCriteria criteria);

    List<User> getAll();

    Optional<User> getByTelephone(String telephone);

    Optional<User> getByPieceIdentite(String telephone);
}
