package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.db.JDBIConnector;
import com.example.finallaptrinhweb.model.CategoryQuantity;
import com.example.finallaptrinhweb.model.ProductCategories;
import com.example.finallaptrinhweb.model.ProductGroups;
import com.example.finallaptrinhweb.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDao {
    private static CategoryDao instance;

    public CategoryDao() {
    }

    public static CategoryDao getInstance() {
        if (instance == null) {
            instance = new CategoryDao();
        }

        return instance;
    }

    public List<ProductGroups> getGroupCategory() {
        try {
            return JDBIConnector.me().get().withHandle(handle -> {
                return handle.createQuery("SELECT * FROM product_groups")
                        .mapToBean(ProductGroups.class)
                        .collect(Collectors.toList());
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductCategories> getCategory() {
        try {
            return JDBIConnector.me().get().withHandle(handle -> {
                return handle.createQuery("SELECT * FROM product_categories")
                        .mapToBean(ProductCategories.class)
                        .collect(Collectors.toList());
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CategoryQuantity> getQuantityCategory() {
        try {
            return JDBIConnector.me().get().withHandle(handle -> {
                return handle.createQuery("SELECT pc.id AS categoryId, COALESCE(SUM(p.quantity), 0) AS totalQuantity\n" +
                                "FROM product_categories pc\n" +
                                "LEFT JOIN products p ON pc.id = p.category_id\n" +
                                "GROUP BY pc.id, pc.categoryName")
                        .mapToBean(CategoryQuantity.class)
                        .collect(Collectors.toList());
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void setStatus(int status, int id) {
        try {
            JDBIConnector.me().get().useHandle((handle) -> {
                handle.createUpdate("UPDATE product_categories SET active = ? WHERE id = ?")
                        .bind(0, status)
                        .bind(1, id)
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editCategory(int id, String categoryName) {
        try {
            JDBIConnector.me().get().useHandle((handle) -> {
                handle.createUpdate("UPDATE product_categories SET categoryName = ? WHERE id = ?")
                        .bind(0, categoryName)
                        .bind(1, id)
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategory(int id, String name) {
        try {
            JDBIConnector.me().get().useHandle((handle) -> {
                handle.createUpdate("INSERT INTO `product_categories`(`id`, `categoryName`, `group_id`, `active`) VALUES (?,?,?,?)")
                        .bind(0, getNextIdCategory())
                        .bind(1, name)
                        .bind(2, id)
                        .bind(3, 0)
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getNextIdCategory() throws SQLException {
        List<ProductCategories> list = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM product_categories WHERE id = (SELECT MAX(id) FROM product_categories)")
                    .mapToBean(ProductCategories.class)
                    .collect(Collectors.toList());
        });
        return list.get(0).getId() + 1;
    }
    public int getCategory(String cateName) throws SQLException {
        int id = 0;
        String sql= "select id from product_categories where categoryName like ?";
        PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
        preparedStatement.setString(1, cateName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            id = resultSet.getInt("id");
        }
        return  id;
    }


    public static void main(String[] args) throws SQLException {
        System.out.println(new CategoryDao().getNextIdCategory());
    }
}
