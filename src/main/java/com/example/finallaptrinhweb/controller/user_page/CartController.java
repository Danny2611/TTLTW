package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.CartDAO;
import com.example.finallaptrinhweb.model.Cart;
import com.example.finallaptrinhweb.model.CartItem;
import com.example.finallaptrinhweb.model.User;
import com.example.finallaptrinhweb.request.CartRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/api/cart")
public class CartController extends HttpServlet {

    private final CartDAO cartDAO = new CartDAO();
    private final Gson gson = new Gson();
    private static  final Logger logger = Logger.getLogger(CartController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("auth");
        try {
            // 1. Đọc dữ liệu từ request body
            BufferedReader reader = req.getReader();
            CartRequest cartRequest = gson.fromJson(reader, CartRequest.class);

            // Kiểm tra dữ liệu đầu vào
            if (cartRequest == null || user == null || cartRequest.getProductId() <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write(jsonResponse(false, "Dữ liệu không hợp lệ"));
                return;
            }

            // 2. Thêm sản phẩm vào giỏ hàng
            boolean success = cartDAO.addToCart(user.getId(), cartRequest.getProductId());

            // 3. Trả về phản hồi JSON
            if (success) {
                logger.info("Add product successfully");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(jsonResponse(true, "Sản phẩm đã được thêm vào giỏ hàng"));
            } else {
                logger.error("Add product failure");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write(jsonResponse(false, "Không thể thêm sản phẩm vào giỏ hàng"));
            }

        } catch (Exception e) {
            logger.error("ERR in cart");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write(jsonResponse(false, "Lỗi hệ thống: " + e.getMessage()));
        }
    }

    // Hàm tạo JSON response
    private String jsonResponse(boolean success, String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", success);
        jsonObject.addProperty("message", message);
        return gson.toJson(jsonObject);
    }

    // Class ánh xạ dữ liệu từ request body


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("auth");
        if (user==null) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Missing userId\"}");
            return;
        }

        // Lấy giỏ hàng từ CartDAO
        CartDAO cartDAO = new CartDAO();
        List<CartItem> cart = cartDAO.getCartByUserId(user.getId());

        if (cart == null) {
            resp.setContentType("application/json");
            resp.getWriter().write("{\"cartItems\": [], \"totalAmount\": 0}");
            return;
        }
        Map<String, Object> cartJson = new HashMap<>();
        cartJson.put("cartItems", cart);
        cartJson.put("totalAmount", cart.stream()
                .mapToDouble(item-> item.getProduct().getPrice() * item.getQuantity()).sum()
        );

        Gson gson = new Gson();
        String cartJsonString = gson.toJson(cartJson);
        logger.info("Get cart successfully");
        // Trả về JSON response
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(cartJsonString);
    }
}
