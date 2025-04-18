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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/ImportExcelServlet")
@MultipartConfig
public class ExcelController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        List<Map<String, Object>> result = new ArrayList<>();

        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();

        try (Workbook workbook = new XSSFWorkbook(fileContent)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Bỏ dòng tiêu đề

                String id = getCellValueAsString(row.getCell(0)); // Cột ID
                String name = getCellValueAsString(row.getCell(1));; // Cột Name
                int quantity = (int) row.getCell(2).getNumericCellValue(); // Cột Quantity
                System.out.println("name" + name);
                ProductDAO productDAO = new ProductDAO();
                Product existing = productDAO.getProductById(Integer.parseInt(id));

                if (existing != null) {
                    productDAO.updateQuantity(existing.getId(), quantity);
                    result.add(Map.of("id", id, "action", "updated"));
                } else {
//                    Product newProduct = new Product(id, name, quantity);
//                    ProductDAO.addProduct(newProduct);
                    result.add(Map.of("id", id, "action", "created"));
                }
            }

            out.println(new Gson().toJson(Map.of("success", true, "data", result)));

        } catch (Exception e) {
            e.printStackTrace();
            out.println(new Gson().toJson(Map.of("success", false, "message", e.getMessage())));
        }
    }
    public static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue()); // hoặc giữ dạng double nếu muốn
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

}
