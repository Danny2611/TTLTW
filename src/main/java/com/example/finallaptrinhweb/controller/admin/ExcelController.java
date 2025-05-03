package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.CategoryDao;
import com.example.finallaptrinhweb.dao.ProductDAO;
import com.example.finallaptrinhweb.dao.SupplierDAO;
import com.example.finallaptrinhweb.model.Product;
import com.example.finallaptrinhweb.model.User;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;
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
    private static  final Logger logger = Logger.getLogger(ExcelController.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



//         user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("adminAuth");



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

                String price = getCellValueAsString(row.getCell(3));
                String supplierName = getCellValueAsString(row.getCell(4));
                String productCode = getCellValueAsString(row.getCell(5));
                String category = getCellValueAsString(row.getCell(6));
                String purpose = getCellValueAsString(row.getCell(7));
                String contraindication = getCellValueAsString(row.getCell(8));
                String stock = getCellValueAsString(row.getCell(9));
                String ingredients = getCellValueAsString(row.getCell(10));
                String dosage = getCellValueAsString(row.getCell(11));
                String instructions = getCellValueAsString(row.getCell(12));
                String warrantyPeriod = getCellValueAsString(row.getCell(13));
                String storageCondition = getCellValueAsString(row.getCell(14));
                String productType = getCellValueAsString(row.getCell(15));
                String image = getCellValueAsString(row.getCell(16));
                String active = getCellValueAsString(row.getCell(17));
                System.out.println("name" + name);
                ProductDAO productDAO = new ProductDAO();
                Product existing = productDAO.getProductById(Integer.parseInt(id));

                logger.info("User "+ user.getEmail() + "import inventory with excel");
                if (existing != null) {
                    System.out.println("update quantity with productName" + existing.getProductName() + "with quantity "+ quantity);
                    productDAO.updateStockQuantity(existing.getId(), quantity);
                    logger.info("update stockQuantity successfully");
                    result.add(Map.of("id", id, "action", "updated"));
                } else {
                    System.out.println("create");
                    int idCate = CategoryDao.getInstance().getCategory(category);
                    Product product = new Product();
                    product.setProductName(name);
                    product.setPrice(Double.valueOf(price));
                    product.setCategoryId(idCate);
                    product.setQuantity(quantity);
                    product.setPurpose(purpose);
                    product.setContraindications(contraindication);
                    product.setStockQuantity(Integer.parseInt(stock));
                    product.setIngredients(ingredients);
                    product.setDosage(dosage);
                    product.setInstructions(instructions);
                    product.setWarrantyPeriod(warrantyPeriod);
                    product.setProductType(productType);
                    product.setStorageCondition(storageCondition);
                    product.setSupplierId(SupplierDAO.getSupplierByName(supplierName));
                    product.setImageUrl(image); // Lưu URL ảnh
                    product.setSupplierImageUrl(null); // Nếu không dùng thì có thể bỏ
                    product.setActive(true);

                    // Lưu vào DB
                    productDAO.addProduct(product);
                    logger.info("create product successfully");
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
                    return String.valueOf((long) cell.getNumericCellValue());
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
