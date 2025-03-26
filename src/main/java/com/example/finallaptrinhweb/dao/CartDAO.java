package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.CartItem;
import com.example.finallaptrinhweb.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    ProductDAO productDAO = new ProductDAO();
    public boolean addToCart(int userId, int productId){
        try {
            // 1. Kiểm tra giỏ hàng 'pending' của user
            int cartId = getOrCreateCart(userId);

            System.out.println("cartId"+ cartId);
            // 2. Thêm sản phẩm vào chi tiết giỏ hàng hoặc cập nhật số lượng
            return addToCartDetailOrUpdateQuantity(cartId, productId, 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addToCartDetailOrUpdateQuantity(int cartId, int productId, int quantity) {
        if(quantity > productDAO.getQuantityByProductId(productId)){
            throw  new RuntimeException("Số lượng tồn kho không đủ");
        }
        try {
            // 1. Kiểm tra sản phẩm đã có trong giỏ chưa
            String checkQuery = "SELECT quantity FROM cart_details WHERE id = ? AND productId = ?";
            try (PreparedStatement checkStmt = DBCPDataSource.preparedStatement(checkQuery)) {
                checkStmt.setInt(1, cartId);
                checkStmt.setInt(2, productId);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    // 2. Nếu có, cập nhật số lượng
                    int currentQuantity = rs.getInt("quantity");
                    return updateQuantity(cartId, productId, currentQuantity + quantity);
                } else {
                    // 3. Nếu chưa có, thêm mới vào giỏ hàng
                    return insertIntoCartDetail(cartId, productId, quantity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private int getOrCreateCart(int userId) throws SQLException {
        // 1. Kiểm tra giỏ hàng 'pending' của user
        String findCartQuery = "SELECT cartId FROM carts WHERE userId = ? AND status = 'pending'";
        try (PreparedStatement findCartStmt = DBCPDataSource.preparedStatement(findCartQuery)) {
            findCartStmt.setInt(1, userId);
            ResultSet rs = findCartStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cartId"); // Trả về cartId nếu đã tồn tại
            }
        }

        // 2. Nếu chưa có, tạo giỏ hàng mới
        String createCartQuery = "INSERT INTO carts (userId, status) VALUES (?, 'pending')";
        try (PreparedStatement createCartStmt = DBCPDataSource.preparedStatementReturnKey(createCartQuery, Statement.RETURN_GENERATED_KEYS)) {
            createCartStmt.setInt(1, userId);
            createCartStmt.executeUpdate();

            // 3. Lấy cartId vừa tạo
            try (ResultSet generatedKeys = createCartStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }

        throw new SQLException("Không thể tạo hoặc tìm thấy giỏ hàng!");
    }




    private boolean updateQuantity(int cartId, int productId, int newQuantity) throws SQLException {
        String updateQuery = "UPDATE cart_details SET quantity = ? WHERE id = ? AND productId = ?";
        try (PreparedStatement updateStmt = DBCPDataSource.preparedStatement(updateQuery)) {
            updateStmt.setInt(1, newQuantity);
            updateStmt.setInt(2, cartId);
            updateStmt.setInt(3, productId);
            return updateStmt.executeUpdate() > 0;
        }
    }
    private boolean insertIntoCartDetail(int cartId, int productId, int quantity) throws SQLException {
        String insertQuery = "INSERT INTO cart_details (id, productId, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = DBCPDataSource.preparedStatement(insertQuery)) {
            insertStmt.setInt(1, cartId);
            insertStmt.setInt(2, productId);
            insertStmt.setInt(3, quantity);
            return insertStmt.executeUpdate() > 0;
        }
    }
    public boolean incrementProduct(int userId, int productId) {
        try {
            int cartId = getOrCreateCart(userId);
            int stock = productDAO.getQuantityByProductId(productId);

            String query = "UPDATE cart_details SET quantity = quantity + 1 " +
                    "WHERE cartId = ? AND productId = ? AND quantity < ?";
            try (PreparedStatement stmt = DBCPDataSource.preparedStatement(query)) {
                stmt.setInt(1, cartId);
                stmt.setInt(2, productId);
                stmt.setInt(3, stock);
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean decrementProduct(int userId, int productId) {
        try {
            int cartId = getOrCreateCart(userId);

            String query = "UPDATE cart_details SET quantity = quantity - 1 " +
                    "WHERE cartId = ? AND productId = ? AND quantity > 0";
            try (PreparedStatement stmt = DBCPDataSource.preparedStatement(query)) {
                stmt.setInt(1, cartId);
                stmt.setInt(2, productId);
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<CartItem> getCartByUserId(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        String query = "SELECT p.id, p.productName, p.price, cd.quantity  FROM cart_details cd JOIN carts c ON cd.id = c.cartId JOIN products p ON cd.productId = p.id WHERE c.userId = ? ";

        try (
             PreparedStatement stmt = DBCPDataSource.preparedStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("productName"),
                            rs.getDouble("price")
                    );
                    CartItem item = new CartItem(product, rs.getInt("quantity"));
                    cartItems.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }

}
