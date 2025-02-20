package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.model.Address;
import com.example.finallaptrinhweb.dao.LoadfooterDao;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/setting-address")
public class AddressSetting extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "setting");

        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String hotline = request.getParameter("hotline");
        String email = request.getParameter("email");
        String timeOpen = request.getParameter("timeOpen");
        String mapAddress = request.getParameter("map");

        boolean isUpdated = LoadfooterDao.updateAddress(address, phone, hotline, email, timeOpen, mapAddress);
        List<Address> addresses = LoadfooterDao.loadAddresses();
        ServletContext context = getServletContext();
        context.setAttribute("address", addresses);

        // Chuyển hướng đến trang setting-address.jsp
        request.getRequestDispatcher("./setting-address.jsp").forward(request, response);
    }
}
