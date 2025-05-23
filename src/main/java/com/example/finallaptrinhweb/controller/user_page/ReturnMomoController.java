package com.example.finallaptrinhweb.controller.user_page;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet(value = "/user/return")
public class ReturnMomoController extends HttpServlet {
    private static  final Logger logger  = Logger.getLogger(ReturnMomoController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultCode = request.getParameter("resultCode");
        if ("0".equals(resultCode)) {
            request.setAttribute("message", "Thanh toán thành công!");
            response.sendRedirect(request.getContextPath() +"/user/order_success.jsp");
            return;
        } else {
            request.setAttribute("message", "Thanh toán thất bại!");
        }
        request.getRequestDispatcher("paymentResult.jsp").forward(request, response);
    }
}
