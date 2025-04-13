package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.db.JDBIConnector;
import com.example.finallaptrinhweb.model.CouponCode;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CouponCodeDAO {
    private static CouponCodeDAO instance;

    public CouponCodeDAO() {
    }

    public static CouponCodeDAO getInstance() {
        if (instance == null) {
            instance = new CouponCodeDAO();
        }

        return instance;
    }

    public List<CouponCode> getAllCoupon() {
        try {
            List<CouponCode> coupons = JDBIConnector.me().get().withHandle((handle) -> {
                return handle.createQuery("SELECT * FROM discounts")
                        .mapToBean(CouponCode.class)
                        .collect(Collectors.toList());
            });
            return coupons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CouponCode getCouponById(int id) {
        try {
            CouponCode coupons = (CouponCode) JDBIConnector.me().get().withHandle((handle) -> {
                return handle.createQuery("SELECT * FROM discounts WHERE id =?")
                        .bind(0, id)
                        .mapToBean(CouponCode.class)
                        .findFirst()
                        .orElse(null);
            });
            return coupons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CouponCode getCouponByName(String name) {
        try {
            CouponCode coupons = (CouponCode) JDBIConnector.me().get().withHandle((handle) -> {
                return handle.createQuery("SELECT * FROM discounts WHERE discountType =? and isUse = ?")
                        .bind(0, name).bind(1,1)
                        .mapToBean(CouponCode.class)
                        .findFirst()
                        .orElse(null);
            });
            return coupons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCoupon(String name, double percent, String dateEnd) {
        try {
            JDBIConnector.me().get().withHandle((handle) -> {
                return handle.createUpdate("INSERT INTO `discounts`(`id`, `discountType`, `discountValue`, `expirationDate`) VALUES (?,?,?,?)")
                        .bind(0, getNextId())
                        .bind(1, name)
                        .bind(2, percent)
                        .bind(3, dateEnd)
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delCoupon(int id) {
        try {
            JDBIConnector.me().get().withHandle((handle) -> {
                return handle.createUpdate("DELETE FROM `discounts` WHERE id = ?")
                        .bind(0, id)
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editCoupon(int id,String name, double percent, String dateEnd) {
        try {
            JDBIConnector.me().get().useHandle((handle) -> {
                handle.createUpdate("UPDATE `discounts` SET `discountType`=?, `discountValue`=?, `expirationDate`=? WHERE id = ?")
                        .bind(0, name)
                        .bind(1, percent)
                        .bind(2, dateEnd)
                        .bind(3, id)
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private int getNextId() {
        try {
            Integer id = JDBIConnector.me().get().withHandle((handle) -> {
                return handle.createQuery("SELECT MAX(id) FROM discounts")
                        .mapTo(Integer.class)
                        .one();
            });
            return id + 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  boolean setUseCouponIsUse(int id) throws SQLException {

        String sql = "update  discounts set isUse = 1 where id =?";
        PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
        preparedStatement.setInt(1,id);
        return  preparedStatement.executeUpdate() >0;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(new CouponCodeDAO().getCouponById(1));
    }

}
