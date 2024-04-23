package com.models.ai.rest.users;

import com.models.ai.domain.users.User;
import com.models.ai.domain.users.UserProvider;
import com.models.ai.rest.users.adapters.UserAdapter;
import com.models.ai.rest.users.adapters.UserDTO;
import com.models.ai.rest.utils.ApiResponse;
import com.models.ai.rest.utils.ApiResponseMetaData;
import com.models.ai.rest.utils.ResourcePaths;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "jwt")
public class UserController {

    private final UserProvider userProvider;
    private final UserAdapter userAdapter;

    @PostMapping(path = ResourcePaths.Users.ENDPOINT_API_USERS_SEARCH)
    public ResponseEntity<ApiResponse<List<UserDTO>>> getUsers() {
        log.debug("REST request to get Users by: {}");

        List<User> user = userProvider.getAll();

        Long totalusers = 0L;
        /*
        if (criteria.getSearchMetaCriteria().getWithCount()) {
            totalusers = userProvider.countBy(criteria);
        }*/
        ApiResponseMetaData apiResponseMetaData =
                ApiResponseMetaData.builder().total(totalusers).build();

        List<UserDTO> userDTO = userAdapter.adaptUserToDtoWithInfo(user);

        return ResponseEntity.ok(ApiResponse.ok(userDTO, apiResponseMetaData));
    }
}
