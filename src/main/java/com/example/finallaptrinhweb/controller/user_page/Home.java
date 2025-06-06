package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.dao.WishlistDAO;
import com.example.finallaptrinhweb.model.Product;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.finallaptrinhweb.controller.user_page.ImageService.Service.*;

@WebServlet("/user/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> allProduct = productDAO.getAllProducts();

        // Cập nhật url của tất cả sản phẩm vào csdl
        // Duyệt qua danh sách sản phẩm và cập nhật imageUrl
        String basePath = getServletContext().getRealPath("data\\sp_");
        for (Product product : allProduct) {
            int productId = product.getId();

            // Nếu imageUrl là null hoặc rỗng, cập nhật lại
            if (product.getImageUrl() == null || product.getImageUrl().isEmpty()) {
                String imageUrl = basePath + productId;
                String firstImagePath = getFirstImagePath(imageUrl);

                // Nếu có hình ảnh, cập nhật lại DB
                if (firstImagePath != null) {
                    productDAO.updateImgUrl(productId, firstImagePath);
                    product.setImageUrl(firstImagePath); // Cập nhật lại trong object để sử dụng sau này
                }
            }
        }

        // Danh sách sản phẩm
        List<Product> products = productDAO.getAllProductsLimited(0, 3);
        List<Product> threePoultryProducts = productDAO.getThreePoultryProducts();
        List<Product> tt = productDAO.getThreeOtherProducts();

        System.out.println(products);
        request.setAttribute("pro",threePoultryProducts);
        request.setAttribute("pr",tt);
        request.setAttribute("products", products);

        //Danh sách banner

        //Danh sách thương hiệu

        //danh sách sản phẩm trong wishlist lấy ra để kiểm tra
        WishlistDAO wishlistDAO = new WishlistDAO();
        User user = (User) request.getSession().getAttribute("auth");
        List<Integer> wishlistProductIds = new ArrayList<>();
        if (user != null) {
            wishlistProductIds = wishlistDAO.getWishListByUserID(user.getId());
        }
        request.setAttribute("wishlistProductIds", wishlistProductIds);

        request.getRequestDispatcher("/user/index.jsp").forward(request, response);

    }

}
