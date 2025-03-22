package com.example.finallaptrinhweb.controller.user_page;


import com.example.finallaptrinhweb.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/verify")
public class UserVerify extends HttpServlet {
    private static final int MAX_ATTEMPTS = 3;
    private static final long LOCK_DURATION = 5 * 60 * 1000; // 5 phút (ms)

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String authcode = (String) session.getAttribute("authcode");
        String code = request.getParameter("verifycode");

        // Lấy số lần nhập sai và thời gian khóa từ session
        Integer attempts = (Integer) session.getAttribute("attempts");
        Long lockTime = (Long) session.getAttribute("lockTime");

        if (attempts == null) attempts = 0;

        // Kiểm tra nếu bị khóa
        if (lockTime != null) {
            long elapsedTime = System.currentTimeMillis() - lockTime;
            if (elapsedTime < LOCK_DURATION) {
                request.setAttribute("wrongAuthCode", "Yêu cầu nhập lại sau 5 phút!");
                request.getRequestDispatcher("./verify.jsp").forward(request, response);
                return;
            } else {
                // Nếu đã qua 5 phút, reset lại số lần nhập sai
                session.removeAttribute("lockTime");
                session.setAttribute("attempts", 0);
                attempts = 0; // Reset biến attempts trong logic
            }
        }

        if (code.equals(authcode)) {
            session.removeAttribute("attempts"); // Reset số lần nhập sai
            session.removeAttribute("lockTime"); // Xóa thời gian khóa
            try {
                UserDAO.getInstance().SetVerifiedStatus(authcode);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("./signIn.jsp");
        } else {
            attempts++;
            session.setAttribute("attempts", attempts);

            if (attempts >= MAX_ATTEMPTS) {
                session.setAttribute("lockTime", System.currentTimeMillis()); // Lưu thời gian khóa
                request.setAttribute("wrongAuthCode", "Bạn đã nhập sai quá nhiều lần. Yêu cầu nhập lại sau 5 phút!");
            } else {
                request.setAttribute("wrongAuthCode", "Mã xác thực chưa đúng! Bạn còn " + (MAX_ATTEMPTS - attempts) + " lần thử.");
            }
            request.getRequestDispatcher("./verify.jsp").forward(request, response);
        }
    }
}

