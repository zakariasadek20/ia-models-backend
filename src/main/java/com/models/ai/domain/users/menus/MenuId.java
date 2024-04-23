package com.models.ai.domain.users.menus;

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
public class MenuId extends Identity {

    @Column(name = "id")
    private final String value;

    public MenuId() {
        super();
        this.value = UUID.randomUUID().toString();
    }

    private MenuId(String value) {
        super();
        this.value = value;
    }

    public static MenuId from(String value) {
        if (value == null) return null;
        return new MenuId(value);
    }

    @Override
    protected void validate() throws DomainException {}

    @Override
    public String toString() {
        return value.toString();
    }
}
