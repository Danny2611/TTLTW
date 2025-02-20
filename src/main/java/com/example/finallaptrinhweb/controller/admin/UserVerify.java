package com.example.finallaptrinhweb.controller.admin;


import com.example.finallaptrinhweb.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/verify"
)
public class UserVerify extends HttpServlet {
    public UserVerify() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String authcode = (String) session.getAttribute("authcode");
        String code = request.getParameter("verifycode");
        if (code.equals(authcode)) {
            try {
                UserDAO.getInstance().SetVerifiedStatus(authcode);
            } catch (SQLException var7) {
                throw new RuntimeException(var7);
            }

            response.sendRedirect("./signIn.jsp");
        } else {
            request.setAttribute("wrongAuthCode", "Mã xác thực chưa đúng !");
            request.getRequestDispatcher("./verify.jsp").forward(request, response);
        }

    }
}
