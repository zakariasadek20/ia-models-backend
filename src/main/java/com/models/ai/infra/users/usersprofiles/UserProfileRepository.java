package com.models.ai.infra.users.usersprofiles;

import com.models.ai.domain.users.UserId;
import com.models.ai.domain.users.usersprofiles.UserProfile;
import com.models.ai.domain.users.usersprofiles.UserProfileId;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UserProfileId> {
    @Query("SELECT p FROM UserProfile p Where p.id.userId = ?1")
    List<UserProfile> findById_userId(UserId userId);

    @Query("SELECT p FROM UserProfile p Where p.id.userId in (?1)")
    List<UserProfile> findById_userIdIn(Collection<UserId> userIds);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserProfile p Where p.id.userId = ?1")
    void deleteBy_UserId(UserId userId);
}
