package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.RoleDAO;
import com.example.finallaptrinhweb.dao.UserDAOT;
import com.example.finallaptrinhweb.model.Role;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/admin/list-admin")
public class ListAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "admin-list");
        List<User> admins = UserDAOT.loadUserFromSql("select * from users where role_id>1");

        RoleDAO roleDAO = new RoleDAO();
        List<Role> roles = roleDAO.getAllRole();
        request.setAttribute("admins", admins);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("./list-admin.jsp").forward(request, response);
    }

}
