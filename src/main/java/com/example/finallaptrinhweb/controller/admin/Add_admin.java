package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        String password = request.getParameter("pass");
        String phone = request.getParameter("phone");
        try {
            // Thực hiện thêm admin vào cơ sở dữ liệu
            UserDAO.getInstance().addAdmin(username, email, password);
            // Chuyển hướng đến trang thành công nếu thêm thành công
            response.sendRedirect("./add-admin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/user/error-404.html");
        }
    }
}
