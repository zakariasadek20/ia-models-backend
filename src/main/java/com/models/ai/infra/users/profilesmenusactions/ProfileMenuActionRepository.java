package com.models.ai.infra.users.profilesmenusactions;

import com.models.ai.domain.users.profiles.ProfileId;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuAction;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuActionId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProfileMenuActionRepository extends JpaRepository<ProfileMenuAction, ProfileMenuActionId> {

    @Query("SELECT p FROM ProfileMenuAction p Where p.id.profileId = :profile_id")
    List<ProfileMenuAction> findById_profileId(@Param("profile_id") ProfileId profileId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProfileMenuAction p Where p.id.profileId = ?1")
    void deleteById_profileId(@Param("profile_id") ProfileId profileId);
}
