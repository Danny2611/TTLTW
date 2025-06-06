package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.CouponCodeDAO;
import com.example.finallaptrinhweb.dao.OrderDAO;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet(value = "/user/return")
public class ReturnMomoController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ReturnMomoController.class);
    private static final String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";

    OrderDAO orderDAO = new OrderDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy tất cả parameters từ MoMo
            String partnerCode = request.getParameter("partnerCode");
            String orderId = request.getParameter("orderId");
            String requestId = request.getParameter("requestId");
            String amount = request.getParameter("amount");
            String orderInfo = request.getParameter("orderInfo");
            String orderType = request.getParameter("orderType");
            String transId = request.getParameter("transId");
            String resultCode = request.getParameter("resultCode");
            String message = request.getParameter("message");
            String payType = request.getParameter("payType");
            String responseTime = request.getParameter("responseTime");
            String extraData = request.getParameter("extraData");
            String signature = request.getParameter("signature");

            // Debug log
            System.out.println("=== MOMO RETURN ===");
            System.out.println("resultCode: " + resultCode);
            System.out.println("message: " + message);
            System.out.println("orderId: " + orderId);
            System.out.println("amount: " + amount);
            System.out.println("transId: " + transId);

            // Verify signature để đảm bảo request từ MoMo
            if (signature != null && !signature.isEmpty()) {
                String rawHash = "accessKey=F8BBA842ECF85" +
                        "&amount=" + (amount != null ? amount : "") +
                        "&extraData=" + (extraData != null ? extraData : "") +
                        "&message=" + (message != null ? message : "") +
                        "&orderId=" + (orderId != null ? orderId : "") +
                        "&orderInfo=" + (orderInfo != null ? orderInfo : "") +
                        "&orderType=" + (orderType != null ? orderType : "") +
                        "&partnerCode=" + (partnerCode != null ? partnerCode : "") +
                        "&payType=" + (payType != null ? payType : "") +
                        "&requestId=" + (requestId != null ? requestId : "") +
                        "&responseTime=" + (responseTime != null ? responseTime : "") +
                        "&resultCode=" + (resultCode != null ? resultCode : "") +
                        "&transId=" + (transId != null ? transId : "");

                String computedSignature = hmacSHA256(rawHash, secretKey);
                boolean isValidSignature = signature.equals(computedSignature);
                System.out.println("Signature valid: " + isValidSignature);

                if (!isValidSignature) {
                    logger.warn("Invalid signature from MoMo callback");
                }
            }

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("auth");

            if ("0".equals(resultCode)) {
                // Thanh toán thành công
                System.out.println("Payment SUCCESS");
                logger.info("User " + (user != null ? user.getEmail() : "unknown") + " payment SUCCESS - TransID: " + transId);
                String address = (String) session.getAttribute("address");
                String phone = (String) session.getAttribute("phone");
                String  receiver= (String) session.getAttribute("receiver");
                String discountStr = (String) session.getAttribute("discount");
                Integer discount = 0;
                if (discountStr != null && !discountStr.trim().isEmpty()) {
                    discount = Integer.parseInt(discountStr.trim());
                }
                Integer quantity = Integer.parseInt(session.getAttribute("quantity").toString());
                Integer totalPay = Integer.parseInt(session.getAttribute("totalPay").toString());
                Integer ship = Integer.parseInt(session.getAttribute("ship").toString());

                System.out.println("discount:" + discount);
                try {
                    if(discount !=null){
                        boolean update = CouponCodeDAO.getInstance().setUseCouponIsUse(discount);
                    }
                } catch (SQLException e) {
                    logger.error("Change isUse cupOn fail", e);
                    throw new RuntimeException(e);
                }
                OrderDAO.addOrder(receiver, user.getId(),discount == 0 ? null: discount,2,
                        quantity, "Chờ xử lí", totalPay -ship,
                        Integer.parseInt(phone), address, 2, new Timestamp(System.currentTimeMillis()), totalPay, ship);
                // Set attributes cho JSP
                request.setAttribute("status", "success");
                request.setAttribute("message", "Thanh toán thành công!");
                request.setAttribute("orderId", orderId);
                request.setAttribute("amount", amount);
                request.setAttribute("transId", transId);
//                OrderDAO.addOrder()

                // Redirect đến trang success
                response.sendRedirect(request.getContextPath() + "/user/order_success.jsp");
                return;

            } else {
                // Thanh toán thất bại
                System.out.println("Payment FAILED: " + message);
                logger.warn("User " + (user != null ? user.getEmail() : "unknown") + " payment FAILED - " + message);

                // Set attributes cho JSP
                request.setAttribute("status", "failed");
                request.setAttribute("message", "Thanh toán thất bại: " + (message != null ? message : "Lỗi không xác định"));
                request.setAttribute("resultCode", resultCode);
                request.setAttribute("orderId", orderId);
            }

            // Forward đến trang hiển thị kết quả thất bại
            request.getRequestDispatcher("/user/paymentResult.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error processing MoMo return: " + e.getMessage());
            e.printStackTrace();
            logger.error("MoMo return processing error", e);

            request.setAttribute("status", "error");
            request.setAttribute("message", "Có lỗi xảy ra trong quá trình xử lý thanh toán");
            request.getRequestDispatcher("/user/paymentResult.jsp").forward(request, response);
        }
    }

    private String hmacSHA256(String data, String key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo chữ ký", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}