package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.CategoryDao;
import com.example.finallaptrinhweb.model.ProductGroups;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/add-category")
public class AddCategory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = request.getParameter("name");

        CategoryDao dao = new CategoryDao();

        if (action != null) {
            int id = Integer.parseInt(request.getParameter("id"));

            dao.addCategory(id, name);
            request.setAttribute("id", id);
            request.setAttribute("categoryName", name);
        }

        List<ProductGroups> categoryParent = dao.getGroupCategory();
        request.setAttribute("categoryParent", categoryParent);

        request.getRequestDispatcher("add-category.jsp").forward(request, response);
    }
}
