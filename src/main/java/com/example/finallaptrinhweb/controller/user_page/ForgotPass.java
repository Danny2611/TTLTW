package com.example.finallaptrinhweb.controller.user_page;


import com.example.finallaptrinhweb.controller.user_page.MailService.SendEmail;
import com.example.finallaptrinhweb.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/user/forgotpassword")
public class ForgotPass extends HttpServlet {
    public ForgotPass() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identifier = request.getParameter("identifier");
        SendEmail send = new SendEmail();

        try {
            if (UserDAO.getInstance().CheckExistUser(identifier)) {
                // Tạo mật khẩu ngẫu nhiên
                String newPassword = generateRandomPassword();
                // Lưu mật khẩu mới đã mã hóa vào CSDL
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                UserDAO.getInstance().resetPassword(identifier, hashedPassword);

                // Gửi mật khẩu mới đến email của người dùng
                if (  identifier.contains("@")) {
                    if (send.sendPassword(identifier, newPassword)) {
                        response.sendRedirect("./signIn.jsp");
                    }
                }else{

                }

            } else {
                request.setAttribute("wrongInfor", "Tài khoản không tồn tại !");
                request.getRequestDispatcher("./forgotPass.jsp").forward(request, response);
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }
    // Phương thức tạo mật khẩu ngẫu nhiên
    public String generateRandomPassword() {
        // Đây chỉ là một cách đơn giản, bạn có thể thay đổi theo nhu cầu
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}

