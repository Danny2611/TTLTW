package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.dao.SupplierDAO;
import com.example.finallaptrinhweb.model.Product;
import com.example.finallaptrinhweb.model.Supplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/admin/product")
public class Product_direct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "product");

        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProducts();

        Map<Integer, Supplier> supplierMap = new HashMap<>();
        SupplierDAO supplierDAO = new SupplierDAO();
        for (Product product : products) {
            int supplierId = product.getSupplierId();
            if (!supplierMap.containsKey(supplierId)) {
                Supplier supplier = supplierDAO.getSupplierById(supplierId);
                supplierMap.put(supplierId, supplier);
            }
        }

        request.setAttribute("product", products);
        request.setAttribute("supplierMap", supplierMap);
        request.getRequestDispatcher("./product.jsp").forward(request, response);

    }
}
