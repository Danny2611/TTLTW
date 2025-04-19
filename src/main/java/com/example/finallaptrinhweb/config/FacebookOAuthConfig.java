package com.example.finallaptrinhweb.config;

import com.example.finallaptrinhweb.db.DbProperties;

public class FacebookOAuthConfig {
    public static final String APP_ID = DbProperties.FACEBOOK_APP_ID;
    public static final String APP_SECRET = DbProperties.FACEBOOK_APP_SECRET;
    public static final String REDIRECT_URI = DbProperties.FACEBOOK_REDIRECT_URI;
    public static final String DIALOG_OAUTH = DbProperties.FACEBOOK_DIALOG_OAUTH;
    public static final String ACCESS_TOKEN_URL = DbProperties.FACEBOOK_ACCESS_TOKEN_URL;
    public static final String GRAPH_API_URL = DbProperties.FACEBOOK_GRAPH_API_URL;
    // Phương thức tạo URL xác thực
    public static String getAuthorizationUrl() {
        String url = DIALOG_OAUTH +
                "?client_id=" + APP_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&response_type=code" +
                "&scope=email,public_profile";
        System.out.println("Facebook OAuth URL: " + url); // In ra console
        return url;
    }
}
