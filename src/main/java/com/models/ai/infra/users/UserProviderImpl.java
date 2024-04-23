package com.models.ai.infra.users;

import com.models.ai.domain.users.Commands.UserSearchCriteria;
import com.models.ai.domain.users.User;
import com.models.ai.domain.users.UserId;
import com.models.ai.domain.users.UserProvider;
import jakarta.persistence.EntityManager;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserProviderImpl implements UserProvider {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final UserSearchDelegate userSearchDelegate;

    @Override
    public Optional<User> getByLogin(String login) {
        if (Objects.nonNull(login)) {
            Optional<User> optionalUserInfo = userRepository.findOneByLogin(login);
            return optionalUserInfo;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getBy(UserId userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> saveAll(List<User> user) {
        return userRepository.saveAll(user);
    }

    @Override
    public List<User> getBy(UserSearchCriteria criteria) {

        return userSearchDelegate.getBy(criteria);
    }

    @Override
    public Long countBy(UserSearchCriteria criteria) {
        return userSearchDelegate.countBy(criteria);
    }

    @Override
    public List<User> getBy(Collection<UserId> userIds) {

        List<User> users = new ArrayList<>();
        List<List<UserId>> partitions = ListUtils.partition(new ArrayList<>(userIds), 999);
        for (List<UserId> partition : partitions) {
            users.addAll(userRepository.findAllByIdIn(partition));
        }
        return users;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getByTelephone(String telephone) {
        return userRepository.getOneByTelephone(telephone);
    }

    @Override
    public Optional<User> getByPieceIdentite(String pieceIdentite) {
        return userRepository.getOneByPieceIdentite(pieceIdentite);
    }
}
