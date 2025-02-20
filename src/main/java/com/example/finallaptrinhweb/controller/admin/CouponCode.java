package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.CouponCodeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/coupon-code")
public class CouponCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "coupon-code");
        request.setAttribute("title","Mã giảm giá");

        CouponCodeDAO dao = new CouponCodeDAO();

        System.out.println(dao.getAllCoupon());
        request.setAttribute("coupon", dao.getAllCoupon());
        request.getRequestDispatcher("coupon-code.jsp").forward(request,response);

    }
}
