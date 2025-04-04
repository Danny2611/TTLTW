package com.example.finallaptrinhweb.controller.user_page.GoogleService;



import com.example.finallaptrinhweb.auth.oauth.GoogleOAuthService;
import com.example.finallaptrinhweb.auth.oauth.GoogleUserInfo;
import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.model.User;

import java.io.IOException;
import java.sql.SQLException;

public class AuthService {
    private final GoogleOAuthService googleOAuthService;
    private final UserDAO userDAO;

    public AuthService() {
        this.googleOAuthService = new GoogleOAuthService();
        this.userDAO = UserDAO.getInstance();
    }

    // Xử lý đăng nhập bằng Google
    public User processGoogleLogin(String authorizationCode) throws IOException, SQLException {
        // Lấy access token
        String accessToken = googleOAuthService.getAccessToken(authorizationCode);

        // Lấy thông tin người dùng từ Google
        GoogleUserInfo googleUserInfo = googleOAuthService.getUserInfo(accessToken);

        // Kiểm tra người dùng đã tồn tại chưa
        boolean userExists = userDAO.CheckExistUser(googleUserInfo.getEmail());

        // Nếu không tồn tại, tiến hành đăng ký
        if (!userExists) {
            registerGoogleUser(googleUserInfo);
        }

        // Trả về thông tin người dùng từ database
        return userDAO.GetInfor(googleUserInfo.getEmail());
    }

    // Đăng ký người dùng từ Google
    private void registerGoogleUser(GoogleUserInfo googleUserInfo) throws SQLException {
        String username = googleUserInfo.getGivenName() != null ?
                googleUserInfo.getGivenName() :
                googleUserInfo.getName().split(" ")[0];

        userDAO.SignUp(
                username,
                googleUserInfo.getEmail(),
                null,  // không cần password vì đăng nhập qua Google
                "verified",
                1  // mặc định là user role
        );
    }
}