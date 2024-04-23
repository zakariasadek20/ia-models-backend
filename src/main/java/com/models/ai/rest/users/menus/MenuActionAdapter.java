package com.models.ai.rest.users.menus;

import com.models.ai.domain.users.menus.menusactions.MenuAction;
import com.models.ai.rest.users.menus.adapters.MenuActionDTO;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class MenuActionAdapter {
    public List<MenuActionDTO> adaptFromMenuActions(
            List<MenuAction> matchedMenuActions, List<MenuAction> allMenuActions) {

        if (CollectionUtils.isEmpty(allMenuActions)) {
            return Optional.ofNullable(matchedMenuActions).orElse(Collections.emptyList()).stream()
                    .map(MenuActionDTO::new)
                    .collect(Collectors.toList());
        }

        return allMenuActions.stream()
                .map(m -> new MenuActionDTO(m, isMenuEnabled(m, matchedMenuActions)))
                .collect(Collectors.toList());
    }

    private static boolean isMenuEnabled(MenuAction m, List<MenuAction> matchedMenuActions) {
        return Optional.ofNullable(matchedMenuActions).orElse(Collections.emptyList()).stream()
                .anyMatch(matched -> Objects.equals(m.getId(), matched.getId()));
    }
}
