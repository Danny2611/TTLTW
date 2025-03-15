package com.example.finallaptrinhweb.controller.user_page;


import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.model.User;
import com.example.finallaptrinhweb.session.SessionManager;
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

        HttpSession session = request.getSession();
        SessionManager.addSession(user.getId(),session);
        if (user != null && user.getRoleId() == 1) {
            if (verifiedStatus) {
                session.setAttribute("auth", user);
                // Chuyển hướng đến trang index.jsp
                response.sendRedirect(request.getContextPath() + "/user/home");
            } else {
                request.setAttribute("wrongInfor", "Tài khoản chưa kích hoạt");
                request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
            }
        } else if (user != null ) {
            session.setAttribute("adminAuth", user);
            if(user.getRoleId() == 2) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            }
            else if(user.getRoleId() ==3) {
                response.sendRedirect(request.getContextPath() + "/admin/product");
            } else if (user.getRoleId() == 4) {
                response.sendRedirect(request.getContextPath() + "/admin/total-report");
            } else if (user.getRoleId() == 5) {
                response.sendRedirect(request.getContextPath() + "/admin/contact");

            }
        }

        else {
            request.setAttribute("wrongInfor", "Đăng nhập thất bại hoặc bạn không có quyền truy cập");
            request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
        }
    }
}

