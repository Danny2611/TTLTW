package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.Address;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadfooterDao {

    public static List<Address> loadAddresses() {
        List<Address> adds = new ArrayList<>();

        // Đoạn mã lấy dữ liệu từ CSDL
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM address");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String address = resultSet.getString("address");
                long phone = resultSet.getLong("phone");
                long hotline = resultSet.getLong("hotline");
                String email = resultSet.getString("email");
                String time = resultSet.getString("timeOpen");
                String map = resultSet.getString("map");
                adds.add(new Address(address, phone, hotline, email, time, map));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace(); // Xử lý lỗi nếu cần
        }

        return adds;
    }

    public static boolean updateAddress(String address, String phone, String hotline, String email, String timeOpen, String mapAddress) {
        int updated = 0;
        String sql = "UPDATE address SET address=?, phone=?, hotline=?, email=?, timeOpen=?, map=? WHERE id=1";

        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, hotline);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, timeOpen);
            preparedStatement.setString(6, mapAddress);

            synchronized (preparedStatement) {
                updated = preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return updated == 1;
    }


    public static void main(String[] args) {
        // Gọi hàm để lấy thông tin địa chỉ
        List<Address> addresses = loadAddresses();

        // In ra thông tin địa chỉ
        for (Address address : addresses) {
            System.out.println("Address: " + address.getAddress());
            System.out.println("Phone: " + address.getPhone());
            System.out.println("Hotline: " + address.getHotline());
            System.out.println("Email: " + address.getEmail());
            System.out.println("Time Open: " + address.getTimeOpen());
            System.out.println("Map: " + address.getMap());
            System.out.println("------------------------");
        }
    }
}
