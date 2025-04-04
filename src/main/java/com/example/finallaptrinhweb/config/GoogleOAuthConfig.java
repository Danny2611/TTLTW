package com.example.finallaptrinhweb.config;

import com.example.finallaptrinhweb.db.DbProperties;

public class GoogleOAuthConfig {
    // Các hằng số cấu hình OAuth từ DbProperties
    public static final String CLIENT_ID = DbProperties.GOOGLE_CLIENT_ID;
    public static final String CLIENT_SECRET = DbProperties.GOOGLE_CLIENT_SECRET;
    public static final String REDIRECT_URI = DbProperties.GOOGLE_REDIRECT_URI;
    public static final String AUTH_ENDPOINT = DbProperties.AUTH_ENDPOINT;
    public static final String TOKEN_ENDPOINT = DbProperties.GOOGLE_LINK_GET_TOKEN;
    public static final String USER_INFO_ENDPOINT = DbProperties.GOOGLE_LINK_GET_USER_INFO;

    // Phương thức tạo URL xác thực
    public static String getAuthorizationUrl() {
        return AUTH_ENDPOINT +
                "?scope=profile%20email" +
                "&redirect_uri=" + REDIRECT_URI +
                "&response_type=code" +
                "&client_id=" + CLIENT_ID +
                "&prompt=select_account";
    }
}
