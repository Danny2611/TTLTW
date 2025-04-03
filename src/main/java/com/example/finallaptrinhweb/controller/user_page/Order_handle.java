package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.OrderDAO;
import com.example.finallaptrinhweb.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@WebServlet("/user/order-handle")
public class Order_handle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        // Lấy dữ liệu từ form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = firstName + " " + lastName;
        System.out.println("username" + username);
        int user_id = user.getId();
        Integer discounts_id = request.getParameter("discounts_id") != null ? Integer.parseInt(request.getParameter("discounts_id")) : null;
        int ship_id = 1;
        String quantityStr = request.getParameter("quantity");
        int quantity = (quantityStr != null && !quantityStr.isEmpty()) ? Integer.parseInt(quantityStr) : 0;
        System.out.println("quantity" + quantity);
        String status = "Chờ xử lý";
        int phone = Integer.parseInt(request.getParameter("phoneNumber"));
        String detail_address = request.getParameter("addressLine1") + ", " + request.getParameter("addressLine2") + request.getParameter("district")  +  request.getParameter("city") ;
        int payment = request.getParameter("cash").equals("COD") ? 1 : 2;
        Timestamp date_created = new Timestamp(System.currentTimeMillis());
        double total_pay = Double.parseDouble(request.getParameter("totalAmount"));
        double ship_price = Double.parseDouble(request.getParameter("fee"));
        System.out.println(firstName+ lastName);
        // Gọi phương thức thêm đơn hàng
        int result = OrderDAO.addOrder(username, user_id, discounts_id, ship_id, quantity, status, total_pay -ship_price, phone, detail_address, payment, date_created, total_pay, ship_price);

        if (result > 0) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("error");
        }
    }



}
