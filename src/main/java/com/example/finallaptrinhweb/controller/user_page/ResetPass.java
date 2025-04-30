package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/resetpassword")
public class ResetPass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String oldPassword = request.getParameter("pass");
        String newPassword = request.getParameter("newpass");
        String confirmPassword = request.getParameter("renewpass");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        boolean hasError = false;
        Map<String, String> errors = new HashMap<>();
        try {
            String currentPasswordHashed = UserDAO.getInstance().getPassword(user.getEmail());

            if (!UserDAO.getInstance().checkPassword(oldPassword, currentPasswordHashed)) {
                errors.put("oldPassError", "Mật khẩu cũ không đúng!");
                hasError = true;
            }
            if (!isValidLength(newPassword)) {
                errors.put("newPassError", "Mật khẩu phải có ít nhất 8 ký tự.");
                hasError = true;
            } else if (!hasUppercase(newPassword)) {
                errors.put("newPassError", "Mật khẩu phải chứa ít nhất 1 chữ cái in hoa.");
                hasError = true;
            } else if (!hasDigit(newPassword)) {
                errors.put("newPassError", "Mật khẩu phải chứa ít nhất 1 chữ số.");
                hasError = true;
            } else if (!hasSpecialChar(newPassword)) {
                errors.put("newPassError", "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt.");
                hasError = true;
            }
            if (!confirmPassword.equals(newPassword)) {
                errors.put("reNewPassError", "Mật khẩu xác nhận không trùng khớp.");
                hasError = true;
            }
            if (hasError) {
                if (isAjaxRequest(request)) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    JSONObject json = new JSONObject();
                    json.put("status", "error");
                    json.put("message", "Cập nhật mật khẩu thất bại");
                    json.put("errors", errors);
                    response.getWriter().write(json.toString());
                } else {
                    for (Map.Entry<String, String> entry : errors.entrySet()) {
                        request.setAttribute(entry.getKey(), entry.getValue());
                    }
                    request.getRequestDispatcher("./user_info.jsp").forward(request, response);
                }
            } else {
                UserDAO.getInstance().updatePassword(user.getEmail(), newPassword);
                if (isAjaxRequest(request)) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    json.put("message", "Mật khẩu đã được thay đổi thành công");
                    response.getWriter().write(json.toString());
                } else {
                    request.setAttribute("successMessage", "Mật khẩu đã được thay đổi");
                    request.getRequestDispatcher("./user_info.jsp").forward(request, response);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
    private boolean isValidLength(String password) {
        return password.length() >= 8;
    }
    private boolean hasUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }
    private boolean hasDigit(String password) {
        return password.matches(".*\\d.*");
    }
    private boolean hasSpecialChar(String password) {
        return password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }

}
