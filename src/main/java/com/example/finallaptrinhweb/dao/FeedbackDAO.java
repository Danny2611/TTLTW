package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.DTO;
import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.*;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.util.List;

public class FeedbackDAO {

    public static boolean addFeedback(Feedback feedback) {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO feedbacks (name, email, content, submissionDate) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, feedback.getName());
            preparedStatement.setString(2, feedback.getEmail());
            preparedStatement.setString(3, feedback.getContent());
            preparedStatement.setTimestamp(4, feedback.getSubmissionDate());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<DTO> getAllFeedbacks() {
        List<DTO> feedbackList = new ArrayList<>();

        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT c.id, c.star, c.content , u.email, p.productName, p.id as productId, c.createdAt FROM comments c join products p on c.productId = p.id   join users u on u.id = c.userId");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int star = resultSet.getInt("star");
                String email = resultSet.getString("email");
                String content = resultSet.getString("content");
                String productName = resultSet.getString("productName");
                int productId =resultSet.getInt("productId");
                LocalDate submissionDate = resultSet.getDate("createdAt").toLocalDate();
                User user = new User();
                user.setEmail(email);

                Product product = new Product();
                product.setId(productId);
                product.setProductName(productName);

                Comment comment = new Comment();
                comment.setId(Long.valueOf(id));
                comment.setStar(star);
                comment.setContent(content);
                comment.setCreatedAt(submissionDate);


                DTO dto = new DTO(user,comment,product);
                feedbackList.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

}
