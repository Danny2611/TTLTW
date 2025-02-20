package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.model.Cart;
import com.example.finallaptrinhweb.model.OrderProduct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/checkout")
public class CheckoutService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null && !cart.isEmpty()) {
            // Add logic to set other attributes as needed
            double price = cart.getPriceSaled() != 0 ? cart.getPriceSaled() : cart.getTotalPrice();
            int typeWeight = cart.getMaxTypeWeight();
            double priceShipment = calculateShipmentPrice();
            double totalPrice = price + priceShipment;

            // Set attributes for the JSP
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("price", price);
            request.setAttribute("priceShipment", priceShipment);
            request.setAttribute("typeWeight", typeWeight);

            // Forward to the checkout JSP
            request.getRequestDispatcher("/user/check_out.jsp").forward(request, response);
        } else {
            // Handle the case when the cart is empty
            // You might want to redirect or display a message
            response.sendRedirect("/user/cart");
        }
    }

    private double calculateShipmentPrice() {
        return 20000;
    }
}
