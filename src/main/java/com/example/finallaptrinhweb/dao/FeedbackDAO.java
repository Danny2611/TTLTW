package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.Feedback;
import com.example.finallaptrinhweb.model.Util;
import java.sql.Timestamp;
import java.sql.ResultSet;
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

    public static List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbackList = new ArrayList<>();

        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, name, email, content, submissionDate FROM comments");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String content = resultSet.getString("content");
                Timestamp submissionDate = resultSet.getTimestamp("submissionDate");

                Feedback feedback = new Feedback(id, email, name, content, submissionDate);
                feedbackList.add(feedback);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

}
