package com.models.ai.rest.users.profiles.adapters;

import com.models.ai.domain.users.menus.Menu;
import com.models.ai.domain.users.menus.MenuProvider;
import com.models.ai.domain.users.menus.menusactions.MenuAction;
import com.models.ai.domain.users.menus.menusactions.MenuActionProvider;
import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.rest.users.menus.adapters.MenuAdapter;
import com.models.ai.rest.users.menus.adapters.MenuDetailDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileAdapter {

    private final MenuActionProvider menuActionProvider;
    private final MenuProvider menuProvider;
    private final MenuAdapter menuAdapter;

    public ProfileDetailDTO adaptProfileWithAllMenu(Profile profile) {
        List<MenuAction> matchedMenuActions = menuActionProvider.getBy(profile.getId());
        List<MenuAction> allMenuActions = menuActionProvider.getAll();
        List<Menu> menus = menuProvider.getAllMenu();
        List<MenuDetailDTO> menuDetailDTOS =
                menuAdapter.adaptToMenuDetailDTOWithAllMenu(menus, matchedMenuActions, allMenuActions);

        return ProfileDetailDTO.from(profile, menuDetailDTOS);
    }
}
