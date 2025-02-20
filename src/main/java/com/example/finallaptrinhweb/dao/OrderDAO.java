package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.Order;
import com.example.finallaptrinhweb.model.Util;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class OrderDAO {


    public static List<Order> loadOderByUserId(int user_id) {
        List<Order> orderList = new ArrayList<>();
        try {
            String query = "SELECT * FROM `orders` WHERE user_id = ?";

            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
                preparedStatement.setInt(1, user_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = getOrder(resultSet);
                        orderList.add(order);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    private static Order getOrder(ResultSet resultSet) {
        if (resultSet == null)
            return null;
        Order order = new Order();
        try {
            order.setId(resultSet.getInt("id"));
            order.setUserId(resultSet.getInt("user_id"));
            order.setDiscountsId(resultSet.getInt("discounts_id"));
            order.setShipId(resultSet.getInt("ship_id"));
            order.setPayment(resultSet.getInt("payment") == 1);
            order.setPhone(resultSet.getLong("phone"));
            order.setDetailAddress(resultSet.getString("detail_address"));
            order.setStatus(resultSet.getString("status"));
            order.setDateCreated(resultSet.getTimestamp("date_created"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }


    public static Order loadOrder_view(int order_id) {
        Order order = new Order();
        try {
            String query = "SELECT o.id, o.date_created, u.id AS user_id, o.quantity, o.status, o.totalAmount, o.phone, o.detail_address, o.payment, o.date_created AS order_date, o.total_pay, o.ship_price," +
                    "u.username, (SUM(op.price * op.quantity)) AS total " +
                    "FROM orders o " +
                    "JOIN order_products op ON o.id = op.order_id " +
                    "JOIN shipping_info s ON s.id = o.ship_id " +
                    "JOIN users u ON o.user_id = u.id " +
                    "WHERE o.id = ?";

            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
                preparedStatement.setInt(1, order_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        order.setId(resultSet.getInt("id"));
                        order.setUserId(resultSet.getInt("user_id"));
                        order.setDateCreated(Timestamp.valueOf(resultSet.getString("date_created")));
                        order.setStatus(resultSet.getString("status"));
                        order.setTotalPay(resultSet.getDouble("total_pay"));
                        order.setPayment(resultSet.getBoolean("payment"));
                        order.setDetailAddress(resultSet.getString("detail_address"));
                        order.setPhone(resultSet.getLong("phone"));
                        order.setUsername(resultSet.getString("username"));
                        order.setShipPrice(resultSet.getDouble("ship_price"));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }
    public static List<Order> loadOrderNear(int limit) {
        List<Order> orderList = new ArrayList<>();
        try {
            String query = "SELECT o.id, o.date_created, u.id AS user_id, o.quantity, o.status, o.totalAmount, o.phone, o.detail_address, o.payment, o.date_created AS order_date, o.total_pay, o.ship_price," +
                    "o.username, (SUM(op.price * op.quantity)) AS total " +
                    "FROM orders o " +
                    "JOIN order_products op ON o.id = op.order_id " +
                    "JOIN shipping_info s ON s.id = o.ship_id " +
                    "JOIN users u ON o.user_id = u.id " +
                    "GROUP BY o.id, o.date_created, o.username, o.status " +
                    "ORDER BY o.date_created DESC " +
                    "LIMIT ?";

            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
                preparedStatement.setInt(1, limit);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = new Order();
                        order.setId(resultSet.getInt("id"));
                        order.setDateCreated(resultSet.getTimestamp("date_created"));
                        order.setStatus(resultSet.getString("status"));
                        order.setTotalPay(resultSet.getDouble("total_pay"));
                        order.setPayment(resultSet.getBoolean("payment"));
                        order.setDetailAddress(resultSet.getString("detail_address"));
                        order.setPhone(resultSet.getLong("phone"));
                        order.setUsername(resultSet.getString("o.username"));
                        order.setShipPrice(resultSet.getDouble("ship_price"));
                        orderList.add(order);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }


    public static List<String> getAllStatus() {
        List<String> orderList = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT status FROM `orders`";

            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        orderList.add(resultSet.getString(1));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    public static List<Order> loadAllOrder() {
        List<Order> orderList = new ArrayList<>();
        try {
            String query = "SELECT \n" +
                    "    orders.id AS order_id,\n" +
                    "    orders.date_created,\n" +
                    "    orders.username,\n" +
                    "    orders.total_pay,\n" +
                    "    orders.status\n" +
                    "FROM \n" +
                    "    orders\n" +
                    "INNER JOIN \n" +
                    "    order_products ON orders.id = order_products.order_id\n" +
                    "GROUP BY \n" +
                    "    orders.id";

            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = new Order();
                        order.setId(resultSet.getInt("order_id"));
                        order.setDateCreated(resultSet.getTimestamp("date_created"));
                        order.setUsername(resultSet.getString("username"));
                        order.setTotalPay(resultSet.getDouble("total_pay"));
                        order.setStatus(resultSet.getString("status"));
                        orderList.add(order);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    // Bổ sung phương thức để tải danh sách đơn hàng dựa trên trạng thái
    public static List<Order> loadOrderByStatus(String status) {
        List<Order> orderList = new ArrayList<>();
        try {
            String query = "SELECT \n" +
                    "    orders.id AS order_id,\n" +
                    "    orders.date_created,\n" +
                    "    orders.username,\n" +
                    "    orders.total_pay,\n" +
                    "    orders.status\n" +
                    "FROM \n" +
                    "    orders\n" +
                    "INNER JOIN \n" +
                    "    order_products ON orders.id = order_products.order_id\n" +
                    "WHERE \n" +
                    "    orders.status = ?\n" +
                    "GROUP BY \n" +
                    "    orders.id;\n";

            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
                preparedStatement.setString(1, status);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = new Order();
                        order.setId(resultSet.getInt("order_id"));
                        order.setDateCreated(resultSet.getTimestamp("date_created"));
                        order.setUsername(resultSet.getString("username"));
                        order.setTotalPay(resultSet.getDouble("total_pay"));
                        order.setStatus(resultSet.getString("status"));
                        orderList.add(order);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }


    public static List<Order> loadOrderStatusByIdUser(int idUser) {
        List<Order> orderList = new ArrayList<>();
        try {
            String query = "SELECT p.id, p.productName, o.`status`, (SUM(p.price * op.quantity)) AS total, o.date_created " +
                    "FROM product p " +
                    "JOIN order_product op ON p.id = op.pro_id " +
                    "JOIN `order` o ON o.id = op.order_id " +
                    "JOIN `user` u ON u.id = o.user_id " +
                    "WHERE u.id = ? " +
                    "GROUP BY p.id, p.name, o.`status`, o.date_created";

            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
                preparedStatement.setInt(1, idUser);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = new Order();
                        order.setId(resultSet.getInt("id"));
                        order.setStatus(resultSet.getString("status"));
                        order.setTotalPay(resultSet.getDouble("total"));
                        order.setDateCreated(resultSet.getTimestamp("date_created"));
                        orderList.add(order);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }
    public static void main(String[] args) {
        System.out.println(loadOrderNear(1));
    }

    public static List<Order> loadOrderByUserId(int user_id) {
        List<Order> orderList = new ArrayList<>();
        try {
            String query = "SELECT o.id, o.date_created, u.username, o.status, " +
                    "(SUM(op.price * op.quantity) + s.ship_price) AS total, COUNT(o.id) AS countOr " +
                    "FROM orders o " +
                    "JOIN order_products op ON o.id = op.order_id " +
                    "JOIN shipping_info s ON s.id = o.ship_id " +
                    "JOIN users u ON o.user_id = u.id " +
                    "WHERE o.user_id = ? " +
                    "GROUP BY o.id, o.date_created, o.user_id, o.status";

            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
                preparedStatement.setInt(1, user_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = new Order();
                        order.setId(resultSet.getInt("id"));
                        order.setDateCreated(resultSet.getTimestamp("date_created"));
                        order.setUsername(resultSet.getString("username"));
                        order.setStatus(resultSet.getString("status"));
                        order.setTotalPay(resultSet.getDouble("total"));
                        orderList.add(order);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    public static boolean updateStatusById(int order_id, String status) {
        try {
            String query = "UPDATE `orders` SET `status` = ? WHERE id=?";
            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query)) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, order_id);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }



    public static int addOrder(String username, int user_id, Integer discounts_id, int ship_id, int quantity, String status,
                               double totalAmount, int phone, String detail_address, int payment, Timestamp date_created,
                               double total_pay, double ship_price) {

        int updated = 0;
        String sql = "INSERT INTO `orders`(`id`, `username`, `user_id`, `discounts_id`, `ship_id`, `quantity`, `status`, `totalAmount`, `phone`, `detail_address`, `payment`, `date_created`, `total_pay`, `ship_price`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);

            preparedStatement.setInt(1, getNextOrderId());
            preparedStatement.setString(2, username);
            preparedStatement.setInt(3, user_id);
            preparedStatement.setInt(4, discounts_id);
            preparedStatement.setInt(5, ship_id);
            preparedStatement.setInt(6, quantity);
            preparedStatement.setString(7, status);
            preparedStatement.setDouble(8, totalAmount);
            preparedStatement.setInt(9, phone);
            preparedStatement.setString(10, detail_address);
            preparedStatement.setInt(11, payment);
            preparedStatement.setTimestamp(12, date_created);
            preparedStatement.setDouble(13, total_pay);
            preparedStatement.setDouble(14, 20000);

            updated = preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updated;
    }

    public static int addOrderProduct(int discountsId, String productName, String imageUrl,
                                      int quantity, double price) {
        int updated = 0;
        String sql = "INSERT INTO `order_products`(`id`,`order_id`, `discounts_id`, `productName`, `imageUrl`, `quantity`, `price`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);

            preparedStatement.setInt(1, getNextOrderProId());
            preparedStatement.setInt(2, getNextOrderId() - 1);
            preparedStatement.setInt(3, discountsId);
            preparedStatement.setString(4, productName);
            preparedStatement.setString(5, imageUrl);
            preparedStatement.setInt(6, quantity);
            preparedStatement.setDouble(7, price);

            updated = preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updated;
    }

    private static int getNextOrderProId() {
        int result = 0;
        try {
            String query = "SELECT MAX(id) FROM `order_products`";
            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    result = resultSet.getInt(1) + 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public static int getNextOrderId() {
        int result = 0;
        try {
            String query = "SELECT MAX(id) FROM `orders`";
            try (PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    result = resultSet.getInt(1) + 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

}
