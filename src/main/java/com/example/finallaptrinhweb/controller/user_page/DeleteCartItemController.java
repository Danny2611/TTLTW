package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.CartDAO;
import com.example.finallaptrinhweb.model.User;
import com.example.finallaptrinhweb.request.CartRequest;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/api/cart/delete")
public class DeleteCartItemController extends HttpServlet {


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        CartDAO cartDAO = new CartDAO();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("auth");

        try {
            // Đọc dữ liệu từ body request
            BufferedReader reader = req.getReader();
            CartRequest deleteRequest = gson.fromJson(reader, CartRequest.class);
            int productId = deleteRequest.getProductId();

            boolean isDeleted = cartDAO.deleteCartItem(user.getId(), productId);

            if (isDeleted) {
                resp.setStatus(HttpServletResponse.SC_OK);
                out.write(gson.toJson("Item deleted successfully"));
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write(gson.toJson("Failed to delete item"));
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write(gson.toJson("Error deleting item from cart"));
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }


}
