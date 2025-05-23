package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.finallaptrinhweb.model.Product;
import com.example.finallaptrinhweb.model.Supplier;

public class ProductDAO {
    private static Connection connection = null;

    public ProductDAO() {
        // Không cần thiết lập kết nối ở đây, sử dụng getConnection khi cần
    }

//    Lấy cho admin
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product product = mapResultSetToProduct(resultSet);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return products;
    }

    public List<Product> getAllProductsLimited(int start, int limit) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE active = ? LIMIT ?, ?";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setBoolean(1, true); // Điều kiện active = true
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return products;
    }
    // lấy danh sách sản phẩm tương tự theo cùng loại và danh mục
    public List<Product> getProductsByCategoryExcludingCurrent(int categoryId, String productType,  int currentProductId, int limit) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ? AND productType = ? AND id != ? AND active = ? LIMIT ?";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setString(2, productType);
            preparedStatement.setInt(3, currentProductId);
            preparedStatement.setBoolean(4, true);
            preparedStatement.setInt(5, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    public int getTotalProducts() {
        int total = 0;
        String query = "SELECT COUNT(*) FROM products";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                total = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return total;
    }

    public Product getProductById(int productId) {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = mapResultSetToProduct(resultSet);
                }
            }
        } catch (SQLException e) {

            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn

        }

        return product;
    }

    // suggesst Product  when search
    public List<String> getSuggestions(String term) {
        List<String> suggestions = new ArrayList<>();
        String query = "SELECT DISTINCT productName FROM products WHERE productName LIKE ? LIMIT 10";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setString(1, "%" + term + "%");  // Tìm kiếm ở bất kỳ vị trí nào

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    suggestions.add(resultSet.getString("productName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suggestions;
    }

    public List<Product> searchProducts(String searchTerm, boolean active) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE productName LIKE ? and active =? ";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");
            preparedStatement.setBoolean(2,active);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return products;
    }

    // Trong lớp ProductDAO
    public List<Product> searchProductsLimited(String searchTerm, int start, int pageSize) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE productName LIKE ? AND active = ? LIMIT ?, ?";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");
            preparedStatement.setBoolean(2, true);
            preparedStatement.setInt(3, start);
            preparedStatement.setInt(4, pageSize);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return products;
    }


    public int getTotalSearchResults(String searchTerm) {
        int total = 0;
        String query = "SELECT COUNT(*) FROM products WHERE productName LIKE ?";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    total = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý muốn
        }

        return total;
    }


    public List<Product> getAllProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ?";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setInt(1, categoryId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return products;
    }

    public Map<String, Integer> getListObject() {
        Map<String, Integer> products = new HashMap<>();
        String query = "SELECT pc.categoryName, COUNT(p.id) AS productCount\n" +
                "FROM product_categories pc\n" +
                "LEFT JOIN products p ON pc.id = p.category_id\n" +
                "GROUP BY pc.id, pc.categoryName;\n";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.put(resultSet.getString(1), resultSet.getInt(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }
        return products;
    }

    public List<Product> getProductByCategory(String object) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.* FROM products p " +
                "JOIN product_categories c ON p.category_id = c.id " +
                "WHERE c.categoryName = ? AND p.active = ? AND c.active = ?";


        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setString(1, object);
            preparedStatement.setBoolean(2, true);
            preparedStatement.setBoolean(3, true);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return products;
    }

    public List<Product> getProductByType(String productType) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM `products` WHERE productType = ? and active = ?";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setString(1, productType);
            preparedStatement.setBoolean(2, true);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return products;
    }


    public Map<String, Integer> getGroupListObject() {
        Map<String, Integer> groups = new HashMap<>();
        String query = "SELECT pg.groupName, COUNT(pc.id) AS productCount " +
                "FROM product_groups pg " +
                "LEFT JOIN product_categories pc ON pg.id = pc.group_id " +
                "GROUP BY pg.groupName";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    groups.put(resultSet.getString(1), resultSet.getInt(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }
        return groups;
    }

    public Map<String, Integer> getListProductType() {
        Map<String, Integer> groups = new HashMap<>();
        String query = "SELECT productType, COUNT(id) AS productCount\n" +
                "FROM products\n" +
                "GROUP BY productType;\n";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    groups.put(resultSet.getString(1), resultSet.getInt(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }
        return groups;
    }

    public List<Product> getProductByGroup(String groupName) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.* FROM products p " +
                "JOIN product_categories c ON p.category_id = c.id " +
                "JOIN product_groups g ON c.group_id = g.id " +
                "WHERE g.groupName = ? AND p.active = ? AND g.active =? and c.active= ?";


        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setString(1, groupName);
            preparedStatement.setBoolean(2, true);
            preparedStatement.setBoolean(3, true);
            preparedStatement.setBoolean(4, true);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return products;
    }

    public void updateImgUrl(int id, String imgUrl) {
        String query = "UPDATE `products` SET imageUrl = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setString(1, imgUrl);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductByIdWithSupplierInfo(int productId) {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = mapResultSetToProduct(resultSet);

                    // Lấy thông tin của nhà cung cấp từ SupplierDAO hoặc bất kỳ nguồn dữ liệu nào khác
                    SupplierDAO supplierDAO = new SupplierDAO();
                    Supplier supplier = supplierDAO.getSupplierById(product.getSupplierId());

                    // Set giá trị cho supplierImageUrl sử dụng phương thức setSupplierImageUrl trong Product
                    if (supplier != null) {
                        product.setSupplierImageUrl(supplier.getImageUrl());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return product;
    }

    public static int sumOfProduct(String sql) {
        int sum = 0;
        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    sum = rs.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sum;
    }

    public static Product loadProductById(int id) {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = mapResultSetToProduct(resultSet);

                    // Lấy thông tin của nhà cung cấp từ SupplierDAO hoặc bất kỳ nguồn dữ liệu nào khác
                    SupplierDAO supplierDAO = new SupplierDAO();
                    Supplier supplier = supplierDAO.getSupplierById(product.getSupplierId());

                    // Set giá trị cho supplierImageUrl sử dụng phương thức setSupplierImageUrl trong Product
                    if (supplier != null) {
                        product.setSupplierImageUrl(supplier.getImageUrl());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return product;
    }

    public static boolean updateProduct(int id, String productName, int categoryId, double price, int quantity,
                                        String purpose, String contraindications, int stockQuantity, String ingredients, String dosage,
                                        String instructions, String warrantyPeriod, String storageCondition, String productType,
                                        int supplierId, String currentImageUrl) {

        String sql = "UPDATE products SET productName=?, category_id=?, price=?, quantity=?, purpose=?, "
                + "contraindications=?, stockQuantity=?, ingredients=?, dosage=?, instructions=?, warrantyPeriod=?, "
                + "storageCondition=?, productType=?, supplier_id=?, imageUrl=? WHERE id=?";

        int update = 0;

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql)) {
            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setString(5, purpose);
            preparedStatement.setString(6, contraindications);
            preparedStatement.setInt(7, stockQuantity);
            preparedStatement.setString(8, ingredients);
            preparedStatement.setString(9, dosage);
            preparedStatement.setString(10, instructions);
            preparedStatement.setString(11, warrantyPeriod);
            preparedStatement.setString(12, storageCondition);
            preparedStatement.setString(13, productType);
            preparedStatement.setInt(14, supplierId);
            preparedStatement.setString(15, currentImageUrl);
            preparedStatement.setInt(16, id);

            update = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return update == 1;
    }

    public static boolean deleteProductById(int productId) {
        String query = "DELETE FROM products WHERE id = ?";
        int deletedRows = 0;

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setInt(1, productId);
            deletedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return deletedRows > 0;
    }
    public static boolean changeActiveProductById(int productId) {
        String query = "Update products SET active= NOT active  WHERE id = ?";
        int updatedRows  = 0;

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setInt(1, productId);
            updatedRows  = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return updatedRows  > 0;
    }

    public static void addProduct(Product product) {
        String query = "INSERT INTO `products`(`id`, `productCode`, `productName`, `category_id`, `price`, `quantity`, `purpose`, `contraindications`, `stockQuantity`, `ingredients`, `dosage`, `instructions`, `warrantyPeriod`, `storageCondition`, `productType`,`supplier_id`, `imageUrl`, `active`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, String.valueOf(product.getId()));
            preparedStatement.setString(3, product.getProductName());
            preparedStatement.setInt(4, product.getCategoryId());
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.setInt(6, product.getQuantity());
            preparedStatement.setString(7, product.getPurpose());
            preparedStatement.setString(8, product.getContraindications());
            preparedStatement.setInt(9, product.getStockQuantity());
            preparedStatement.setString(10, product.getIngredients());
            preparedStatement.setString(11, product.getDosage());
            preparedStatement.setString(12, product.getInstructions());
            preparedStatement.setString(13, product.getWarrantyPeriod());
            preparedStatement.setString(14, product.getStorageCondition());
            preparedStatement.setString(15, product.getProductType());
            preparedStatement.setInt(16, product.getSupplierId());
            preparedStatement.setString(17, product.getImageUrl());
            preparedStatement.setInt(18, product.isActive() ? 1 : 0);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }
    }

    public static List<Product> getThreePoultryProducts() {
        List<Product> threePoultryProducts = new ArrayList<>();
        String query = "SELECT * FROM products WHERE productType = 'Gia cầm' LIMIT 3";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product giaCam = mapResultSetToProduct(resultSet);
                threePoultryProducts.add(giaCam);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return threePoultryProducts;
    }

    public static List<Product> getThreeOtherProducts() {
        List<Product> threeOtherProducts = new ArrayList<>();
        String query = "SELECT * FROM products WHERE productType = 'Khác' LIMIT 3";

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product product = mapResultSetToProduct(resultSet);
                threeOtherProducts.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return threeOtherProducts;
    }


    private static Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setProductName(resultSet.getString("productName"));
        product.setCategoryId(resultSet.getInt("category_id"));
        product.setPrice(resultSet.getDouble("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setPurpose(resultSet.getString("purpose"));
        product.setContraindications(resultSet.getString("contraindications"));
        product.setStockQuantity(resultSet.getInt("stockQuantity"));
        product.setIngredients(resultSet.getString("ingredients"));
        product.setDosage(resultSet.getString("dosage"));
        product.setInstructions(resultSet.getString("instructions"));
        product.setWarrantyPeriod(resultSet.getString("warrantyPeriod"));
        product.setStorageCondition(resultSet.getString("storageCondition"));
        product.setProductType(resultSet.getString("productType"));
        product.setSupplierId(resultSet.getInt("supplier_id"));
        product.setImageUrl(resultSet.getString("imageUrl"));
        product.setActive(resultSet.getBoolean("active"));

        // Bổ sung các trường thông tin khác nếu cần

        return product;
    }

    public int getQuantityByProductId(int productId){
        int quantity = 0;
        String sql = "select quantity from products where id = ?";
        try {
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  quantity;

    }

    public void updateStockQuantity(int productId, int quantity) throws SQLException {
        String sql = "update products set stockQuantity = stockQuantity + ? where id = ?";
        PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
        preparedStatement.setInt(1, quantity);
        preparedStatement.setInt(2, productId);
        preparedStatement.executeUpdate();
    }
    public static boolean updateStockProduct(int product_id, int quantity) {
        try {
            String query = "UPDATE `products` SET `stockQuantity` = stockQuantity - ? WHERE id = ?";
            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
                preparedStatement.setInt(1, quantity);
                preparedStatement.setInt(2, product_id);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}