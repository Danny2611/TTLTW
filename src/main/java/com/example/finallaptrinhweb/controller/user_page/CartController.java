package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.CartDAO;
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

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(value = "/api/cart")
public class CartController extends HttpServlet {

    private final CartDAO cartDAO = new CartDAO();
    private final Gson gson = new Gson();

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
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(jsonResponse(true, "Sản phẩm đã được thêm vào giỏ hàng"));
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write(jsonResponse(false, "Không thể thêm sản phẩm vào giỏ hàng"));
            }

        } catch (Exception e) {
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

}
