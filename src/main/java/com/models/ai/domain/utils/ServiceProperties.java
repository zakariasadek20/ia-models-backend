package com.models.ai.domain.utils;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProperties {
    private Params params = new Params();
    private Properties properties = new Properties();
    private Logs logs = new Logs();
    private Security security = new Security();
    private Twilio twilio = new Twilio();
    private Atrait atrait = new Atrait();
    private SmsConnect smsConnect = new SmsConnect();
    private DateFinEligibilite dateFinEligibilite = new DateFinEligibilite();

    @Getter
    @Setter
    public static class Logs {
        private String folder;
    }

    @Getter
    @Setter
    public static class Params {
        private Long otpValidityInSeconds;
        private Long otpGenerationLimitTimeMinute;
        private Long otpGenerationLimit;
        private String documentsDirectory;
        private Long passwordDuration;
    }

    @Getter
    @Setter
    public static class Properties {
        private Monitoring monitoring = new Monitoring();

        @Getter
        @Setter
        public static class Monitoring {
            private boolean performance;
            private boolean inputAndOutput;
        }
    }

    @Getter
    @Setter
    public static class Twilio {
        private String accountSid;
        private String authToken;
        private String phoneNumber;
    }

    @Getter
    @Setter
    public static class Atrait {
        private String username;
        private String pass;
        private String authUrl;
        private String smsSendUrl;
    }

    @Getter
    @Setter
    public static class SmsConnect {
        private SmsConnectLoginPassword loginPassword;

        private String url;
        private String oadc;

        @Getter
        @Setter
        public static class SmsConnectLoginPassword {
            private String name;
            private String login;
            private String password;
        }
    }

    @Getter
    @Setter
    public static class DateFinEligibilite {
        private Long dateFinValidityInMonths;
        private Long dateFinValidityInDays;
    }

    @Getter
    @Setter
    public static class Security {
        private final Authentication authentication = new Authentication();

        private final RememberMe rememberMe = new RememberMe();

        @Getter
        @Setter
        public static class Authentication {
            private final Jwt jwt = new Jwt();

            @Getter
            @Setter
            public static class Jwt {
                private String secret = ServiceDefaults.Security.Authentication.Jwt.secret;

                private String base64Secret = ServiceDefaults.Security.Authentication.Jwt.base64Secret;

                private long tokenValidityInSeconds =
                        ServiceDefaults.Security.Authentication.Jwt.tokenValidityInSeconds;

                private long tokenValidityInSecondsForRememberMe =
                        ServiceDefaults.Security.Authentication.Jwt.tokenValidityInSecondsForRememberMe;
            }
        }

        public static class RememberMe {
            @NotNull
            private String key = ServiceDefaults.Security.RememberMe.key;
        }
    }
}
