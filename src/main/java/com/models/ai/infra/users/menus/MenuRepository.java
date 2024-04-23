package com.models.ai.infra.users.menus;

import com.models.ai.domain.users.menus.Menu;
import com.models.ai.domain.users.menus.MenuId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, MenuId> {}
