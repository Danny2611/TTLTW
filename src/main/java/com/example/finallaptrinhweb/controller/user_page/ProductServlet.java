package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageSize = 12;
        int pageNumber = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
        int start = (pageNumber - 1) * pageSize;

        ProductDAO productDAO = new ProductDAO();
        List<Product> products;
        int totalProducts = 0;

        String searchTerm = request.getParameter("searchTerm");
        String object = request.getParameter("category");
        String group = request.getParameter("group");
        String productType = request.getParameter("type");

        // Kiểm tra xem đã lọc theo nhóm sản phẩm hay không
        boolean isFilteringByGroup = group != null;
        request.setAttribute("isFilteringByGroup", isFilteringByGroup);

        if (productType != null) {
            products = productDAO.getProductByType(productType);
            request.setAttribute("filteredProducts", products);
        } else if (searchTerm != null) {
            products = productDAO.searchProductsLimited(searchTerm, start, pageSize);
            totalProducts = productDAO.getTotalSearchResults(searchTerm);
            if (products.isEmpty()) {
                request.setAttribute("noProductsFound", true);
            }
        } else if (object != null) {
            products = productDAO.getProductByCategory(object);
            request.setAttribute("selectedCategory", object);
        } else if (isFilteringByGroup) { // Nếu đang lọc theo nhóm sản phẩm
            products = productDAO.getProductByGroup(group);
        } else {
            products = productDAO.getAllProductsLimited(start, pageSize);
            totalProducts = productDAO.getTotalProducts();
        }

        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        Map<String, Integer> listObject = productDAO.getListObject();
        Map<String, Integer> listProductType = productDAO.getListProductType();
        Map<String, Integer> listGroups = productDAO.getGroupListObject();

        request.setAttribute("product", products);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("objects", listObject.entrySet());
        request.setAttribute("proTypes", listProductType.entrySet());
        request.setAttribute("groups", listGroups.entrySet());
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("isSearchPage", searchTerm != null);

        String decodedQueryString = buildQueryString(request, object, group, productType, searchTerm);

        String contextPath = request.getContextPath();
        if (decodedQueryString.startsWith(contextPath)) {
            decodedQueryString = decodedQueryString.substring(contextPath.length());
        }

        String url = decodedQueryString.substring(decodedQueryString.indexOf("=") + 1);
        request.setAttribute("url", url);

        request.getRequestDispatcher("./product.jsp").forward(request, response);
    }

    private String buildQueryString(HttpServletRequest request, String object, String group, String productType, String searchTerm) {
        StringBuilder urlBuilder = new StringBuilder(request.getContextPath());

        if (object != null) {
            urlBuilder.append("&category=").append(object);
        }

        if (group != null) {
            urlBuilder.append("&group=").append(group);
        }

        if (searchTerm != null) {
            urlBuilder.append("&searchTerm=").append(searchTerm);
        }

        if (productType != null) {
            urlBuilder.append("&productType=").append(productType);
        }

        return urlBuilder.toString();
    }
}
