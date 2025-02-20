package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.SocialMedia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SocialMediaDao {

    public static boolean update(String facebook, String instagram, String twitter, String pinterest, String youtube, String linkedin) {
        int updated = 0;
        String sql = "UPDATE social_media SET facebook=?, instagram=?, twitter=?, pinterest=?, youtube=?, linkedin=? WHERE id=1";
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, facebook);
            preparedStatement.setString(2, instagram);
            preparedStatement.setString(3, twitter);
            preparedStatement.setString(4, pinterest);
            preparedStatement.setString(5, youtube);
            preparedStatement.setString(6, linkedin);

            updated = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updated == 1;
    }

    public static SocialMedia load() {
        SocialMedia socialMedia = new SocialMedia();
        String sql = "SELECT * FROM social_media";
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                socialMedia.setFacebook(resultSet.getString("facebook"));
                socialMedia.setInstagram(resultSet.getString("instagram"));
                socialMedia.setTwitter(resultSet.getString("twitter"));
                socialMedia.setPinterest(resultSet.getString("pinterest"));
                socialMedia.setYoutube(resultSet.getString("youtube"));
                socialMedia.setLinkedin(resultSet.getString("linkedin"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return socialMedia;
    }

    public static void main(String[] args) {
        // Cập nhật thông tin social media
        boolean isUpdated = update("newFacebook", "newInstagram", "newTwitter", "newPinterest", "newYoutube", "newLinkedin");

        if (isUpdated) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }

        // Load thông tin social media sau khi cập nhật
        SocialMedia socialMedia = load();

        // In ra thông tin social media
        System.out.println("Thông tin social media sau cập nhật:");
        System.out.println("Facebook: " + socialMedia.getFacebook());
        System.out.println("Instagram: " + socialMedia.getInstagram());
        System.out.println("Twitter: " + socialMedia.getTwitter());
        System.out.println("Pinterest: " + socialMedia.getPinterest());
        System.out.println("Youtube: " + socialMedia.getYoutube());
        System.out.println("Linkedin: " + socialMedia.getLinkedin());
    }

}
