package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.model.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/user/cart")
public class ShoppingCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");


//        get totalPrice after aplly coupon by coupon id
//        int coupon = cart.getCoupon_code_id();
//        double totalPrice = cart.getPriceSaled(coupon);

//        request.setAttribute("totalPrice", totalPrice);
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("./cart.jsp").forward(request, response);
    }

}
