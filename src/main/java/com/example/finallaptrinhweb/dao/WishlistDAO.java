package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {
    public WishlistDAO(){}

    public List<Integer> getWishListByUserID(int userID){
        List<Integer> result = new ArrayList<>();
        String querry = "SELECT * FROM wishlist WHERE user_id = ?";
        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(querry)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getInt("product_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addToWishlist(int userId, int productId) {
        String query = "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)";

        return false;
    }

    public boolean removeFromWishlist(int userId, int productId) {
        String query = "DELETE FROM wishlist WHERE user_id = ? AND product_id = ?";

        return false;
    }
}
