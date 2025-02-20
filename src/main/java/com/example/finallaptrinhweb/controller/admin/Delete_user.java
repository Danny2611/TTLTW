package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.UserDAOT;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/delete_user")

public class Delete_user extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID người dùng từ tham số URL
        String userIdParam = request.getParameter("id");

        if (userIdParam != null && !userIdParam.isEmpty()) {
            try {
                int userId = Integer.parseInt(userIdParam);

                boolean isDeleted = UserDAOT.deleteUserById(userId);

                if (isDeleted) {
                    // Nếu xóa thành công, chuyển hướng về trang danh sách người dùng
                    response.sendRedirect(request.getContextPath() + "/admin/users");
                } else {
                    // Nếu xóa không thành công, hiển thị thông báo lỗi
                    response.getWriter().println("Xóa người dùng không thành công.");
                }
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu không thể chuyển đổi ID thành số nguyên
                response.getWriter().println("ID người dùng không hợp lệ.");
            }
        } else {
            // Hiển thị thông báo nếu không có ID người dùng được cung cấp
            response.getWriter().println("Không có ID người dùng được cung cấp.");
        }
    }
}