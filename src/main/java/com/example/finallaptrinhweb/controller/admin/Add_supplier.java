package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.SupplierDAO;
import com.example.finallaptrinhweb.model.Supplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(urlPatterns = "/admin/add-supplier")
public class Add_supplier extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "supplier");
        String type = request.getParameter("type");
        if (type != null) {
            if (type.equalsIgnoreCase("enterAdd")) {
                request.setAttribute("type", "add");
                request.setAttribute("title", "Thêm nhà cung cấp");
                request.getRequestDispatcher("./add-supplier.jsp").forward(request, response);
                return;
            } else if (type.equalsIgnoreCase("enterEdit")) {
                request.setAttribute("type", "edit");
                request.setAttribute("title", "Chỉnh sửa nhà cung cấp");
                int id = Integer.parseInt(request.getParameter("id"));
                Supplier supplier = SupplierDAO.loadSupplier(id);
                request.setAttribute("supplier", supplier);
                request.getRequestDispatcher("./add-supplier.jsp").forward(request, response);
                return;
            }
        }
        if (type.equalsIgnoreCase("add")) {
            request.setAttribute("type", "add");
            request.setAttribute("title", "Thêm nhà cung cấp");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            int phone = Integer.parseInt(request.getParameter("phone"));
            String email = request.getParameter("email");
            boolean isInsert = SupplierDAO.insertSupplier(name, address, phone, email);
            request.getRequestDispatcher("./add-supplier.jsp").forward(request, response);
        } else if (type.equalsIgnoreCase("edit")) {
            request.setAttribute("type", "edit");
            request.setAttribute("title", "Chỉnh sửa nhà cung cấp");
            System.out.println("co vao edit");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            int phone = Integer.parseInt(request.getParameter("phone"));
            String email = request.getParameter("email");
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            boolean isUpdate = SupplierDAO.updateSupplier(id, name, address, phone, email);
            Supplier su = SupplierDAO.loadSupplier(id);
            request.setAttribute("supplier", su);
            request.getRequestDispatcher("./add-supplier.jsp").forward(request, response);

        }

    }
}