package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/admin/active")
public class Active  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));
        System.out.println(productId);
        if (ProductDAO.changeActiveProductById(productId)) {
            // Xóa thành công, có thể chuyển hướng hoặc xử lý theo ý của bạn
            resp.sendRedirect(req.getContextPath() + "/admin/product"); // Ví dụ: chuyển hướng đến danh sách sản phẩm
        } else {
            // Xóa không thành công, có thể thông báo lỗi hoặc xử lý theo ý của bạn
            resp.getWriter().println("Sửa trạng thái không thành công.");
        }
    }
}
