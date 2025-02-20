package com.example.finallaptrinhweb.controller.user_page;


import com.example.finallaptrinhweb.controller.user_page.GoogleService.Service;
import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/loginbygoogle"
)
public class LogInByGoogle extends HttpServlet {
    public LogInByGoogle() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = Service.getToken(code);
        User user = null;
        Boolean userExist = false;

        try {
            user = Service.getUserInfo(accessToken);
            userExist = UserDAO.getInstance().CheckExistUser(user.getEmail());
        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }

        try {
            if (user != null && !userExist) {
                UserDAO.getInstance().SignUp(user.getUsername(), user.getEmail(), (String) null, "verified", 1);
            }

            HttpSession session = request.getSession();
            session.setAttribute("auth", user);
            response.sendRedirect("./home");
        } catch (SQLException var8) {
            throw new RuntimeException(var8);
        }
    }
}
