package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.SocialMediaDao;
import com.example.finallaptrinhweb.model.SocialMedia;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/setting")
public class Setting_direct extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "setting");

        String facebook = request.getParameter("facebook");
        String instagram = request.getParameter("instagram");
        String twitter = request.getParameter("twitter");
        String pinterest = request.getParameter("pinterest");
        String youtube = request.getParameter("youtube");
        String linkedin = request.getParameter("linkedin");

        // Thực hiện cập nhật thông tin social media
        boolean isSocialUpdated = SocialMediaDao.update(facebook, instagram, twitter, pinterest, youtube, linkedin);

        // Lấy thông tin social media sau khi cập nhật
        SocialMedia socialMedia = SocialMediaDao.load();

        // Đặt thông tin social media vào application scope
        getServletContext().setAttribute("social_media", socialMedia);

        // Chuyển hướng đến trang setting.jsp
        request.getRequestDispatcher("./setting.jsp").forward(request, response);
    }
}
