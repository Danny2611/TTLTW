package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.CategoryDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/admin/edit-category")
public class EditCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String categoryName = String.valueOf(request.getParameter("name"));
        String action = request.getParameter("action");

        if (action != null) {
            CategoryDao dao = new CategoryDao();
            dao.editCategory(id, categoryName);
        }

        request.setAttribute("id", id);
        request.setAttribute("categoryName", categoryName);

        request.getRequestDispatcher("edit-category.jsp").forward(request, response);
    }
}
