//package com.example.finallaptrinhweb.controller.admin;
//
//import com.example.finallaptrinhweb.dao.UserDAO;
//import com.example.finallaptrinhweb.model.User;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/admin/signin")
//public class SignIn extends HttpServlet {
//    // ... (các phương thức khác)
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String email = request.getParameter("email");
//        String pass = request.getParameter("password");
//        User user = null;
//
//        boolean verifiedStatus;
//        try {
//            user = UserDAO.getInstance().CheckLogin(email, pass);
//            verifiedStatus = UserDAO.getInstance().CheckVerifiedStatus(email);
//        } catch (SQLException var8) {
//            throw new RuntimeException(var8);
//        }
//
//        if (user != null) {
//            if (verifiedStatus) {
//                int roleId = user.getRoleId();
//
//                // Kiểm tra xem roleId có phải là admin hay không (ví dụ, roleId = 2 là admin)
//                if (roleId == 2) {
//                    // Lưu thông tin user vào session
//                    HttpSession session = request.getSession();
//                    session.setAttribute("auth", user);
//
//                    // Chuyển hướng đến trang dashboard.jsp
//                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
//                } else {
//                    // Nếu roleId không phải là admin, chuyển hướng đến trang không có quyền truy cập
//                    response.sendRedirect(request.getContextPath() + "/user/error-404.html");
//                }
//            } else {
//                request.setAttribute("wrongInfor", "Tài khoản chưa kích hoạt");
//                request.getRequestDispatcher("./signIn.jsp").forward(request, response);
//            }
//        } else {
//            request.setAttribute("wrongInfor", "Đăng nhập thất bại");
//            request.getRequestDispatcher("./signIn.jsp").forward(request, response);
//        }
//    }
//}