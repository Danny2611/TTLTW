package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.CouponCodeDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;

@WebServlet("/admin/add-coupon")
public class AddCoupon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String type = request.getParameter("type") == null ? "" : request.getParameter("type");
        String name = request.getParameter("name");
        double percent = Double.parseDouble(request.getParameter("percent") == null ? String.valueOf(0) : request.getParameter("percent"));
        String date_end = request.getParameter("date_end");
        int id = Integer.parseInt(request.getParameter("id") == null ? String.valueOf(0) : request.getParameter("id"));


        CouponCodeDAO dao = new CouponCodeDAO();
        if (type.equals("add")) {
            request.setAttribute("title", "Thêm mã giảm giá");
            request.setAttribute("type", "add");
        } else {
            request.setAttribute("discount", dao.getCouponById(id));
            request.setAttribute("title", "Sửa mã giảm giá");
            request.setAttribute("type", "edit");
        }

        if(action.equals("add")) {
            dao.addCoupon(name, percent, date_end);
            response.sendRedirect("coupon-code");
            return;
        }else if(action.equals("delete")) {
            dao.delCoupon(id);
            response.sendRedirect("coupon-code");
            return;
        } else if (action.equals("edit")) {
            dao.editCoupon(id, name, percent, date_end);
            response.sendRedirect("coupon-code");
            return;
        }

        request.getRequestDispatcher("add-coupon-code.jsp").forward(request,response);
    }
}
