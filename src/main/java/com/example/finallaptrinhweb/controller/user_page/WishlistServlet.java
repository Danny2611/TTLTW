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
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/user/wishlist")
public class WishlistServlet  extends HttpServlet {
    private static  final Logger logger = Logger.getLogger(WishlistServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("auth");
        if(user ==null) {
            resp.sendRedirect(req.getContextPath() +"/signIn");
        }
        WishlistDAO wishlistDAO = new WishlistDAO();
        ProductDAO productDAO = new ProductDAO();
        List<Integer> wishlist = wishlistDAO.getWishListByUserID(user.getId());

        List<Product> listProduct = new ArrayList<>();
        Set<Integer> categoryIds = new HashSet<>();

        for (int productID : wishlist) {
            Product product = productDAO.getProductById(productID);
            if (product != null) {
                listProduct.add(product);
                categoryIds.add(product.getCategoryId());
            }
        }

        Map<Integer, List<Product>> productsByCategory = new HashMap<>();
        if (categoryIds.isEmpty()) {
            List<Product> allProducts = productDAO.getAllProducts();
            productsByCategory.put(0, allProducts);
        } else {
            for (int categoryId : categoryIds) {
                List<Product> products = productDAO.getAllProductsByCategory(categoryId);
                productsByCategory.put(categoryId, products);
            }
        }
        System.out.println(productsByCategory);

        req.setAttribute("wishlist", listProduct);
        req.setAttribute("wishlistIds", wishlist);
        req.setAttribute("productsByCategory", productsByCategory);

        req.getRequestDispatcher("./wishList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("auth");
        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        int productId = Integer.parseInt(req.getParameter("productId"));
        WishlistDAO wishlistDAO = new WishlistDAO();
        boolean success;
        boolean isInWishlist = wishlistDAO.isInWishlist(user.getId(), productId);

        if (isInWishlist) {
            success = wishlistDAO.removeFromWishlist(user.getId(), productId);
        } else {
            success = wishlistDAO.addToWishlist(user.getId(), productId);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        if (success) {
            logger.info("Wishlist push successfully");
            String action = isInWishlist ? "removed" : "added";
            out.print("{\"status\": \"success\", \"productId\": " + productId + ", \"action\": \"" + action + "\"}");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            logger.error("Wishlist push failure");
            out.print("{\"status\": \"error\"}");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        out.flush();
    }

}
