package com.models.ai.domain.users.menus.menusactions;

import com.models.ai.domain.users.profiles.ProfileId;
import java.util.List;

public interface MenuActionProvider {
    List<MenuAction> getBy(ProfileId profileId);

    List<MenuAction> getAll();
}
