package com.models.ai.infra.users;

import com.models.ai.domain.users.User;
import com.models.ai.domain.users.UserId;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {

    Optional<User> findOneByLogin(String login);

    List<User> findAllByIdIn(Collection<UserId> userIds);

    Optional<User> getOneByTelephone(String email);

    Optional<User> getOneByPieceIdentite(String pieceIdentite);
}
