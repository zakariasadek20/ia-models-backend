package com.models.ai.domain.utils;

public interface ServiceDefaults {
    interface Security {
        interface ClientAuthorization {
            String accessTokenUri = null;
            String tokenServiceId = null;
            String clientId = null;
            String clientSecret = null;
        }

        interface Authentication {
            interface Jwt {
                String secret = null;
                String base64Secret =
                        "a2JrQVczVFZMRDJjZmkzd3drTFZvZl9TNmE5bHVLXzFSOEtNZnV4M2pVbm9OWlFTdWhuUk1PNm1PSVY0VGFzTk5YTU5aRFlMcGMzeWpUUUFHMGFIQk4zNkc1ajI0Y2w0LXRHZXBqdmNHT2dYZFpTOGNzSHlidXhRTHRHMlRHZkMzanpmb2FLaHNRQlJZbXZvdVFzMzBOTUNIU0R5Y2x1Sk51S28zaS1tY3pvc2lLQW9PTHRXQkNKeVZjampQcFFZVWlnalNjeHJQWkdZR3BRb1A2NnRBOWNPTTdXSFlyZWlQR0U5SFlWcjdYMFd0M0hlQTEwZF9ZYWZMTmJqTnFZUUp4NldTNGlRcGpxQnRoMnlwblo3ckp0VTdieXZrbnBHcVdTN1VLYUEwMm42THZRcDlSLWNkdElBOXNIMmV2VzJlc2RqdkdSTmdQOVpsQmF4OUxFTg==";
                long tokenValidityInSeconds = 3600L * 24L; // 3600 * 24;
                long tokenValidityInSecondsForRememberMe = 120L; // 2592000;
            }
        }

        interface RememberMe {
            String key = null;
        }
    }
}
