package com.example.finallaptrinhweb.controller.user_page;


import com.example.finallaptrinhweb.controller.user_page.MailService.SendEmail;
import com.example.finallaptrinhweb.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/signup"
)
public class SignUp extends HttpServlet {
    public SignUp() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        SendEmail send = new SendEmail();
        String code = send.getRandomVerifyCode();
        String roleParam = request.getParameter("role");
        int roleId = Integer.parseInt(roleParam);
        if (repass.equals(pass)) {
            try {
                // Kiểm tra độ dài và thành phần của mật khẩu
                if (isStrongPassword(pass) && !UserDAO.getInstance().CheckExistUser(email)) {
                    UserDAO.getInstance().SignUp(name, email, pass, code, roleId);
                    if (send.sendVerifyCode(email, code)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("authcode", code);
                        response.sendRedirect("./verify.jsp");
                    }
                } else {
                    request.setAttribute("wrongInfor", "Mật khẩu phải có ít nhất 8 ký tự, bao gồm số, chữ in hoa và ký tự đặc biệt!");
                    request.getRequestDispatcher("./signUp.jsp").forward(request, response);
                }
            } catch (SQLException var10) {
                throw new RuntimeException(var10);
            }
        } else {
            request.setAttribute("wrongInfor", "Mật khẩu không trùng khớp!");
            request.getRequestDispatcher("./signUp.jsp").forward(request, response);
        }
    }

    // Phương thức kiểm tra xem mật khẩu có đủ mạnh không
    private boolean isStrongPassword(String s) {
        // Kiểm tra độ dài
        if (s.length() < 8) {
            return false;
        }

        // Kiểm tra chứa số
        if (!s.matches(".*\\d.*")) {
            return false;
        }

        // Kiểm tra chứa chữ in hoa
        if (!s.matches(".*[A-Z].*")) {
            return false;
        }

        // Kiểm tra chứa ký tự đặc biệt
        if (!s.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return false;
        }

        return true;
    }
}

