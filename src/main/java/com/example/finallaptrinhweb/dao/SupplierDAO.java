package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.Supplier;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public static List<Supplier> loadSupplierList() {
        List<Supplier> supplierList = new ArrayList<>();
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM suppliers")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(resultSet.getInt("id"));
                supplier.setSupplierName(resultSet.getString("supplierName"));
                supplier.setContactName(resultSet.getString("contactName"));
                supplier.setEmail(resultSet.getString("email"));
                supplier.setPhone(resultSet.getString("phone"));
                supplier.setDetailAddress(resultSet.getString("detail_address"));
                supplier.setImageUrl(resultSet.getString("imageUrl"));
                supplier.setProductId(resultSet.getInt("product_id"));
                supplierList.add(supplier);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return supplierList;
    }

    public static boolean insertSupplier(String name, String address, String phone, String email, String logoUrl) {
        String sql = "INSERT INTO suppliers (supplierName,detail_address,phone,email, imageUrl) VALUES (?, ?, ?, ?, ?)";
        int update = 0;
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, logoUrl);

            update = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update == 1;
    }

    public static boolean updateSupplier(int id, String name, String email, String phone, String detailAddress, String logoUrl) {
        String sql = "UPDATE suppliers SET supplierName=?, detail_address=?, phone=?, email=?, imageUrl=? WHERE id=?";
        int update = 0;
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, detailAddress);
            preparedStatement.setString(5, logoUrl);
            preparedStatement.setInt(6, id);

            update = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update == 1;
    }

    public static Supplier loadSupplier(int id) {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM suppliers WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getSupplier(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Supplier getSupplier(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        Supplier supplier = new Supplier();
        try {
            supplier.setId(resultSet.getInt("id"));
            supplier.setSupplierName(resultSet.getString("supplierName"));
            supplier.setContactName(resultSet.getString("contactName"));
            supplier.setEmail(resultSet.getString("email"));
            supplier.setPhone(resultSet.getString("phone"));
            supplier.setDetailAddress(resultSet.getString("detail_address"));
            supplier.setImageUrl(resultSet.getString("imageUrl"));
            supplier.setProductId(resultSet.getInt("product_id"));
            return supplier;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static boolean deleteSupplier(int id) {
        String sql = "DELETE FROM suppliers WHERE id=?";
        int update = 0;
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            update = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update == 1;
    }

    public Supplier getSupplierById(int id) {
        String query = "SELECT * FROM suppliers WHERE id=?";
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getSupplier(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int sumOfSupplier(String sql) {
        int sum = 0;
        try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    sum = rs.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sum;
    }


    public static void main(String[] args) {
        // Test loadSupplierList
        System.out.println("Load Supplier List:");
        SupplierDAO.loadSupplierList().forEach(System.out::println);


    }
}

