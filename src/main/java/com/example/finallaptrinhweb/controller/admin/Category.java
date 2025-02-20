package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.CategoryDao;
import com.example.finallaptrinhweb.model.CategoryQuantity;
import com.example.finallaptrinhweb.model.ProductCategories;
import com.example.finallaptrinhweb.model.ProductGroups;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/category")
public class Category extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "category");
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("admin");

//        if (admin == null) {
//            response.sendRedirect("./admin/login.jsp");
//        }

        CategoryDao dao = new CategoryDao();

        List<ProductGroups> groups = dao.getGroupCategory();
        List<ProductCategories> categories = dao.getCategory();
        List<CategoryQuantity> quantities = dao.getQuantityCategory();


        request.setAttribute("groups", groups);
        request.setAttribute("categories", categories);
        request.setAttribute("quantities", quantities);



        request.getRequestDispatcher("./categories.jsp").forward(request, response);
    }
}
