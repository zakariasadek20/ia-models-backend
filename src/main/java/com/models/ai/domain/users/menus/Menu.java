package com.models.ai.domain.users.menus;

import com.models.ai.domain.base.BaseAuditedEntity;
import jakarta.persistence.*;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "Menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@SuperBuilder
@EqualsAndHashCode(
        callSuper = true,
        of = {})
public class Menu extends BaseAuditedEntity<MenuId> {

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "i18n_code")
    private String i18nCode;

    @Column(name = "icon")
    private String icon;

    @Column(name = "link")
    private String link;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "parent"))})
    private MenuId parentId;

    @Column(name = "level_")
    private long level;

    @Column(name = "order_")
    private long order;

    @Column(name = "global_order")
    private String globalOrder;

    @Column(name = "visible")
    protected Boolean visible;

    public Menu() {
        super();
        this.id = new MenuId();
    }

    public Optional<MenuId> getParentId() {
        return Optional.ofNullable(parentId);
    }
}
