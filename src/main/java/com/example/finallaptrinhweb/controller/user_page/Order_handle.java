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
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin đơn hàng từ giỏ hàng
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("auth");

        // Lấy thông tin đơn hàng từ request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String district = request.getParameter("district");
        String city = request.getParameter("city");
        String phoneNumber = request.getParameter("phoneNumber");
        int cash = request.getParameter("cash") == null ? 0 : 1;
        int momo = request.getParameter("momo") == null ? 0 : 1;

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Lưu đơn hàng vào cơ sở dữ liệu
        int insertSta = 0;
        if (user != null) {
            // Lưu vào order
            insertSta = OrderDAO.addOrder(firstName + " " + lastName, user.getId(),
                    cart.getCoupon_code_id() == 0 ? 1 : cart.getCoupon_code_id(), 1, cart.getTotalQuantity(),
                    "Chờ xử lý", cart.getTotalPrice(), Integer.parseInt(phoneNumber),
                    addressLine1 + ", " + addressLine2 + ", " + district + ", " + city, cash == 0 ? momo : cash,
                    Timestamp.valueOf(currentDateTime.format(formatter)),
                    cart.getPriceSaled(), 0);

            Map<Integer, CartItem> products = cart.getProducts();

            // Lưu vào order_products
            if (insertSta == 1) {
                for (Map.Entry<Integer, CartItem> entry : products.entrySet()) {
                    int productId = entry.getKey();
                    CartItem cartItem = entry.getValue();

                    insertSta = OrderDAO.addOrderProduct(cart.getCoupon_code_id() == 0 ? 1 : cart.getCoupon_code_id(),
                            cartItem.getProduct().getProductName(), cartItem.getProduct().getImageUrl(),
                            cartItem.getQuantity(), cartItem.getProduct().getPrice());
                }
            }
        }

        // Xóa đơn hàng từ giỏ hàng (đây là một ví dụ, bạn có thể thay đổi tùy thuộc vào
        // cách bạn quản lý giỏ hàng)
        if (insertSta == 1)
            cart.clear();
        session.setAttribute("cart", cart);

        System.out.println(cart);

        // Redirect hoặc hiển thị trang thông báo thành công
        response.sendRedirect("order_success.jsp");
    }

}
