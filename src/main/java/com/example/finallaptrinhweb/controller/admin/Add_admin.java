package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.controller.user_page.ForgotPass;
import com.example.finallaptrinhweb.controller.user_page.MailService.SendEmail;
import com.example.finallaptrinhweb.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/admin/add-admin")
public class Add_admin extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        SendEmail send = new SendEmail();
        ForgotPass forgotPass = new ForgotPass();
        String password = forgotPass.generateRandomPassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String phone = request.getParameter("phone");
        send.sendPassword(email,password);
        try {
            // Thực hiện thêm admin vào cơ sở dữ liệu
            UserDAO.getInstance().addAdmin(username, email, hashedPassword);
            // Chuyển hướng đến trang thành công nếu thêm thành công
            response.sendRedirect("./add-admin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/user/error-404.html");
        }
    }
}
