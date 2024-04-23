package com.models.ai.rest.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourcePaths {

    public static final String BASE_URL = "/ai/api/v1";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class SignUp {
        public static final String ENDPOINT_API_SIGNUP = BASE_URL + "/signup";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Users {

        public static final String ENDPOINT_API_USERS = BASE_URL + "/users";
        public static final String ENDPOINT_API_USERS_SEARCH = BASE_URL + "/users/search";
        public static final String ENDPOINT_API_USERS_ONE_USER = ENDPOINT_API_USERS + "/{user_id}";
        public static final String ENDPOINT_API_USERS_AUTHENTICATE = ENDPOINT_API_USERS + "/authenticate";
        public static final String ENDPOINT_API_UPDATE_STATUT = ENDPOINT_API_USERS_ONE_USER + "/statut";

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static final class PathVariales {
            public static final String USER_ID = "user_id";
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Passwords {

        public static final String ENDPOINT_API_PASSWORDS = BASE_URL + "/passwords";
        public static final String ENDPOINT_API_PASSWORDS_FORGOT = ENDPOINT_API_PASSWORDS + "/forgot";
        public static final String ENDPOINT_API_PASSWORDS_CHANGE = ENDPOINT_API_PASSWORDS + "/change";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Otp {

        public static final String ENDPOINT_API_OTP = BASE_URL + "/otp";
        public static final String ENDPOINT_API_OTP_VALIDATE = ENDPOINT_API_OTP + "/validate";
        public static final String ENDPOINT_API_OTP_REGENERATE = ENDPOINT_API_OTP + "/regenerate";

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static final class PathVariales {}
    }

    // -------------------------------------------------------------------------
    // ---------------------------------Referentiel-----------------------------
    // -------------------------------------------------------------------------

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Profiles {
        public static final String ENDPOINT_API_PROFILES = BASE_URL + "/profiles";
        public static final String ENDPOINT_API_PROFILES_PROFILE_WITH_MENU = BASE_URL + "/profiles/{profile_id}/menus";

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static final class PathVariales {
            public static final String PROFILE_ID = "profile_id";
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ProfilesMenusActions {
        public static final String ENDPOINT_API_PROFILES_MENUS_ACTIONS = BASE_URL + "/profiles-menus-actions";

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static final class PathVariales {}
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class StaticData {
        public static final String ENDPOINT_API_STATIC_DATA = BASE_URL + "/static_data";
        public static final String ENDPOINT_API_INTERNAL_STATIC_DATA = BASE_URL + "/internal_static_data";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class AppClients {

        public static final String ENDPOINT_API_APP_CLIENTS = BASE_URL + "/app-clients";
        public static final String ENDPOINT_API_APP_CLIENTS_ONE_APP = ENDPOINT_API_APP_CLIENTS + "/{app_clients_id}";

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static final class PathVariales {
            public static final String APP_CLIENTS_ID = "app_clients_id";
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class AiModels {

        public static final String ENDPOINT_API_AI_MODELS = BASE_URL + "/ai-models";
        public static final String ENDPOINT_API_AI_MODELS_ONE_AI = ENDPOINT_API_AI_MODELS + "/{ai_model_id}";
        public static final String ENDPOINT_API_AI_MODELS_RUN_ONE_AI = ENDPOINT_API_AI_MODELS_ONE_AI + "/run";

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static final class PathVariales {
            public static final String AI_MODEL_AI = "ai_model_id";
        }
    }
}
