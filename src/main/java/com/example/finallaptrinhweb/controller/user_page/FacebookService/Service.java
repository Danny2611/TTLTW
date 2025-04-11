package com.example.finallaptrinhweb.controller.user_page.FacebookService;

import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.db.DbProperties;
import com.example.finallaptrinhweb.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.sql.SQLException;

public class Service {

    public static String getToken(String code) throws ClientProtocolException, IOException {
        String url = DbProperties.FACEBOOK_ACCESS_TOKEN_URL +
                "?client_id=" + DbProperties.FACEBOOK_APP_ID +
                "&redirect_uri=" + DbProperties.FACEBOOK_REDIRECT_URI +
                "&client_secret=" + DbProperties.FACEBOOK_APP_SECRET +
                "&code=" + code;

        String response = Request.Get(url)
                .execute()
                .returnContent()
                .asString();

        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        return jsonResponse.get("access_token").getAsString();
    }

    public static User getUserInfo(String accessToken) throws ClientProtocolException, IOException, SQLException {
        String url = DbProperties.FACEBOOK_GRAPH_API_URL + "&access_token=" + accessToken;

        String response = Request.Get(url)
                .execute()
                .returnContent()
                .asString();

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        // Đảm bảo id phù hợp với database
        jsonObject.addProperty("id", UserDAO.getInstance().GetId());

        // Sử dụng tên đầy đủ làm username
        String name = jsonObject.get("name").getAsString();
        jsonObject.addProperty("username", name);

        // Map dữ liệu vào đối tượng User
        User facebookUser = new Gson().fromJson(jsonObject, User.class);
        return facebookUser;
    }
}