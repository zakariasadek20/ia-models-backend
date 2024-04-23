package com.models.ai.domain.users.menus.menusactions;

import com.models.ai.domain.base.BaseAuditedEntity;
import com.models.ai.domain.users.menus.MenuId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "menu_action")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@SuperBuilder
@EqualsAndHashCode(
        callSuper = true,
        of = {})
public class MenuAction extends BaseAuditedEntity<MenuActionId> {
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "menu_id")),
    })
    private MenuId menuId;

    @Column(name = "code")
    private String code;

    @Column(name = "resource")
    private String resource;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MenuActionStatus status;

    public MenuAction() {
        this.id = new MenuActionId();
        this.status = MenuActionStatus.ENABLED;
    }
}
