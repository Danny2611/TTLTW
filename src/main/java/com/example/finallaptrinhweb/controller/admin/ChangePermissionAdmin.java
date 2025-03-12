package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.UserDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/change-permission")
public class ChangePermissionAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Thiết lập kiểu phản hồi là JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        try {
            // Đọc JSON từ request body
            BufferedReader reader = req.getReader();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            // Lấy giá trị roleId và userId từ JSON
            int roleId = jsonObject.get("roleId").getAsInt();
            int userId = jsonObject.get("userId").getAsInt();

            System.out.println("roleId: " + roleId + ", userId: " + userId);

            // Gọi DAO để cập nhật quyền
            UserDAO userDAO = new UserDAO();
            boolean success = userDAO.changePermission(roleId, userId);

            // Trả về phản hồi JSON
            JsonObject responseJson = new JsonObject();
            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                responseJson.addProperty("status", "success");
                responseJson.addProperty("message", "Cập nhật quyền thành công!");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseJson.addProperty("status", "error");
                responseJson.addProperty("message", "Cập nhật quyền thất bại!");
            }

            out.print(responseJson.toString());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonObject errorJson = new JsonObject();
            errorJson.addProperty("status", "error");
            errorJson.addProperty("message", "Đã xảy ra lỗi trên server!");
            out.print(errorJson.toString());
            e.printStackTrace();
        } finally {
            out.flush();
        }
    }
}
