package com.models.ai.rest.users.menus.adapters;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class MenuDetailDTO extends MenuDTO {

    public static final Comparator<MenuDetailDTO> ORDER_COMPARATOR = (o1, o2) -> {
        if (o1.getLevel() < o2.getLevel()) {
            return -1;
        } else if (o1.getLevel() == o2.getLevel()) {
            return Long.compare(o1.getOrder(), o2.getOrder());
        } else {
            return 1;
        }
    };

    private boolean enabled;
    private List<MenuActionDTO> actions;
    private final List<MenuDetailDTO> children = new LinkedList<>();

    public void addChild(MenuDetailDTO c) {
        children.add(c);
        if (c.isEnabled()) {
            this.enabled = true;
        }
    }
}
