package com.example.finallaptrinhweb.dao;

import com.cloudinary.Cloudinary;
import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.utill.CloudinaryConfig;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AvataDAO {
    public String getAvatarByUserId(int userId) {
        String query = "SELECT avata_url FROM avata WHERE user_id = ?";
        String avatarUrl = null;

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    avatarUrl = resultSet.getString("avata_url");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avatarUrl;
    }

    public boolean updateAvatar(int userId, String avatarUrl) {
        String query = "INSERT INTO avata (user_id, avata_url) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE avata_url = ?";

        boolean updated = false;

        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, avatarUrl);
            preparedStatement.setString(3, avatarUrl);
            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }


    public static String uploadAvatar(File file) {
        try {
            Cloudinary cloudinary = CloudinaryConfig.getInstance();

            Map<String, Object> options = new HashMap<>();
            options.put("folder", "user_avatars");
            options.put("use_filename", true);
            options.put("unique_filename", true);

            Map uploadResult = cloudinary.uploader().upload(file, options);
            return (String) uploadResult.get("secure_url");
        } catch (Exception e) {
            System.err.println("Lỗi khi upload lên Cloudinary: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
