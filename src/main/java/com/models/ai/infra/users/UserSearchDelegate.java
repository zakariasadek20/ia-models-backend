package com.models.ai.infra.users;

import com.models.ai.domain.users.Commands.UserSearchCriteria;
import com.models.ai.domain.users.User;
import com.models.ai.domain.users.UserStatus;
import com.models.ai.domain.users.profiles.ProfileId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSearchDelegate {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public Long countBy(UserSearchCriteria criteria) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sqlQuery =
                new StringBuilder("Select count(u.id) FROM User u join UserInfo ui ON u.id = ui.id.userId ");
        sqlQuery.append(" JOIN UserProfile up ON up.id.userId = u.id ");
        sqlQuery.append(" WHERE 1 = 1 ");

        // sqlQuery.append(" AND u.type = :userType ");
        // params.put("userType", UserType.AGENT);

        appendUserNameAndFirstName(criteria, params, sqlQuery);
        appendUserLoginAndEmail(criteria, params, sqlQuery);
        appendProfileAndStatus(criteria, params, sqlQuery);

        appendTelephone(criteria, params, sqlQuery);

        Query query = entityManager.createQuery(sqlQuery.toString());

        params.forEach(query::setParameter);

        Long count = (long) query.getSingleResult();

        return count;
    }

    public List<User> getBy(UserSearchCriteria criteria) {

        Map<String, Object> params = new HashMap<>();
        StringBuilder sqlQuery = new StringBuilder("Select u FROM User u join UserInfo ui ON u.id = ui.id.userId ");
        sqlQuery.append(" JOIN UserProfile up ON up.id.userId = u.id ");
        sqlQuery.append(" WHERE 1 = 1 ");

        // sqlQuery.append(" AND u.type = :userType ");

        // params.put("userType", UserType.AGENT);

        appendUserNameAndFirstName(criteria, params, sqlQuery);
        appendUserLoginAndEmail(criteria, params, sqlQuery);
        appendProfileAndStatus(criteria, params, sqlQuery);

        appendTelephone(criteria, params, sqlQuery);

        TypedQuery<User> query = entityManager.createQuery(sqlQuery.toString(), User.class);

        params.forEach(query::setParameter);

        int pageSize = criteria.getSearchMetaCriteria().getPageSize();
        int page = criteria.getSearchMetaCriteria().getPage();

        if (pageSize == 0) {
            query.setFirstResult(0);
            query.setMaxResults(Integer.MAX_VALUE);
        } else {
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
        }

        List<User> users = query.getResultList();

        return users;
    }

    private static void appendProfileAndStatus(
            UserSearchCriteria criteria, Map<String, Object> params, StringBuilder sqlQuery) {

        if (!ObjectUtils.isEmpty(criteria.getProfile())) {
            sqlQuery.append(" AND up.id.profileId = :profileId ");

            params.put("profileId", ProfileId.from(criteria.getProfile()));
        }

        if (!ObjectUtils.isEmpty(criteria.getStatut())) {
            sqlQuery.append(" AND u.status in (:status) ");

            params.put("status", List.of(UserStatus.valueOf(criteria.getStatut())));
        }
    }

    private static void appendUserLoginAndEmail(
            UserSearchCriteria criteria, Map<String, Object> params, StringBuilder sqlQuery) {
        if (!ObjectUtils.isEmpty(criteria.getLogin())) {
            sqlQuery.append(" AND u.login = :login ");

            params.put("login", criteria.getLogin());
        }
        if (!ObjectUtils.isEmpty(criteria.getEmail())) {
            sqlQuery.append(" AND ui.email = :email ");

            params.put("email", criteria.getEmail());
        }
    }

    private static void appendUserNameAndFirstName(
            UserSearchCriteria criteria, Map<String, Object> params, StringBuilder sqlQuery) {
        if (!ObjectUtils.isEmpty(criteria.getFirstName())) {
            sqlQuery.append(" AND ui.firstName = :firstName ");

            params.put("firstName", criteria.getFirstName());
        }
        if (!ObjectUtils.isEmpty(criteria.getLastName())) {
            sqlQuery.append(" AND ui.lastName = :lastName ");

            params.put("lastName", criteria.getLastName());
        }
    }

    private static void appendTelephone(
            UserSearchCriteria criteria, Map<String, Object> params, StringBuilder sqlQuery) {
        if (!ObjectUtils.isEmpty(criteria.getTelephone())) {
            sqlQuery.append(" AND ui.telephone = :telephone ");

            params.put("telephone", criteria.getTelephone());
        }
    }
}
