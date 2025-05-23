package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.controller.admin.LogInByGoogle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@WebServlet("/user/checkout")
public class CheckoutService extends HttpServlet {
    private static  final Logger logger = Logger.getLogger(CheckoutService.class);
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String value = request.getParameter("value");
        System.out.println(city + district);
        // 🛠 Kiểm tra giá trị đầu vào
        if (city == null || district == null || value == null || city.isEmpty() || district.isEmpty() || value.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Missing parameters\"}");
            return;
        }

        // Mã hóa tham số URL
        String apiUrl = "https://services.giaohangtietkiem.vn/services/shipment/fee?"
                + "pick_province=" + URLEncoder.encode("Hồ Chí Minh", StandardCharsets.UTF_8.toString())
                + "&pick_district=" + URLEncoder.encode("Thủ Đức", StandardCharsets.UTF_8.toString())
                + "&province=" + URLEncoder.encode(city, StandardCharsets.UTF_8.toString())
                + "&district=" + URLEncoder.encode(district, StandardCharsets.UTF_8.toString())
                + "&weight=" + URLEncoder.encode("1", StandardCharsets.UTF_8.toString())
                + "&value=" + URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
                + "&deliver_option=" + URLEncoder.encode("none", StandardCharsets.UTF_8.toString());

        System.out.println("API URL: " + apiUrl);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json"); //  Fix: Thêm Header
            conn.setRequestProperty("Token", "1IWspuqjIDEeKZd4S32CPHt8ajxOtfUPO1YKShf");

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode); //  Debug lỗi

            if (responseCode == 200) {
                Scanner scanner = new Scanner(new InputStreamReader(conn.getInputStream()));
                StringBuilder jsonResponse = new StringBuilder();
                while (scanner.hasNext()) {
                    jsonResponse.append(scanner.nextLine());
                }
                scanner.close();
                logger.info("Checkout successfully");
                response.setContentType("application/json");
                response.getWriter().write(jsonResponse.toString());
            } else {
                response.setStatus(responseCode);
                response.getWriter().write("{\"error\": \"Failed to fetch data\"}");
            }
        } catch (Exception e) {
            logger.error("ERR server in checkout");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
