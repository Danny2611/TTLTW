package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.model.Product;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/edit-product")
public class Edit_product extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "product");
        request.setAttribute("title", "Chỉnh sửa sản phẩm");
        String type = request.getParameter("type");
        if ("enterEdit".equalsIgnoreCase(type)) {
            request.setAttribute("type", "edit");
            request.setAttribute("title", "Chỉnh sửa sản phẩm");
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = ProductDAO.loadProductById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("edit-products.jsp").forward(request, response);
            return;
        }
        String productName = request.getParameter("productName");
        String purpose = request.getParameter("purpose");
        String contraindications = request.getParameter("contraindications");
        String ingredients = request.getParameter("ingredients");
        String dosage = request.getParameter("dosage");
        String instructions = request.getParameter("instructions");
        String warrantyPeriod = request.getParameter("warrantyPeriod");
        String storageCondition = request.getParameter("storageCondition");
        String productType = request.getParameter("productType");
        if ("edit".equalsIgnoreCase(type)) {
            int id = Integer.parseInt(request.getParameter("productId"));
            double price = Double.parseDouble(request.getParameter("price"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            request.setAttribute("type", "edit");
            request.setAttribute("title", "Chỉnh sửa");
            boolean isUpdate = ProductDAO.updateProduct(id, productName, categoryId, price, quantity,
                    purpose, contraindications, stockQuantity, ingredients, dosage,
                    instructions, warrantyPeriod, storageCondition, productType,
                    supplierId);
            Product product = ProductDAO.loadProductById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("edit-products.jsp").forward(request, response);
        }
    }
}

