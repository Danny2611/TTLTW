package com.example.finallaptrinhweb.auth.oauth;

import com.example.finallaptrinhweb.config.GoogleOAuthConfig;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

// Class này để xử lý việc gọi API Google OAuth
public class GoogleOAuthService {

    // Phương thức để lấy access token từ Google
    public String getAccessToken(String authorizationCode) throws IOException {
        String response = Request.Post(GoogleOAuthConfig.TOKEN_ENDPOINT)
                .bodyForm(Form.form()
                        .add("client_id", GoogleOAuthConfig.CLIENT_ID)
                        .add("client_secret", GoogleOAuthConfig.CLIENT_SECRET)
                        .add("redirect_uri", GoogleOAuthConfig.REDIRECT_URI)
                        .add("code", authorizationCode)
                        .add("grant_type", "authorization_code")
                        .build())
                .execute()
                .returnContent()
                .asString();

        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        return jsonResponse.get("access_token").getAsString();
    }

    // Phương thức để lấy thông tin người dùng từ Google
    public GoogleUserInfo getUserInfo(String accessToken) throws IOException {
        String response = Request.Get(GoogleOAuthConfig.USER_INFO_ENDPOINT + accessToken)
                .execute()
                .returnContent()
                .asString();

        return new Gson().fromJson(response, GoogleUserInfo.class);
    }
}
