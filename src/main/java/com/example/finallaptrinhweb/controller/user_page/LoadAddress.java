package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.model.Address;
import com.example.finallaptrinhweb.dao.LoadfooterDao;
import com.example.finallaptrinhweb.dao.SocialMediaDao;
import com.example.finallaptrinhweb.model.SocialMedia;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/loadAddress", loadOnStartup = 1)
public class LoadAddress extends HttpServlet {

    public void init() throws ServletException {
        // Gọi hàm để load thông tin địa chỉ và đặt vào application scope
        List<Address> addresses = LoadfooterDao.loadAddresses();
        getServletContext().setAttribute("address", addresses);

        //Gọi hàm để load thông tin social media và đặt vào application scope
        SocialMedia socialMedia = SocialMediaDao.load();
        getServletContext().setAttribute("social_media", socialMedia);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Có thể thực hiện các hành động khác nếu cần
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Có thể thực hiện các hành động khác nếu cần
    }
}
