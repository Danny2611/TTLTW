package com.example.finallaptrinhweb.controller.user_page;


import com.example.finallaptrinhweb.controller.user_page.GoogleService.AuthService;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/loginbygoogle")
public class GoogleLoginServlet extends HttpServlet {
    private final AuthService authService;

    public GoogleLoginServlet() {
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy authorization code từ callback của Google
        String code = request.getParameter("code");

        try {
            // Xử lý đăng nhập bằng Google
            User user = authService.processGoogleLogin(code);

            // Lưu thông tin người dùng vào session
            HttpSession session = request.getSession();
            session.setAttribute("auth", user);

            // Chuyển hướng đến trang chủ
            response.sendRedirect("./home");
        } catch (Exception e) {
            // Xử lý lỗi
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đăng nhập không thành công: " + e.getMessage());
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }
    }
}
