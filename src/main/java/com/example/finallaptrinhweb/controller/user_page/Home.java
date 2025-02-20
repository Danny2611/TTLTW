package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
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
            String imageUrl = basePath + productId;

            // Lấy đường dẫn của hình ảnh đầu tiên
            String firstImagePath = getFirstImagePath(imageUrl);

            // Lưu đường dẫn vào csdl
            productDAO.updateImgUrl(productId, firstImagePath);

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

        request.getRequestDispatcher("/user/index.jsp").forward(request, response);

    }

}
