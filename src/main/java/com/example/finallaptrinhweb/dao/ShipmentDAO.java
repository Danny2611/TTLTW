package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;

public class ShipmentDAO {
    public static int addShipment() {
        int id = getNextId();
        int updated = 0;
        double price = 20000;
        String sql = "INSERT INTO shipping_info (shippingCost, date_created, id) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setBigDecimal(1, BigDecimal.valueOf(price));
            preparedStatement.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setInt(3, id);
            synchronized (preparedStatement) {
                updated = preparedStatement.executeUpdate();
            }

            preparedStatement.close();

            if (updated == 1) {
                return id;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }
    public static int getNextId() {
        int nextId = 0;
        try {
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement("SELECT MAX(id) FROM shipping_info");
            synchronized (preparedStatement) {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    nextId = resultSet.getInt(1) + 1;
                }
                resultSet.close();
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nextId;
    }
}
