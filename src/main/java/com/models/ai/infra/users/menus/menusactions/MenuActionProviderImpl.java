package com.models.ai.infra.users.menus.menusactions;

import com.models.ai.domain.base.BaseEntity;
import com.models.ai.domain.users.menus.menusactions.MenuAction;
import com.models.ai.domain.users.menus.menusactions.MenuActionId;
import com.models.ai.domain.users.menus.menusactions.MenuActionProvider;
import com.models.ai.domain.users.profiles.ProfileId;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuAction;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuActionId;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuActionProvider;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class MenuActionProviderImpl implements MenuActionProvider {

    private static final String MENU_ACTION_CACHE = "MENU_ACTION_CACHE";

    private final ProfileMenuActionProvider profileMenuActionProvider;
    private final MenuActionRepository menuActionRepository;

    @Override
    @Cacheable(cacheNames = MENU_ACTION_CACHE)
    public List<MenuAction> getBy(ProfileId profileId) {
        List<ProfileMenuAction> profileMenuActions = profileMenuActionProvider.getBy(profileId);
        Set<MenuActionId> menuActionIds = profileMenuActions.stream()
                .map(BaseEntity::getId)
                .map(ProfileMenuActionId::getMenuActionId)
                .collect(Collectors.toSet());

        return menuActionRepository.findAllById(menuActionIds);
    }

    @Override
    @Cacheable(cacheNames = MENU_ACTION_CACHE)
    public List<MenuAction> getAll() {
        return menuActionRepository.findAll();
    }
}
