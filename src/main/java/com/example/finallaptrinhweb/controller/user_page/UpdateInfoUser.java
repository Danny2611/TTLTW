package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.OrderDAO;
import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.dao.UserDAOT;
import com.example.finallaptrinhweb.dao.OrderProductDAO;
import com.example.finallaptrinhweb.model.Order;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/updateinfouser")
public class UpdateInfoUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            response.sendRedirect("./signIn.jsp");
            return;
        }

        // Lấy danh sách đơn hàng để hiển thị (nếu cần)
        List<Order> orders = OrderDAO.loadOrderByUserId(user.getId());
        request.setAttribute("order", orders);

        // Chuyển hướng đến trang thông tin người dùng
        RequestDispatcher dispatcher = request.getRequestDispatcher("./user_info.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        // Kiểm tra xem user có tồn tại không
        if (user == null) {
            // Xử lý khi người dùng không tồn tại, có thể chuyển hướng hoặc xử lý khác tùy vào yêu cầu của bạn.
            response.sendRedirect("./signIn.jsp"); // Ví dụ chuyển hướng đến trang đăng nhập
            return;
        }

        // Tiếp tục với việc lấy thông tin từ request
        String fullName = request.getParameter("fullName");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String detail_address = request.getParameter("address");


        List<Order> orders = OrderDAO.loadOrderByUserId(user.getId());
        request.setAttribute("order", orders);

        try {
            UserDAO.getInstance().updateUserInfor(user.getEmail(), fullName, birthday, city, district, ward, detail_address, phone);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./user_info.jsp");
            User updatedUser = UserDAO.getInstance().GetInfor(user.getEmail());
            session.removeAttribute("auth");
            session.setAttribute("auth", updatedUser);
            System.out.println("Updated user: " + updatedUser);
            System.out.println("Session auth: " + session.getAttribute("auth"));

            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}