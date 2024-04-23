package com.models.ai.domain.users.profiles;

public final class ProfileUtils {
    public static final String USER = "1000";
    public static final String ADMIN = "2000";

    public static boolean isUser(ProfileId profileId) {
        return ProfileId.from(USER).equals(profileId);
    }

    public static boolean isAdmin(ProfileId profileId) {
        return ProfileId.from(ADMIN).equals(profileId);
    }
}
