package com.models.ai.rest.users.adapters;

import com.models.ai.domain.base.BaseEntity;
import com.models.ai.domain.referentiel.entitylabels.EntityLabel;
import com.models.ai.domain.referentiel.entitylabels.EntityLabelProvider;
import com.models.ai.domain.referentiel.entitylabels.EntityLabelType;
import com.models.ai.domain.users.User;
import com.models.ai.domain.users.UserId;
import com.models.ai.domain.users.UserProvider;
import com.models.ai.domain.users.menus.Menu;
import com.models.ai.domain.users.menus.MenuProvider;
import com.models.ai.domain.users.menus.menusactions.MenuAction;
import com.models.ai.domain.users.menus.menusactions.MenuActionProvider;
import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.domain.users.usersprofiles.UserProfileProvider;
import com.models.ai.rest.users.menus.adapters.MenuActionDTO;
import com.models.ai.rest.users.menus.adapters.MenuAdapter;
import com.models.ai.rest.users.menus.adapters.MenuDetailDTO;
import com.models.ai.rest.users.profiles.adapters.ProfileDetailDTO;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    private final UserProvider userProvider;
    private final UserProfileProvider userProfileProvider;
    private final MenuActionProvider menuActionProvider;
    private final MenuProvider menuProvider;
    private final MenuAdapter menuAdapter;
    private final EntityLabelProvider entityLabelProvider;

    public List<UserDTO> adaptUserToDtoWithInfo(List<User> users) {

        Set<UserId> userIds = users.stream().map(BaseEntity::getId).collect(Collectors.toSet());
        List<User> userInfos = userProvider.getBy(userIds);
        Map<UserId, User> userMap = userInfos.stream().collect(Collectors.toMap(u -> u.getId(), u -> u));

        Map<UserId, List<Profile>> userProfilesMap = userProfileProvider.getProfilesByUserIds(userIds);

        List<UserDTO> userDTOS = users.stream()
                .map(u -> {
                    UserStatutDTO userStatutDTO = UserStatutDTO.from(
                            u.getStatus().getCode(),
                            getLabels(EntityLabelType.USER_STATUS, u.getStatus().getCode()));
                    return UserDTO.from(u, userProfilesMap.get(u.getId()), userStatutDTO);
                })
                .collect(Collectors.toList());
        return userDTOS;
    }

    public UserDTO adaptUserToDTOWithInfo(User user) {

        List<Profile> profileList = userProfileProvider.getBy(user.getId());

        UserStatutDTO userStatutDTO = UserStatutDTO.from(
                user.getStatus().getCode(),
                getLabels(EntityLabelType.USER_STATUS, user.getStatus().getCode()));

        return UserDTO.from(user, profileList, userStatutDTO);
    }

    public UserDTO adaptUserToDtoWithMenu(User user) {
        List<Profile> profileList = userProfileProvider.getBy(user.getId());

        List<ProfileDetailDTO> profileDetailDTOS = new ArrayList<>();
        profileList.forEach(profile -> {
            List<MenuAction> menuActions = menuActionProvider.getBy(profile.getId());
            List<Menu> menus = menuProvider.getWithParentsBy(profile.getId());
            List<MenuDetailDTO> menuDetailDTOS = menuAdapter.adaptToMenuDetailDTO(menus, menuActions);

            Map<String, List<MenuActionDTO>> menuActionsMap = menuActions.stream()
                    .map(MenuActionDTO::new)
                    .collect(Collectors.groupingBy(MenuActionDTO::getMenuId));

            ProfileDetailDTO profileDetailDTO = ProfileDetailDTO.from(profile, menuDetailDTOS, menuActionsMap);

            profileDetailDTOS.add(profileDetailDTO);
        });

        UserDTO userDTO = adaptUserToDTOWithInfo(user);
        userDTO.addProfiles(profileDetailDTOS);
        return userDTO;
    }

    List<EntityLabel> getLabels(EntityLabelType entityLabelType, String id) {
        List<EntityLabel> labels = entityLabelProvider.getBy(entityLabelType, id);
        return ListUtils.emptyIfNull(labels);
    }
}
