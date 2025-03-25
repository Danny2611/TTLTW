package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CartDAO {

    ProductDAO productDAO = new ProductDAO();
    public boolean addToCart(int userId, int productId){
        try {
            // 1. Kiểm tra giỏ hàng 'pending' của user
            int cartId = getOrCreateCart(userId);

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
            String checkQuery = "SELECT quantity FROM cart_detail WHERE cartId = ? AND productId = ?";
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
        String findCartQuery = "SELECT id FROM cart WHERE userId = ? AND status = 'pending'";
        try (PreparedStatement findCartStmt = DBCPDataSource.preparedStatement(findCartQuery)) {
            findCartStmt.setInt(1, userId);
            ResultSet rs = findCartStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        String createCartQuery = "INSERT INTO cart (userId, status) VALUES (?, 'pending')";
        try (PreparedStatement createCartStmt = DBCPDataSource.preparedStatement(createCartQuery)) {
            createCartStmt.setInt(1, userId);
            createCartStmt.executeUpdate();

            ResultSet generatedKeys = createCartStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Trả về cartId mới tạo
            }
        }

        throw new SQLException("Không thể tạo giỏ hàng!");
    }

    private boolean updateQuantity(int cartId, int productId, int newQuantity) throws SQLException {
        String updateQuery = "UPDATE cart_detail SET quantity = ? WHERE cartId = ? AND productId = ?";
        try (PreparedStatement updateStmt = DBCPDataSource.preparedStatement(updateQuery)) {
            updateStmt.setInt(1, newQuantity);
            updateStmt.setInt(2, cartId);
            updateStmt.setInt(3, productId);
            return updateStmt.executeUpdate() > 0;
        }
    }
    private boolean insertIntoCartDetail(int cartId, int productId, int quantity) throws SQLException {
        String insertQuery = "INSERT INTO cart_detail (cartId, productId, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = DBCPDataSource.preparedStatement(insertQuery)) {
            insertStmt.setInt(1, cartId);
            insertStmt.setInt(2, productId);
            insertStmt.setInt(3, quantity);
            return insertStmt.executeUpdate() > 0;
        }
    }
}
