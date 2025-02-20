package com.example.finallaptrinhweb.controller.user_page;


import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/signin")
public class SignIn extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        User user = null;

        boolean verifiedStatus;
        try {
            user = UserDAO.getInstance().CheckLogin(email, pass);
            verifiedStatus = UserDAO.getInstance().CheckVerifiedStatus(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (user != null && user.getRoleId() == 1) {
            if (verifiedStatus) {
                HttpSession session = request.getSession();
                session.setAttribute("auth", user);

                // Chuyển hướng đến trang index.jsp
                response.sendRedirect(request.getContextPath() + "/user/home");
            } else {
                request.setAttribute("wrongInfor", "Tài khoản chưa kích hoạt");
                request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("wrongInfor", "Đăng nhập thất bại hoặc bạn không có quyền truy cập");
            request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
        }
    }
}

