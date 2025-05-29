package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.controller.user_page.GoogleService.Service;
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
import java.sql.SQLException;

@WebServlet("/user/loginbygoogle")
public class LogInByGoogle extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogInByGoogle.class);

    public LogInByGoogle() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            response.sendRedirect("./login");
            return;
        }

        try {
            String accessToken = Service.getToken(code);
            User user = Service.getUserInfo(accessToken);

            if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
                logger.warn("Failed to get valid user information from Google");
                request.setAttribute("wrongInfor", "Không thể lấy thông tin từ Google. Vui lòng thử lại.");
                request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
                return;
            }

            // Kiểm tra tài khoản có bị khóa không
            if (UserDAO.getInstance().isLocked(user.getEmail())) {
                logger.warn("Google login attempt with locked account: " + user.getEmail());
                request.setAttribute("wrongInfor", "Tài khoản của bạn đang bị khóa. Vui lòng thử lại sau 5 phút.");
                request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
                return;
            }

            boolean userExists = UserDAO.getInstance().CheckExistUser(user.getEmail());

            if (!userExists) {
                // Đăng ký người dùng mới từ Google (verified và role_id = 1)
                UserDAO.getInstance().SignUp(user.getUsername(), user.getEmail(), null, "verified", 1);
                logger.info("New user registered via Google: " + user.getEmail());
            }

            // Lấy thông tin đầy đủ của user từ database
            User dbUser = UserDAO.getInstance().GetInforByEmail(user.getEmail());

            // Kiểm tra trạng thái verify
            boolean verifiedStatus = UserDAO.getInstance().CheckVerifiedStatus(dbUser.getEmail());
            if (!verifiedStatus) {
                logger.warn("Google login attempt with unverified account: " + dbUser.getEmail());
                request.setAttribute("wrongInfor", "Tài khoản chưa kích hoạt.");
                request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
                return;
            }

            // Reset số lần nhập sai nếu có
            UserDAO.getInstance().resetRemain(dbUser.getId());

            // Quản lý session
            HttpSession session = request.getSession();
            SessionManager.addSession(dbUser.getId(), session);

            // Ghi log đăng nhập thành công
            logger.info("User " + dbUser.getUsername() + " logged in via Google successfully");
            // Log.infor(dbUser.getId(), "Google Login", "", dbUser.toString());

            // Xử lý chuyển hướng dựa trên role
            redirect(dbUser, request, response);

        } catch (SQLException e) {
            logger.error("Error during Google login: " + e.getMessage(), e);
            request.setAttribute("wrongInfor", "Đã xảy ra lỗi khi đăng nhập bằng Google. Vui lòng thử lại.");
            request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Unexpected error during Google login: " + e.getMessage(), e);
            request.setAttribute("wrongInfor", "Đã xảy ra lỗi không xác định. Vui lòng thử lại sau.");
            request.getRequestDispatcher("/user/signIn.jsp").forward(request, response);
        }
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