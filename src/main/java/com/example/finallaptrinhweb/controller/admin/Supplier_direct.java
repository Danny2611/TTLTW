package com.example.finallaptrinhweb.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.example.finallaptrinhweb.dao.SupplierDAO;
import com.example.finallaptrinhweb.model.Supplier;

@WebServlet(urlPatterns = "/admin/supplier")
public class Supplier_direct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "supplier");
        request.setAttribute("title", "Nhà cung cấp");
        List<Supplier> supplierList = SupplierDAO.loadSupplierList();
        request.setAttribute("sup_view", supplierList);
        System.out.println(request.getAttribute("sup_view"));
        request.getRequestDispatcher("./supplier.jsp").forward(request, response);
    }
}