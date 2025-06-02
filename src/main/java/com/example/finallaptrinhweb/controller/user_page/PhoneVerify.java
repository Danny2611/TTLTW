package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.config.FirebaseConfig;
import com.example.finallaptrinhweb.dao.UserDAO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/user/verifyPhone")
public class PhoneVerify extends HttpServlet {
    private static final long LOCK_DURATION = 5 * 60 * 1000;
    private static final int MAX_ATTEMPTS = 3;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String phoneNumber = (String) session.getAttribute("phoneNumber");
        String idToken = request.getParameter("idToken");

        // Kiểm tra số lần thử lỗi
        Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
        Long lockTime = (Long) session.getAttribute("lockTime");

        if (failedAttempts == null) failedAttempts = 0;

        // Kiểm tra xem người dùng có bị khóa không
        if (lockTime != null) {
            long elapsedTime = System.currentTimeMillis() - lockTime;
            if (elapsedTime < LOCK_DURATION) {
                sendErrorResponse(response, "Bạn đã nhập sai quá nhiều lần. Yêu cầu nhập lại sau 5 phút!");
                return;
            } else {
                session.removeAttribute("lockTime");
                session.setAttribute("failedAttempts", 0);
            }
        }

        try {
            // Xác thực token từ Firebase
            FirebaseAuth firebaseAuth = FirebaseConfig.getFirebaseAuth();
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            String uid = decodedToken.getUid();

            // Kiểm tra xem số điện thoại được xác minh có khớp với số điện thoại đã đăng ký
            String verifiedPhone = decodedToken.getClaims().get("phone_number").toString();

            if (verifiedPhone != null && (verifiedPhone.equals(phoneNumber) ||
                    verifiedPhone.startsWith("+84") && phoneNumber.startsWith("0") &&
                            verifiedPhone.substring(3).equals(phoneNumber.substring(1)))) {

                // Cập nhật trạng thái xác minh của người dùng
                UserDAO.getInstance().SetVerifiedStatusByPhone(phoneNumber);

                // Cập nhật Firebase UID
                UserDAO.getInstance().UpdateFirebaseUid(phoneNumber, uid);

                // Xóa dữ liệu session không cần thiết
                session.removeAttribute("failedAttempts");
                session.removeAttribute("lockTime");
                session.removeAttribute("phoneNumber");

                // Gửi phản hồi thành công
                sendSuccessResponse(response, "Xác thực tài khoản thành công!");
                return;
            } else {
                incrementFailedAttempts(session, failedAttempts, response);
                return;
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            incrementFailedAttempts(session, failedAttempts, response);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            sendErrorResponse(response, "Lỗi hệ thống: " + e.getMessage());
            return;
        }
    }

    private void incrementFailedAttempts(HttpSession session, int failedAttempts, HttpServletResponse response) throws IOException {
        failedAttempts++;
        session.setAttribute("failedAttempts", failedAttempts);

        if (failedAttempts >= MAX_ATTEMPTS) {
            session.setAttribute("lockTime", System.currentTimeMillis());
            sendErrorResponse(response, "Bạn đã nhập sai quá nhiều lần. Yêu cầu nhập lại sau 5 phút!");
        } else {
            sendErrorResponse(response, "Xác thực thất bại! Bạn còn " + (MAX_ATTEMPTS - failedAttempts) + " lần thử.");
        }
    }

    private void sendSuccessResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("success", true);
        jsonResponse.put("message", message);

        out.print(jsonResponse.toString());
        out.flush();
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("success", false);
        jsonResponse.put("message", message);

        out.print(jsonResponse.toString());
        out.flush();
    }
}