package com.models.ai.domain.users.menus.menusactions;

import com.models.ai.domain.base.Identity;
import com.models.ai.domain.common.exceptions.DomainException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Embeddable
@EqualsAndHashCode(callSuper = false)
public class MenuActionId extends Identity {
    @Column(name = "id")
    private final String value;

    public MenuActionId() {
        super();
        this.value = UUID.randomUUID().toString();
    }

    private MenuActionId(String value) {
        super();
        this.value = value;
    }

    public static MenuActionId from(String value) {
        return new MenuActionId(value);
    }

    @Override
    protected void validate() throws DomainException {}

    @Override
    public String toString() {
        return value;
    }
}
