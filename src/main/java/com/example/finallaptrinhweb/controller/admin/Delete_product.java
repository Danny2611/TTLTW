package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(urlPatterns = "/admin/delete-product")
public class Delete_product extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));

        if (ProductDAO.deleteProductById(productId)) {
            // Xóa thành công, có thể chuyển hướng hoặc xử lý theo ý của bạn
            response.sendRedirect(request.getContextPath() + "/admin/product"); // Ví dụ: chuyển hướng đến danh sách sản phẩm
        } else {
            // Xóa không thành công, có thể thông báo lỗi hoặc xử lý theo ý của bạn
            response.getWriter().println("Xóa sản phẩm không thành công.");
        }
    }
}
