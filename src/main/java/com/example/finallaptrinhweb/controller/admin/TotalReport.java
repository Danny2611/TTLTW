package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.OrderDAO;
import com.example.finallaptrinhweb.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.example.finallaptrinhweb.dao.OrderDAO.*;


@WebServlet(urlPatterns = "/admin/total-report")
public class TotalReport extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type_view = request.getParameter("type_view");
        List<Order> orderList;

        if(type_view == null) {
            orderList = loadAllOrder();
        } else {
            orderList = loadOrderByStatus(type_view);
        }

        List<String> listStatus = getAllStatus();

        System.out.println(orderList);

        request.setAttribute("type_view", type_view);
        request.setAttribute("listStatus", listStatus);
        request.setAttribute("total_report", orderList);
        request.setAttribute("title", "Danh sách đặt hàng");
        request.getRequestDispatcher("total-report.jsp").forward(request, response);
    }
}