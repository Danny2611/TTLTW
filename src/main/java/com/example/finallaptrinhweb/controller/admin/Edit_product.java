package com.example.finallaptrinhweb.controller.admin;

import com.cloudinary.utils.ObjectUtils;
import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.model.Product;
import com.example.finallaptrinhweb.utill.CloudinaryConfig;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@MultipartConfig
@WebServlet(urlPatterns = "/admin/edit-product")
public class Edit_product extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("current_page", "product");
        request.setAttribute("title", "Chỉnh sửa sản phẩm");

        String type = request.getParameter("type");
        if ("enterEdit".equalsIgnoreCase(type)) {
            request.setAttribute("type", "edit");
            request.setAttribute("title", "Chỉnh sửa sản phẩm");
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = ProductDAO.loadProductById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("edit-products.jsp").forward(request, response);
            return;
        }
        String productName = request.getParameter("productName");
        String purpose = request.getParameter("purpose");
        String contraindications = request.getParameter("contraindications");
        String ingredients = request.getParameter("ingredients");
        String dosage = request.getParameter("dosage");
        String instructions = request.getParameter("instructions");
        String warrantyPeriod = request.getParameter("warrantyPeriod");
        String storageCondition = request.getParameter("storageCondition");
        String productType = request.getParameter("productType");
        if ("edit".equalsIgnoreCase(type)) {
            int id = Integer.parseInt(request.getParameter("productId"));
            double price = Double.parseDouble(request.getParameter("price"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));

            String currentImageUrl = request.getParameter("currentImageUrl");
            Part filePart = request.getPart("image");
            if (filePart != null && filePart.getSize() > 0) {
                InputStream inputStream = filePart.getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int nRead;
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();

                // Upload ảnh mới lên Cloudinary hoặc lưu trữ ảnh theo cách khác
                Map uploadResult = CloudinaryConfig.getInstance().uploader().upload(buffer.toByteArray(), ObjectUtils.emptyMap());
                currentImageUrl = (String) uploadResult.get("secure_url");  // Cập nhật URL ảnh mới
            }

            request.setAttribute("type", "edit");
            request.setAttribute("title", "Chỉnh sửa");
            boolean isUpdate = ProductDAO.updateProduct(id, productName, categoryId, price, quantity,
                    purpose, contraindications, stockQuantity, ingredients, dosage,
                    instructions, warrantyPeriod, storageCondition, productType,
                    supplierId, currentImageUrl);
            Product product = ProductDAO.loadProductById(id);
            request.setAttribute("product", product);
            request.setAttribute("editSuccess", "true");
            request.getRequestDispatcher("edit-products.jsp").forward(request, response);
        }
    }
}

