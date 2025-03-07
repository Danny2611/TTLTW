package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.dao.WishlistDAO;
import com.example.finallaptrinhweb.model.Product;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/wishlist")
public class WishlistServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("auth");

        WishlistDAO wishlistDAO = new WishlistDAO();
        ProductDAO productDAO = new ProductDAO();
        List<Integer> wishlist = wishlistDAO.getWishListByUserID(user.getId());
        System.out.println(wishlist);
        List<Product> listProduct = new ArrayList<>();
        for (int productID : wishlist) {
             Product product = productDAO.getProductById(productID);
             listProduct.add(product);
        }
        System.out.println(listProduct);
        req.setAttribute("wishlist", listProduct);
        req.getRequestDispatcher("./wishList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
