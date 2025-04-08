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
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/cart/increment")
public class CartIncrementController extends HttpServlet {

    private final CartDAO cartDAO = new CartDAO();
    private final Gson gson = new Gson();
    private  static  final Logger logger = Logger.getLogger(CartIncrementController.class);

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

            if (cartRequest == null || user==null || cartRequest.getProductId() <= 0) {
                sendResponse(resp, false, "Dữ liệu không hợp lệ");
                return;
            }

            // 2. Tăng số lượng sản phẩm
            boolean success = cartDAO.incrementProduct(user.getId(), cartRequest.getProductId());

            if (success) {
                sendResponse(resp, true, "Số lượng sản phẩm đã được tăng");
            } else {
                sendResponse(resp, false, "Không thể tăng số lượng (vượt quá tồn kho)");
            }

        } catch (Exception e) {
            logger.error("ERR server in Increment Cart");
            sendResponse(resp, false, "Lỗi hệ thống: " + e.getMessage());
        }
    }

    // Phản hồi JSON
    private void sendResponse(HttpServletResponse resp, boolean success, String message) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        json.addProperty("message", message);
        resp.getWriter().write(gson.toJson(json));
    }

    // Lớp ánh xạ dữ liệu request
}
