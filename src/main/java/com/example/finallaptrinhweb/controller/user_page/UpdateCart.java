package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.model.Cart;
import com.example.finallaptrinhweb.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/updatecart")
public class UpdateCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseData = new HashMap<>();

        String action = request.getParameter("action");
        int productId = 0, amount, currentQuantity = 0;

        try {
            productId = Integer.parseInt(request.getParameter("id"));
            amount = Integer.parseInt(request.getParameter("amount"));
            currentQuantity = Integer.parseInt(request.getParameter("currentQuantity"));
        } catch (NumberFormatException e) {
            responseData.put("status", "error");
            responseData.put("message", "ID sản phẩm hoặc số lượng không hợp lệ");
            sendResponse(response, responseData, action, productId, 0);
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || !cart.getProducts().containsKey(productId)) {
            responseData.put("status", "error");
            responseData.put("message", "Sản phẩm không có trong giỏ hàng");
            sendResponse(response, responseData, action, productId, 0);
            return;
        }

        ProductDAO productDAO = new ProductDAO();
        Product product = cart.getProducts().get(productId).getProduct();
        int stockQuantity = productDAO.getProductById(productId).getQuantity(); // Lấy số lượng tồn kho

        switch (action) {
            case "increment":
                if (currentQuantity + 1 > stockQuantity) {
                    responseData.put("status", "error");
                    responseData.put("message", "Không đủ hàng trong kho");
                } else {
                    product.setQuantity(currentQuantity + 1);
                    responseData.put("status", "success");
                    responseData.put("message", "Đã tăng số lượng sản phẩm");
                }
                break;
            case "decrement":
                if (currentQuantity > 1) {
                    product.setQuantity(currentQuantity - 1);
                    responseData.put("status", "success");
                    responseData.put("message", "Đã giảm số lượng sản phẩm");
                } else {
                    cart.getProducts().remove(productId);
                    responseData.put("status", "success");
                    responseData.put("message", "Đã xóa sản phẩm khỏi giỏ hàng");
                }
                break;
            case "update":
                if (amount > stockQuantity) {
                    responseData.put("status", "error");
                    responseData.put("message", "Không đủ hàng trong kho");
                } else if (amount > 0) {
                    product.setQuantity(amount);
                    responseData.put("status", "success");
                    responseData.put("message", "Cập nhật số lượng sản phẩm thành công");
                } else {
                    cart.getProducts().remove(productId);
                    responseData.put("status", "success");
                    responseData.put("message", "Đã xóa sản phẩm khỏi giỏ hàng");
                }
                break;
            case "delete":
                cart.getProducts().remove(productId);
                responseData.put("status", "success");
                responseData.put("message", "Đã xóa sản phẩm khỏi giỏ hàng");
                break;
            default:
                responseData.put("status", "error");
                responseData.put("message", "Hành động không hợp lệ");
                break;
        }

        // Cập nhật giỏ hàng trong session
        session.setAttribute("cart", cart);

        sendResponse(response, responseData, action, productId, cart.getProducts().containsKey(productId) ? cart.getProducts().get(productId).getQuantity() : 0);
    }

    private void sendResponse(HttpServletResponse response, Map<String, Object> responseData, String action, int productId, int newQuantity) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        responseData.put("action", action);
        responseData.put("productId", productId);
        responseData.put("newQuantity", newQuantity);
        response.getWriter().write(objectMapper.writeValueAsString(responseData));
    }
}
