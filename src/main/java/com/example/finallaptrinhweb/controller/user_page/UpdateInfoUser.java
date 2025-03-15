package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.*;
import com.example.finallaptrinhweb.model.Order;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.PrintWriter;
import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/updateinfouser")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class UpdateInfoUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            response.sendRedirect("./signIn.jsp");
            return;
        }
        AvataDAO avataDAO = new AvataDAO();
        String avata = avataDAO.getAvatarByUserId(user.getId());
        request.setAttribute("avata", avata);
        List<Order> orders = OrderDAO.loadOrderByUserId(user.getId());
        request.setAttribute("order", orders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("./user_info.jsp");
        dispatcher.forward(request, response);

    }

    @Override
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

        String fullName = request.getParameter("fullName");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String detail_address = request.getParameter("address");

        try {
            UserDAO.getInstance().updateUserInfor(user.getEmail(), fullName, birthday, city, district, ward, detail_address, phone);

            User updatedUser = UserDAO.getInstance().GetInfor(user.getEmail());
            session.setAttribute("auth", updatedUser);

            out.print("{\"status\": \"success\", \"message\": \"Cập nhật thông tin thành công!\"}");
        } catch (SQLException e) {
            out.print("{\"status\": \"error\", \"message\": \"Lỗi khi cập nhật dữ liệu!\"}");
        } finally {
            out.flush();
        }


    }

}