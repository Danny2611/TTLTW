package com.example.finallaptrinhweb.controller.user_page;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/users/validate-password")
public class ValidatePasswordAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password = req.getParameter("password");
        Map<String, Object> responseMap = new HashMap<>();
        List<String> errors = new ArrayList<>();

        if (password == null || password.trim().isEmpty()) {
            errors.add("Mật khẩu không được để trống.");
        } else {
            if (password.length() < 8) {
                errors.add("Mật khẩu phải có ít nhất 8 ký tự.");
            }
            if (!password.matches(".*[A-Z].*")) {
                errors.add("Mật khẩu phải chứa ít nhất 1 chữ cái in hoa.");
            }
            if (!password.matches(".*\\d.*")) {
                errors.add("Mật khẩu phải chứa ít nhất 1 chữ số.");
            }
            if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
                errors.add("Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt.");
            }
        }

        if (errors.isEmpty()) {
            responseMap.put("valid", true);
        } else {
            responseMap.put("valid", false);
            responseMap.put("errors", errors);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(responseMap));
    }
}
