package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.controller.user_page.ForgotPass;
import com.example.finallaptrinhweb.controller.user_page.MailService.SendEmail;
import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/admin/add-admin")
public class Add_admin extends HttpServlet {
    private static  final  Logger logger = Logger.getLogger(Add_admin.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Lấy dữ liệu từ form
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("adminAuth");

        String username = request.getParameter("username");
        System.out.println("username "+ username);
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        System.out.println("role " + role);
        SendEmail send = new SendEmail();
        ForgotPass forgotPass = new ForgotPass();
        String password = forgotPass.generateRandomPassword();
        System.out.println("password:" + password);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String phone = request.getParameter("phone");

        try {
            // Thực hiện thêm admin vào cơ sở dữ liệu
            UserDAO.getInstance().addAdmin(username, email, hashedPassword, Integer.parseInt(role));
            boolean result= send.sendPassword(email,password);

            System.out.println("Kết quả gửi email: " + result);
            logger.info("User: "+user.getEmail()+ " Add admin "+ email +" successfully");
            // Chuyển hướng đến trang thành công nếu thêm thành công
            response.sendRedirect("./list-admin");
        } catch (SQLException e) {
            logger.error("User: "+user.getEmail()+ " Add admin "+ email +" failure");
            e.printStackTrace();
            response.sendRedirect("/user/error-404.html");
        }
    }
}
