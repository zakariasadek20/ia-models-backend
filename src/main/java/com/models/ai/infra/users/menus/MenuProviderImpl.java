package com.models.ai.infra.users.menus;

import com.models.ai.domain.users.menus.Menu;
import com.models.ai.domain.users.menus.MenuId;
import com.models.ai.domain.users.menus.MenuProvider;
import com.models.ai.domain.users.menus.menusactions.MenuAction;
import com.models.ai.domain.users.menus.menusactions.MenuActionProvider;
import com.models.ai.domain.users.profiles.ProfileId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class MenuProviderImpl implements MenuProvider {

    private static final String MENU_CACHE = "MENU_CACHE";

    private final MenuActionProvider menuActionProvider;
    private final EntityManager entityManager;

    @Override
    @Cacheable(cacheNames = MENU_CACHE, key = "'MenuWithParents_' + #profileId")
    public List<Menu> getWithParentsBy(ProfileId profileId) {
        List<MenuAction> menuActions = menuActionProvider.getBy(profileId);
        Set<String> menuIds = menuActions.stream()
                .map(MenuAction::getMenuId)
                .map(MenuId::getValue)
                .collect(Collectors.toSet());

        String sqlQuery = "WITH recursive ancestors(ID, PARENT) AS ( "
                + "  SELECT ID, PARENT FROM MENU WHERE ID in (:menus)"
                + "    UNION ALL  "
                + "  SELECT S2.ID, S2.parent  "
                + "   FROM ancestors S1 INNER JOIN Menu S2 ON S1.parent = S2.ID  "
                + ")  "
                + " SELECT distinct m.* FROM ancestors a, menu m  "
                + " where m.id = a.ID  "
                + " order by global_order";

        Query query = entityManager.createNativeQuery(sqlQuery, Menu.class);

        query.setParameter("menus", menuIds);

        List<Menu> menus = (List<Menu>) query.getResultList();

        return menus;
    }

    @Override
    @Cacheable(cacheNames = MENU_CACHE)
    public List<Menu> getAllMenu() {

        String sqlQuery = "WITH recursive ancestors(ID, PARENT) AS ( "
                + "  SELECT ID, PARENT FROM MENU"
                + "    UNION ALL  "
                + "  SELECT S2.ID, S2.parent  "
                + "   FROM ancestors S1 INNER JOIN Menu S2 ON S1.parent = S2.ID  "
                + ")  "
                + " SELECT distinct m.* FROM ancestors a, menu m  "
                + " where m.id = a.ID  "
                + " order by global_order";

        Query query = entityManager.createNativeQuery(sqlQuery, Menu.class);

        List<Menu> menus = (List<Menu>) query.getResultList();

        return menus;
    }
}
