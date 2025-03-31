
package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.SupplierDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/delete-supplier")
public class Delete_supplier extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int supplierId = Integer.parseInt(request.getParameter("id"));
        boolean isDeleted = SupplierDAO.deleteSupplier(supplierId);

        PrintWriter out = response.getWriter();
        if (isDeleted) {
            out.print("{\"status\": \"success\"}");
        } else {
            out.print("{\"status\": \"error\"}");
        }
        out.flush();
    }
}
