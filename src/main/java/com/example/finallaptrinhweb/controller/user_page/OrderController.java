package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.OrderDAO;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/order")
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("auth");
        if(user == null){
            resp.sendRedirect(req.getContextPath() + "/user/signIn.jsp");
            return;
        }
        else {
            OrderDAO orderDAO = new OrderDAO();
            System.out.println(orderDAO.loadOrderByUserId(user.getId()).size());

            req.setAttribute("orders",OrderDAO.loadOrderByUserId(user.getId()));
            req.getRequestDispatcher("order.jsp").forward(req, resp);
            return;
        }
    }
}
