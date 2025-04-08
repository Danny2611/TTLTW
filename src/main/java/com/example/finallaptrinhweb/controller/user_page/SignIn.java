package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.log.Log;
import com.example.finallaptrinhweb.model.User;
import com.example.finallaptrinhweb.session.SessionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.SQLException;

@WebServlet("/user/signin")
public class SignIn extends HttpServlet {
    private static  final Logger logger = Logger.getLogger(SignIn.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        User user = null;

        // Kiểm tra xem tài khoản có đang bị khóa không
        System.out.println("isLocked: "+ UserDAO.getInstance().isLocked(email));
        if (UserDAO.getInstance().isLocked(email)) {
            request.setAttribute("wrongInfor", "Tài khoản của bạn đang bị khóa. Vui lòng thử lại sau 5 phút.");
            request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
            return;
        }

        int currentRemaining = UserDAO.getInstance().getRemaining(email);
        try {
            user = UserDAO.getInstance().CheckLogin(email, pass);
        } catch (SQLException e) {
            logger.error("ERR in login with "+ e);
            throw new RuntimeException(e);
        }

        if (currentRemaining > 0) {
            boolean verifiedStatus;
            try {
                verifiedStatus = UserDAO.getInstance().CheckVerifiedStatus(email);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (user != null) {
                UserDAO.getInstance().resetRemain(user.getId()); // Reset số lần nhập sai
                HttpSession session = request.getSession();
                SessionManager.addSession(user.getId(), session);
//                Log.infor(user.getId(), "Login Controller", "", user.toString());
                logger.info("User " + user.getUsername() + " Login Successfully");
                if (user.getRoleId() == 1) {
                    if (verifiedStatus) {
                        session.setAttribute("auth", user);
                        response.sendRedirect(request.getContextPath() + "/user/home");
                        return;
                    } else {
                        request.setAttribute("wrongInfor", "Tài khoản chưa kích hoạt.");
                    }
                } else {
                    session.setAttribute("adminAuth", user);
                    switch (user.getRoleId()) {
                        case 2 -> response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                        case 3 -> response.sendRedirect(request.getContextPath() + "/admin/product");
                        case 4 -> response.sendRedirect(request.getContextPath() + "/admin/total-report");
                        case 5 -> response.sendRedirect(request.getContextPath() + "/admin/contact");
                    }
                    return;
                }
            }

            // Nếu đăng nhập thất bại
            UserDAO.getInstance().updateRemaining(email, currentRemaining - 1);
            int newRemaining = UserDAO.getInstance().getRemaining(email); // Lấy lại số lần thử mới nhất

            if (newRemaining == 0) {
                UserDAO.getInstance().lockAccount(email);
                logger.warn("User with email " +email  + "Login Fail");
                request.setAttribute("wrongInfor", "Bạn đã nhập sai quá nhiều lần. Tài khoản sẽ bị khóa trong 5 phút.");
            } else {
                request.setAttribute("wrongInfor", "Đăng nhập thất bại. Bạn còn " + newRemaining + " lần thử.");
            }
            System.out.println("Số lần đăng nhập còn lại: " + newRemaining);

        } else {
            if (UserDAO.getInstance().isLocked(email) == false && user != null) {
                UserDAO.getInstance().resetRemain(user.getId());
                redirect(user, request,response);
            }
            logger.warn("Email " + email + " Login Fail");
            request.setAttribute("wrongInfor", "Bạn tạm thời không thể đăng nhập. Hãy thử lại sau 5 phút.");
        }

        request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
    }

    private void redirect(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (user.getRoleId() == 1) {
            session.setAttribute("auth", user);
            response.sendRedirect(request.getContextPath() + "/user/home");
        } else {
            session.setAttribute("adminAuth", user);
            String path = switch (user.getRoleId()) {
                case 2 -> "/admin/dashboard";
                case 3 -> "/admin/product";
                case 4 -> "/admin/total-report";
                case 5 -> "/admin/contact";
                default -> "/admin";
            };
            response.sendRedirect(request.getContextPath() + path);
        }
    }
}
