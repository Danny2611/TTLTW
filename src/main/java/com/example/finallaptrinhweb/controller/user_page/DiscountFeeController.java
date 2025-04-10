package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.CouponCodeDAO;
import com.example.finallaptrinhweb.model.CouponCode;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/discount")
public class DiscountFeeController extends HttpServlet {
    private final Logger logger = Logger.getLogger(DiscountFeeController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String discount = req.getParameter("discount");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (discount == null || discount.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Missing discount code\"}");
            return;
        }

        CouponCode couponCode = CouponCodeDAO.getInstance().getCouponByName(discount);

        if (couponCode == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("{\"error\": \"Discount code not found\"}");
            return;
        }

        if (couponCode.isUse()) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.getWriter().write("{\"error\": \"Discount code has already been used\"}");
            return;
        }

        // ✅ Nếu hợp lệ, trả về couponCode dưới dạng JSON
        try {
            // Set isUse = true
            boolean update = CouponCodeDAO.getInstance().setUseCouponIsUse(couponCode.getId());

            if (update) {
                // Gửi thông tin couponCode về FE
                // Cách 1: Dùng Gson (nên dùng)
                Gson gson = new Gson();
                String json = gson.toJson(couponCode);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(json);

            } else {
                logger.error("Failed to update discount code");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Failed to update discount code\"}");
            }

        } catch (SQLException e) {
            logger.error("DISCOUNT CHANGE ERR", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Server error while updating discount code\"}");
        }
    }

}
