package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.model.Product;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("admin/ImportExcelServlet")
@MultipartConfig
public class ExcelController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        List<Map<String, Object>> result = new ArrayList<>();

        Part filePart = request.getPart("excelFile");
        InputStream fileContent = filePart.getInputStream();

//        try (Workbook workbook = new XSSFWorkbook(fileContent)) {
//            Sheet sheet = workbook.getSheetAt(0);
//
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) continue; // Bỏ dòng tiêu đề
//
//                String id = row.getCell(0).getStringCellValue(); // Cột ID
//                String name = row.getCell(1).getStringCellValue(); // Cột Name
//                int quantity = (int) row.getCell(2).getNumericCellValue(); // Cột Quantity
//
//                ProductDAO productDAO = new ProductDAO();
//                Product existing = productDAO.getProductById(Integer.parseInt(id));
//
//                if (existing != null) {
//                    existing.setQuantity(existing.getQuantity() + quantity);
//                    productDAO.(existing);
//                    result.add(Map.of("id", id, "action", "updated"));
//                } else {
//                    Product newProduct = new Product(id, name, quantity);
//                    ProductDAO.insert(newProduct);
//                    result.add(Map.of("id", id, "action", "created"));
//                }
//            }
//
//            out.println(new Gson().toJson(Map.of("success", true, "data", result)));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            out.println(new Gson().toJson(Map.of("success", false, "message", e.getMessage())));
//        }
    }
}
