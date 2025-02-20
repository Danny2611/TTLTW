
package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.SupplierDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/delete-supplier")
public class Delete_supplier extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID của nhà cung cấp từ tham số truyền vào
        int supplierId = Integer.parseInt(request.getParameter("id"));

        // Gọi phương thức xóa nhà cung cấp từ SupplierDAO
        boolean isDeleted = SupplierDAO.deleteSupplier(supplierId);

        // Chuyển hướng lại trang danh sách nhà cung cấp
        if (isDeleted) {
            response.sendRedirect(request.getContextPath() + "/admin/supplier");
        } else {
            // Xử lý lỗi xóa, có thể chuyển hướng hoặc hiển thị thông báo lỗi
            response.getWriter().println("Failed to delete supplier.");
        }
    }
}
