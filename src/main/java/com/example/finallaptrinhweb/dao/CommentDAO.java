package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private static Connection connection = null;

    public CommentDAO() {
    }
    public List<Comment> getAllCommentForProduct(int productId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "select c.id, u.username, c.productId, c.star, c.content, c.createdAt  " +
                "FROM comments  c JOIN users u on c.userId = u.id WHERE c.productId = ?";
        try{
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setName(resultSet.getString("username"));
                comment.setProductId(resultSet.getLong("productId"));
                comment.setStar(resultSet.getInt("star"));
                comment.setContent(resultSet.getString("content"));
                comment.setCreatedAt(resultSet.getDate("createdAt").toLocalDate());
                comments.add(comment);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  comments;
    }
    public boolean insertComment(int userId, int productId, int star, String content) {
        String sql = "Insert into comments(userId, productId, star, content) values (?,?,?,?)";
        try{
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2,productId);
            preparedStatement.setInt(3,star);
            preparedStatement.setString(4, content);
            int check = preparedStatement.executeUpdate();
            return check > 0;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }
}
