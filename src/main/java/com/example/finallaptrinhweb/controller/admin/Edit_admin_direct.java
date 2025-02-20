package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.UserDAOT;
import com.example.finallaptrinhweb.model.User;
import com.example.finallaptrinhweb.model.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/edit-admin")
public class Edit_admin_direct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "admin");
        request.setAttribute("title", "Sửa thông tin admin");
        String type = request.getParameter("type");
        if (type.equalsIgnoreCase("enterEdit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = UserDAOT.loadUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("edit-admin.jsp").forward(request, response);
            return;
        }
        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        if (type.equalsIgnoreCase("edit")) {
            request.setAttribute("type", "edit");
            request.setAttribute("title", "Chỉnh sửa admin");
            int id = Integer.parseInt(request.getParameter("usercode"));
            boolean isUpdate = UserDAOT.updateUserById(id, name, phone, email, address);
            User user = UserDAOT.loadUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("edit-admin.jsp").forward(request, response);
        }
    }
}

