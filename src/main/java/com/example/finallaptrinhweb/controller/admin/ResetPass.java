package com.example.finallaptrinhweb.controller.admin;

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

@WebServlet("/admin/resetpassword")
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

        try {
            if (!UserDAO.getInstance().checkPassword(oldPassword, UserDAO.getInstance().getPassword(user.getEmail()))) {
                request.setAttribute("wrongInfor", "Mật khẩu cũ không đúng!");
                request.getRequestDispatcher("./user_info.jsp").forward(request, response);
            } else if (!confirmPassword.equals(newPassword)) {
                request.setAttribute("wrongInfor", "Mật khẩu mới không trùng khớp!");
                request.getRequestDispatcher("./user_info.jsp").forward(request, response);
            } else {
                // Kiểm tra mật khẩu mới, có thể thêm điều kiện phức tạp hơn
                if (isValidPassword(newPassword)) {
                    UserDAO.getInstance().updatePassword(user.getEmail(), newPassword);
                    request.setAttribute("wrongInfor", "Mật khẩu đã được thay đổi");
                    request.getRequestDispatcher("./admin-profile.jsp").forward(request, response);
                } else {
                    request.setAttribute("wrongInfor", "Mật khẩu mới không đủ mạnh!");
                    request.getRequestDispatcher("./admin-profile.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            // Xử lý lỗi, có thể in log hoặc redirect đến trang lỗi
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private boolean isValidPassword(String password) {
        // Thêm điều kiện kiểm tra mật khẩu mới ở đây
        // Ví dụ: độ dài, chứa ký tự đặc biệt, số, và chữ cái in hoa và thường
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[A-Z].*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }
}
