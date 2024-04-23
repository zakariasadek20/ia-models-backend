package com.models.ai.rest.users.menus.adapters;

import com.models.ai.domain.users.menus.Menu;
import com.models.ai.domain.users.menus.MenuId;
import com.models.ai.domain.users.menus.menusactions.MenuAction;
import com.models.ai.rest.users.menus.MenuActionAdapter;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@Slf4j
@RequiredArgsConstructor
public class MenuAdapter {

    private final MenuActionAdapter menuActionAdapter;

    public MenuDetailDTO adaptToMenuDetailDTO(
            Menu menu, List<MenuAction> matchedMenuActions, List<MenuAction> allMenuActions) {
        return MenuDetailDTO.builder()
                .code(menu.getCode())
                .description(menu.getDescription())
                .i18nCode(menu.getI18nCode())
                .icon(menu.getIcon())
                .id(menu.getId().getValue())
                .level(menu.getLevel())
                .order(menu.getOrder())
                .globalOrder(menu.getGlobalOrder())
                .parent(menu.getParentId().map(MenuId::toString).orElse(null))
                .actions(menuActionAdapter.adaptFromMenuActions(matchedMenuActions, allMenuActions))
                .enabled(!CollectionUtils.isEmpty(matchedMenuActions))
                .link(menu.getLink())
                .visible(Optional.ofNullable(menu.getVisible()).orElse(false))
                .build();
    }

    public List<MenuDetailDTO> adaptToMenuDetailDTO(List<Menu> menus, List<MenuAction> menuActions) {

        List<Long> levels = getLevels(menus);
        Map<MenuId, List<MenuAction>> matchedMenuActionsMap = getMenuActionsMap(menuActions);
        Map<MenuId, List<MenuAction>> allMenuActionsMap = Collections.emptyMap();

        return Collections.unmodifiableList(constructMenuTree(menus, levels, matchedMenuActionsMap, allMenuActionsMap));
    }

    public List<MenuDetailDTO> adaptToMenuDetailDTOWithAllMenu(
            List<Menu> menus, List<MenuAction> matchedMenuActions, List<MenuAction> allMenuActions) {

        List<Long> levels = getLevels(menus);
        Map<MenuId, List<MenuAction>> matchedMenuActionsMap = getMenuActionsMap(matchedMenuActions);
        Map<MenuId, List<MenuAction>> allMenuActionsMap = getMenuActionsMap(allMenuActions);

        return Collections.unmodifiableList(constructMenuTree(menus, levels, matchedMenuActionsMap, allMenuActionsMap));
    }

    private List<MenuDetailDTO> constructMenuTree(
            List<Menu> menus,
            List<Long> levels,
            Map<MenuId, List<MenuAction>> matchedActionsMap,
            Map<MenuId, List<MenuAction>> allActionsMap) {
        List<MenuDetailDTO> result = new ArrayList<>();

        for (Long l : levels) {
            result = getLevelMenus(l, menus, result, matchedActionsMap, allActionsMap);
        }
        return result;
    }

    private static Map<MenuId, List<MenuAction>> getMenuActionsMap(List<MenuAction> menuActions) {
        return Optional.ofNullable(menuActions).orElse(Collections.emptyList()).stream()
                .collect(Collectors.groupingBy(MenuAction::getMenuId));
    }

    private static List<Long> getLevels(List<Menu> menus) {
        return menus.stream()
                .map(Menu::getLevel)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private List<MenuDetailDTO> getLevelMenus(
            Long level,
            List<Menu> menus,
            List<MenuDetailDTO> result,
            Map<MenuId, List<MenuAction>> matchedMenuActionsMap,
            Map<MenuId, List<MenuAction>> allActionsMap) {

        Map<String, MenuDetailDTO> meunsInLevel = menus.stream()
                .filter(c -> c.getLevel() == level)
                .map(m -> adaptToMenuDetailDTO(m, matchedMenuActionsMap.get(m.getId()), allActionsMap.get(m.getId())))
                .collect(Collectors.toMap(MenuDetailDTO::getId, c -> c));

        for (MenuDetailDTO c : result) {
            MenuDetailDTO parent = meunsInLevel.get(c.getParent());
            parent.addChild(c);
        }

        return meunsInLevel.values().stream()
                .sorted(MenuDetailDTO.ORDER_COMPARATOR)
                .collect(Collectors.toList());
    }
}
