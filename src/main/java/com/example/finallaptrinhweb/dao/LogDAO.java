package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDAO {
    public void saveLog(int userId, String location, String oldData, String newData, String level) {
        String sql = "INSERT INTO logs (user_id, action_location, old_data, new_data, level) VALUES (?, ?, ?, ?, ?)";

        try (
                PreparedStatement pstmt = DBCPDataSource.preparedStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, location);
            pstmt.setString(3, oldData);
            pstmt.setString(4, newData);
            pstmt.setString(5, level);
            int check = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
