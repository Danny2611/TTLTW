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
    private static final long LOCK_DURATION = 5 * 60 * 1000;
    private static final int MAX_ATTEMPTS = 3;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String authcode = (String) session.getAttribute("authcode");
        String code = request.getParameter("verifycode");

        Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
        Long lockTime = (Long) session.getAttribute("lockTime");

        if (failedAttempts == null) failedAttempts = 0;

        if (lockTime != null) {
            long elapsedTime = System.currentTimeMillis() - lockTime;
            if (elapsedTime < LOCK_DURATION) {
                request.setAttribute("wrongAuthCode", "Bạn đã nhập sai quá nhiều lần. Yêu cầu nhập lại sau 5 phút!");
                request.getRequestDispatcher("./verify.jsp").forward(request, response);
                return;
            } else {
                session.removeAttribute("lockTime");
                session.setAttribute("failedAttempts", 0);
            }
        }

        if (code.equals(authcode)) {
            try {
                UserDAO.getInstance().SetVerifiedStatus(authcode);
                session.removeAttribute("failedAttempts");
                session.removeAttribute("lockTime");
                response.sendRedirect("./signIn.jsp");
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Nếu nhập sai
        failedAttempts++;
        session.setAttribute("failedAttempts", failedAttempts);

        if (failedAttempts > MAX_ATTEMPTS) {
            session.setAttribute("lockTime", System.currentTimeMillis());
            request.setAttribute("wrongAuthCode", "Bạn đã nhập sai quá nhiều lần. Yêu cầu nhập lại sau 5 phút!");
        } else {
            request.setAttribute("wrongAuthCode", "Mã xác thực chưa đúng! Bạn còn " + (MAX_ATTEMPTS - failedAttempts + 1) + " lần thử.");
        }

        request.getRequestDispatcher("./verify.jsp").forward(request, response);
    }
}
