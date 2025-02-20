package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.UserDAOT;
import com.example.finallaptrinhweb.model.User;
import com.example.finallaptrinhweb.model.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/edit_user")
public class Edit_user extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "user");
        request.setAttribute("title", "Sửa thông tin người dùng");
        String type = request.getParameter("type");
        if (type.equalsIgnoreCase("enteredit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = UserDAOT.loadUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("./edit-users.jsp").forward(request, response);
            return;
        }
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String birthday = request.getParameter("birthday");
        String createddate = request.getParameter("createddate");
        if (type.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(request.getParameter("usercode"));
            request.setAttribute("type", "edit");
            request.setAttribute("title", "Sửa thông tin người dùng");
            request.setAttribute("id", id);
            boolean isUpdate = UserDAOT.updateUserInAdimin(id, email, name, birthday, address, createddate);
            User user = UserDAOT.loadUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("./edit-users.jsp").forward(request, response);
        }
    }
}
