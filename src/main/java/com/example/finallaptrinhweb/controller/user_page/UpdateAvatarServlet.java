package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.AvataDAO;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
@WebServlet("/updateAvatar")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 6 * 1024 * 1024
)
public class UpdateAvatarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            out.print("{\"status\": \"error\", \"message\": \"Người dùng chưa đăng nhập!\"}");
            out.flush();
            return;
        }

        Part filePart = request.getPart("file");
        if (filePart == null || filePart.getSize() == 0) {
            out.print("{\"status\": \"error\", \"message\": \"Vui lòng chọn ảnh để tải lên!\"}");
            out.flush();
            return;
        }

        File tempFile = File.createTempFile("avatar_", ".jpg");
        try (InputStream fileContent = filePart.getInputStream();
             FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        String avatarUrl = AvataDAO.uploadAvatar(tempFile);
        tempFile.delete();

        if (avatarUrl != null) {
            AvataDAO avataDAO = new AvataDAO();
            boolean updateSuccess = avataDAO.updateAvatar(user.getId(), avatarUrl);

            if (updateSuccess) {
                session.setAttribute("avatarUrl", avatarUrl);

                out.print("{\"status\": \"success\", \"avatarUrl\": \"" + avatarUrl + "\"}");
            } else {
                out.print("{\"status\": \"error\", \"message\": \"Không thể cập nhật ảnh đại diện!\"}");
            }
        } else {
            out.print("{\"status\": \"error\", \"message\": \"Lỗi khi tải ảnh lên Cloudinary!\"}");
        }

        out.flush();
    }
}
