package com.example.finallaptrinhweb.controller.admin;
import com.cloudinary.utils.ObjectUtils;
import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.model.Product;
import com.example.finallaptrinhweb.utill.CloudinaryConfig;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import com.example.finallaptrinhweb.dao.CategoryDao;
import com.example.finallaptrinhweb.model.ProductGroups;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/admin/add-product")
@MultipartConfig
public class Add_product_direct extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String category = request.getParameter("cate");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String purpose = request.getParameter("purpose");
        String contrain = request.getParameter("contrain");
        String stock = request.getParameter("stock");
        String ingre = request.getParameter("ingre");
        String dosage = request.getParameter("dosage");
        String instruc = request.getParameter("instruc");
        String period = request.getParameter("period");
        String type = request.getParameter("type");
        String store = request.getParameter("store");
        String idSup = request.getParameter("idsup");

        // Xử lý upload ảnh
        Part filePart = request.getPart("image"); // Tên input file
        String imageUrl = null;

        if (filePart != null && filePart.getSize() > 0) {
            InputStream inputStream = filePart.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int nRead;
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();

            Map uploadResult = CloudinaryConfig.getInstance().uploader().upload(buffer.toByteArray(), ObjectUtils.emptyMap());

            imageUrl = (String) uploadResult.get("secure_url");
        }

        // Tạo và gán thông tin cho Product
        Product product = new Product();
        product.setProductName(name);
        product.setPrice(Double.valueOf(price));
        product.setCategoryId(Integer.parseInt(category));
        product.setQuantity(Integer.parseInt(quantity));
        product.setPurpose(purpose);
        product.setContraindications(contrain);
        product.setStockQuantity(Integer.parseInt(stock));
        product.setIngredients(ingre);
        product.setDosage(dosage);
        product.setInstructions(instruc);
        product.setWarrantyPeriod(period);
        product.setProductType(type);
        product.setStorageCondition(store);
        product.setSupplierId(Integer.parseInt(idSup));
        product.setImageUrl(imageUrl); // Lưu URL ảnh
        product.setSupplierImageUrl(null); // Nếu không dùng thì có thể bỏ
        product.setActive(true);

        // Lưu vào DB
        ProductDAO dao = new ProductDAO();
        dao.addProduct(product);

        // Chuyển hướng sau khi thêm
        response.sendRedirect("add-product?success=true");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("add-product.jsp").forward(request, response);
    }
}
