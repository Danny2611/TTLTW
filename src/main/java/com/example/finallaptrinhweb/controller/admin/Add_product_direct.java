package com.example.finallaptrinhweb.controller.admin;
import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.model.Product;
import jakarta.servlet.annotation.WebServlet;

import com.example.finallaptrinhweb.dao.CategoryDao;
import com.example.finallaptrinhweb.model.ProductGroups;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/admin/add-product")
public class Add_product_direct extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Không cần kiểm tra doPost nữa vì đã xử lý ở phương thức doGet

        // Lấy giá trị từ form
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
        String img = request.getParameter("img");
        String imgSup = request.getParameter("imgsup");

        // Tạo đối tượng Product và set giá trị
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
        product.setImageUrl(img);
        product.setSupplierImageUrl(imgSup);
        product.setActive(true);

        // Thêm sản phẩm vào cơ sở dữ liệu
        ProductDAO dao = new ProductDAO();
        dao.addProduct(product);

        // Chuyển hướng về trang sản phẩm sau khi thêm
        response.sendRedirect("product");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("add-product.jsp").forward(request, response);
    }
}