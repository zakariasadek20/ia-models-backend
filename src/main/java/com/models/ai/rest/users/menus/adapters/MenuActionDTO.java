package com.models.ai.rest.users.menus.adapters;

import com.models.ai.domain.users.menus.menusactions.MenuAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuActionDTO {

    private String id;
    private String menuId;
    private String code;
    private String resource;
    private String status;
    private boolean enabled;

    public MenuActionDTO(MenuAction menuAction) {
        this.id = menuAction.getId().getValue();
        this.menuId = menuAction.getMenuId().toString();
        this.code = menuAction.getCode();
        this.resource = menuAction.getResource();
        this.status = menuAction.getStatus().name();
        this.enabled = false;
    }

    public MenuActionDTO(MenuAction menuAction, boolean enabled) {
        this(menuAction);
        this.enabled = enabled;
    }
}
