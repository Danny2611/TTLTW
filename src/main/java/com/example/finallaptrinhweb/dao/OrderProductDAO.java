package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.OrderProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAO {

    public static List<OrderProduct> loadOrderProductByOrderId(int orderId) {
        List<OrderProduct> productList = new ArrayList<>();
        try {
            String query = "SELECT op.id, op.productName, op.quantity, op.price, o.id, o.date_created, u.id, o.status, (SUM(op.price * op.quantity) + s.ship_price) AS total, " +
                    "o.payment, o.detail_address, o.phone, u.username, s.ship_price, op.imageUrl " +
                    "FROM orders o " +
                    "JOIN order_products op ON o.id = op.order_id " +
                    "JOIN shipping_info s ON s.id = o.ship_id " +
                    "JOIN users u ON u.id = o.user_id " +
                    "WHERE o.id = ? " +
                    "GROUP BY o.id, o.date_created, u.id, o.status, o.payment, " +
                    "o.detail_address, o.phone, u.username, s.ship_price, op.imageUrl;";

            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query);
            preparedStatement.setInt(1, orderId);

            synchronized (preparedStatement) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setId(resultSet.getInt("id"));
                    orderProduct.setProductName(resultSet.getString("productName"));
                    orderProduct.setQuantity(resultSet.getInt("quantity"));
                    orderProduct.setPrice(resultSet.getDouble("price"));
                    orderProduct.setImageUrl(resultSet.getString("imageUrl"));
                    orderProduct.setTotal(resultSet.getDouble("price"), resultSet.getInt("quantity"));
                    productList.add(orderProduct);
                }
                resultSet.close();
            }
            preparedStatement.close();
            return productList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }
    public static void main(String[] args) {
        System.out.println(loadOrderProductByOrderId(1));
    }

    public static int addOrderProduct(int orderId, int productId, int quantity, double price, double totalPrice) {
        int updated = 0;
        String sql = "INSERT INTO order_products (order_id, pro_id, quantity, price, status, total_price) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);
            preparedStatement.setDouble(5, totalPrice);
            synchronized (preparedStatement) {
                updated = preparedStatement.executeUpdate();
            }

            preparedStatement.close();
            return updated;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updated;
    }
}
