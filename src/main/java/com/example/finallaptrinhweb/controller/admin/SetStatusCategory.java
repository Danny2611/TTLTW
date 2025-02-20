package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.CategoryDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import java.io.IOException;

@WebServlet("/admin/set-status")
public class SetStatusCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status = Boolean.parseBoolean(request.getParameter("active")) ? 1: 0;
        int id = Integer.parseInt(request.getParameter("id"));

        CategoryDao dao = new CategoryDao();

        dao.setStatus(status, id);

        response.sendRedirect(request.getContextPath()+"/admin/category");
    }
}
