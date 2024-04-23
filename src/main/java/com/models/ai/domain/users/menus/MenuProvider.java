package com.models.ai.domain.users.menus;

import com.models.ai.domain.users.profiles.ProfileId;
import java.util.List;

public interface MenuProvider {

    List<Menu> getWithParentsBy(ProfileId id);

    List<Menu> getAllMenu();
}
