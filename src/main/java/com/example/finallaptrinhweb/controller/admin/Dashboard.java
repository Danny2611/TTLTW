package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.OrderDAO;
import com.example.finallaptrinhweb.dao.UserDAOT;
import com.example.finallaptrinhweb.model.User;
import com.example.finallaptrinhweb.dao.SupplierDAO;
import com.example.finallaptrinhweb.model.Supplier;
import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.model.Product;
import com.example.finallaptrinhweb.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(urlPatterns = "/admin/dashboard")
public class Dashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "dashboard");
        request.setAttribute("title", "Dashboard");
        // total user
        int countUser = UserDAOT.sumOfUser("SELECT COUNT(*) FROM users");
        request.setAttribute("c_user", countUser);
        // total product
        int countProduct = ProductDAO.sumOfProduct("SELECT COUNT(*) FROM products");
        request.setAttribute("c_product", countProduct);

        int countSupplier = SupplierDAO.sumOfSupplier("SELECT COUNT(*) FROM suppliers");
        request.setAttribute("c_supplier", countSupplier);

        List<Order> orderList = OrderDAO.loadOrderNear(5);
        request.setAttribute("orderlist", orderList);


        request.getRequestDispatcher("./dashboard.jsp").forward(request, response);
    }
}