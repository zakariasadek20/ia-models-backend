package com.models.ai.infra.users.menus.menusactions;

import com.models.ai.domain.users.menus.menusactions.MenuAction;
import com.models.ai.domain.users.menus.menusactions.MenuActionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuActionRepository extends JpaRepository<MenuAction, MenuActionId> {}
