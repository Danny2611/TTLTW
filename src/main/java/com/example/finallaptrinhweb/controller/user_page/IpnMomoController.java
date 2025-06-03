package com.example.finallaptrinhweb.controller.user_page;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/user/momo-ipn")
public class IpnMomoController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(IpnMomoController.class);
    private static final String SECRET_KEY = "K951B6PE1waDMi640xX08PD3vg6EkVlz";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String requestBody = sb.toString();
        logger.info("=== MOMO IPN RECEIVED ===");
        logger.info("Request body: " + requestBody);

        try {
            JSONObject jsonRequest = new JSONObject(requestBody);

            String partnerCode = jsonRequest.optString("partnerCode", "");
            String orderId = jsonRequest.optString("orderId", "");
            String requestId = jsonRequest.optString("requestId", "");
            String amount = jsonRequest.optString("amount", "");
            String orderInfo = jsonRequest.optString("orderInfo", "");
            String orderType = jsonRequest.optString("orderType", "");
            String transId = jsonRequest.optString("transId", "");
            String resultCode = jsonRequest.optString("resultCode", "");
            String message = jsonRequest.optString("message", "");
            String payType = jsonRequest.optString("payType", "");
            String responseTime = jsonRequest.optString("responseTime", "");
            String extraData = jsonRequest.optString("extraData", "");
            String signature = jsonRequest.optString("signature", "");

            logger.info("OrderID: " + orderId + ", ResultCode: " + resultCode + ", Amount: " + amount);

            // Verify signature
            String rawHash = "accessKey=F8BBA842ECF85" +
                    "&amount=" + amount +
                    "&extraData=" + extraData +
                    "&message=" + message +
                    "&orderId=" + orderId +
                    "&orderInfo=" + orderInfo +
                    "&orderType=" + orderType +
                    "&partnerCode=" + partnerCode +
                    "&payType=" + payType +
                    "&requestId=" + requestId +
                    "&responseTime=" + responseTime +
                    "&resultCode=" + resultCode +
                    "&transId=" + transId;

            String computedSignature = hmacSHA256(rawHash, SECRET_KEY);
            boolean isValidSignature = signature.equals(computedSignature);

            logger.info("Signature valid: " + isValidSignature);

            if (!isValidSignature) {
                logger.warn("Invalid signature from MoMo IPN: " + orderId);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            // Xử lý kết quả thanh toán
            if ("0".equals(resultCode)) {
                // Thanh toán thành công
                logger.info("Payment SUCCESS via IPN - OrderID: " + orderId + ", TransID: " + transId);

            } else {
                // Thanh toán thất bại
                logger.warn("Payment FAILED via IPN - OrderID: " + orderId + ", Message: " + message);
            }

            // Phản hồi thành công cho MoMo
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");

        } catch (Exception e) {
            logger.error("Error processing MoMo IPN", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String hmacSHA256(String data, String key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error creating HMAC-SHA256 signature", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}