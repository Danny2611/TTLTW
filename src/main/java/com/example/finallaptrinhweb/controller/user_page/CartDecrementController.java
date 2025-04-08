package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.CartDAO;
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
import java.util.List;

@WebServlet("/api/cart/decrement")
public class CartDecrementController extends HttpServlet {

    private final CartDAO cartDAO = new CartDAO();
    private final Gson gson = new Gson();
    private static  final Logger logger = Logger.getLogger(CartDecrementController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            // Lấy userId từ query parameter
            String userIdParam = req.getParameter("userId");
            if (userIdParam == null || userIdParam.isEmpty()) {
                logger.error("Miss user id in decrement cart");
                sendErrorResponse(resp, "Thiếu userId");
                return;
            }

            int userId = Integer.parseInt(userIdParam);

            // Lấy danh sách sản phẩm từ giỏ hàng
            List<CartItem> cartItems = cartDAO.getCartByUserId(userId);

            // Trả về kết quả JSON
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("success", true);
            jsonResponse.add("cartItems", gson.toJsonTree(cartItems));

            logger.info("Decrement Cart Successfully");

            resp.getWriter().write(gson.toJson(jsonResponse));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ERR server in Decrement Cart");
            sendErrorResponse(resp, "Lỗi hệ thống: " + e.getMessage());
        }
    }

    private void sendErrorResponse(HttpServletResponse resp, String message) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("success", false);
        json.addProperty("message", message);
        resp.getWriter().write(json.toString());
    }

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

            if (cartRequest == null || user== null || cartRequest.getProductId() <= 0) {
                sendResponse(resp, false, "Dữ liệu không hợp lệ");
                return;
            }

            // 2. Giảm số lượng sản phẩm
            boolean success = cartDAO.decrementProduct(user.getId(), cartRequest.getProductId());

            if (success) {
                sendResponse(resp, true, "Số lượng sản phẩm đã được giảm");
            } else {
                sendResponse(resp, false, "Không thể giảm số lượng (đã đạt 0)");
            }

        } catch (Exception e) {
            sendResponse(resp, false, "Lỗi hệ thống: " + e.getMessage());
        }
    }

    private void sendResponse(HttpServletResponse resp, boolean success, String message) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        json.addProperty("message", message);
        resp.getWriter().write(gson.toJson(json));
    }


}
