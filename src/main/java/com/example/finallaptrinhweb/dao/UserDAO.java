package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.db.JDBIConnector;
import com.example.finallaptrinhweb.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    private static UserDAO instance;

    public UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }

        return instance;
    }

    public User CheckLogin(String identifier, String password) throws SQLException {
        // Kiểm tra identifier có phải là email hay không (chứa @)
        if (identifier.contains("@")) {
            return CheckLoginByEmail(identifier, password);
        } else {
            return CheckLoginByPhone(identifier, password);
        }
    }
    private User CheckLoginByEmail(String email, String password) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE email = ?")
                    .bind(0, email)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        if (users.size() != 1) {
            return null;
        } else {
            User user = (User) users.get(0);
            String hashedPasswordFromDatabase = user.getPassword();
            return email.equals(user.getEmail()) && BCrypt.checkpw(password, hashedPasswordFromDatabase) ? user : null;
        }
    }

    private User CheckLoginByPhone(String phone, String password) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE phone = ?")
                    .bind(0, phone)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        if (users.size() != 1) {
            return null;
        } else {
            User user = (User) users.get(0);
            String hashedPasswordFromDatabase = user.getPassword();
            return phone.equals(user.getPhone()) && BCrypt.checkpw(password, hashedPasswordFromDatabase) ? user : null;
        }
    }

    public boolean CheckExistUser(String identifier) throws SQLException {
        // Check if the identifier is an email (contains @) or a phone number
        if (identifier.contains("@")) {
            return CheckExistEmail(identifier);
        } else {
            return CheckExistPhone(identifier);
        }
    }

    public boolean CheckExistEmail(String email) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE email = ?")
                    .bind(0, email)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return !users.isEmpty();
    }

    public boolean CheckExistPhone(String phone) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE phone = ?")
                    .bind(0, phone)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return !users.isEmpty();
    }

    public boolean CheckVerifiedStatus(String identifier) throws SQLException {
        // Check if the identifier is an email (contains @) or a phone number
        if (identifier.contains("@")) {
            return CheckEmailVerifiedStatus(identifier);
        } else {
            return CheckPhoneVerifiedStatus(identifier);
        }
    }

    public boolean CheckEmailVerifiedStatus(String email) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE email = ? && verify_status = ?")
                    .bind(0, email)
                    .bind(1, "verified")
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return !users.isEmpty();
    }

    public boolean CheckPhoneVerifiedStatus(String phone) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE phone = ? && verify_status = ?")
                    .bind(0, phone)
                    .bind(1, "verified")
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return !users.isEmpty();
    }

    public void UpdateFirebaseUid(String phone, String firebaseUid) throws SQLException {
        JDBIConnector.me().get().useHandle((handle) -> {
            handle.createUpdate("UPDATE users SET firebase_uid = ?, verify_status = 'verified' WHERE phone = ?")
                    .bind(0, firebaseUid)
                    .bind(1, phone)
                    .execute();
        });
    }

    public User GetInforById(int id) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE id = ?")
                    .bind(0, id)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return users.isEmpty() ? null : users.get(0);
    }

    public int GetId() throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE id = (SELECT MAX(id) FROM users)")
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return users.get(0).getId();
    }

    public User GetInforByIdentifier(String identifier) throws SQLException {
        // Kiểm tra identifier có phải là email hay không (chứa @)
        if (identifier.contains("@")) {
            return GetInforByEmail(identifier);
        } else {
            return GetInforByPhone(identifier);
        }
    }

    public User GetInforByEmail(String email) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE email = ?")
                    .bind(0, email)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return users.isEmpty() ? null : users.get(0);
    }

    public User GetInforByPhone(String phone) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE phone = ?")
                    .bind(0, phone)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return users.isEmpty() ? null : users.get(0);
    }

    public void SignUp(String username, String email, String password, String verifyStatus, int roleId) throws SQLException {
        SignUp(username, email, null, password, verifyStatus, roleId, null);
    }

    public void SignUpWithPhone(String username, String phone, String password, String verifyStatus,String firebaseUid, int roleId) throws SQLException {
        SignUp(username, null, phone, password, verifyStatus, roleId, firebaseUid);
    }

    public void SignUp(String username, String email, String phone, String password, String verifyStatus, int roleId, String firebase_uid) throws SQLException {
        Date dateCreated = new Date();
        // Chỉ hash password nếu nó được cung cấp (trường hợp đăng ký thông thường)
        final String hashedPassword = (password != null && !password.isEmpty())
                ? BCrypt.hashpw(password, BCrypt.gensalt())
                : null;

        JDBIConnector.me().get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO users (id, username, email, phone, password, verify_status, firebase_uid, date_created, role_id) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?)")
                    .bind(0, this.GetId() + 1)
                    .bind(1, username)
                    .bind(2, email)
                    .bind(3, phone)
                    .bind(4, hashedPassword)
                    .bind(5, verifyStatus)
                    .bind(6, firebase_uid)
                    .bind(7, dateCreated)
                    .bind(8, roleId)
                    .execute();
        });
    }

    public boolean checkPassword(String candidatePassword, String hashedPasswordFromDatabase) {
        return BCrypt.checkpw(candidatePassword, hashedPasswordFromDatabase);
    }

    public void SetVerifiedStatus(String authcode) throws SQLException {
        JDBIConnector.me().get().useHandle((handle) -> {
            handle.createUpdate("UPDATE users SET verify_status = 'verified' WHERE verify_status = ?")
                    .bind(0, authcode)
                    .execute();
        });
    }

    public void SetVerifiedStatusByPhone(String phone) throws SQLException {
        JDBIConnector.me().get().useHandle((handle) -> {
            handle.createUpdate("UPDATE users SET verify_status = 'verified' WHERE phone = ?")
                    .bind(0, phone)
                    .execute();
        });
    }


    public String getPassword(String identifier) throws SQLException {
        // Kiểm tra identifier có phải là email hay không (chứa @)
        if (identifier.contains("@")) {
            return getPasswordByEmail(identifier);
        } else {
            return getPasswordByPhone(identifier);
        }
    }

    public String getPasswordByEmail(String email) throws SQLException {
        List<String> passwords = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT password FROM users WHERE email = ?")
                    .bind(0, email)
                    .mapTo(String.class)
                    .collect(Collectors.toList());
        });

        if (!passwords.isEmpty()) {
            return passwords.get(0);
        } else {
            return null; // Hoặc giá trị mặc định khác tùy thuộc vào logic của bạn
        }
    }

    public String getPasswordById(int id) throws SQLException {
        List<String> passwords = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT password FROM users WHERE id = ?")
                    .bind(0, id)
                    .mapTo(String.class)
                    .collect(Collectors.toList());
        });

        if (!passwords.isEmpty()) {
            return passwords.get(0);
        } else {
            return null; // hoặc trả giá trị mặc định nếu cần
        }
    }


    public String getPasswordByPhone(String phone) throws SQLException {
        List<String> passwords = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT password FROM users WHERE phone = ?")
                    .bind(0, phone)
                    .mapTo(String.class)
                    .collect(Collectors.toList());
        });

        if (!passwords.isEmpty()) {
            return passwords.get(0);
        } else {
            return null; // Hoặc giá trị mặc định khác tùy thuộc vào logic của bạn
        }
    }

    public void updateUserInfor(int id, String fullName, String birthday, String city,
                                String district, String ward, String address, String phone, String email) {
        try {
            JDBIConnector.me().get().withHandle(handle -> {
                return handle.createUpdate("UPDATE users SET fullName = ?, dateOfBirth = ?, city = ?, district = ?, ward = ?, detail_address = ?, phone = ?, email = ? WHERE id = ?")
                        .bind(0, fullName)
                        .bind(1, birthday)
                        .bind(2, city)
                        .bind(3, district)
                        .bind(4, ward)
                        .bind(5, address)
                        .bind(6, phone)
                        .bind(7, email)
                        .bind(8, id)
                        .execute();
            });
        } catch (SQLException e) {
            // In ra lỗi chi tiết SQL
            System.err.println("Lỗi khi cập nhật user thông tin:");
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("ErrorCode: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            // Có thể ném lại hoặc xử lý tùy theo logic bạn muốn
        } catch (Exception e) {
            // Bắt các lỗi khác nếu có
            System.err.println("Lỗi không xác định khi cập nhật user thông tin:");
            e.printStackTrace();
        }
    }



    public void updatePassword(String email, String password) throws SQLException {
        // Mã hóa mật khẩu trước khi cập nhật vào cơ sở dữ liệu
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        JDBIConnector.me().get().useHandle((handle) -> {
            handle.createUpdate("UPDATE users SET password = ? WHERE email = ?")
                    .bind(0, hashedPassword)
                    .bind(1, email)
                    .execute();
        });
    }

    // Trong UserDAO

    public void resetPassword(String identifier, String hashedPassword) throws SQLException {
        try {
            // Kiểm tra identifier có phải là email hay không (chứa @)
            if (identifier.contains("@")) {
                resetPasswordByEmail(identifier, hashedPassword);
            } else {
                resetPasswordByPhone(identifier, hashedPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetPasswordByEmail(String email, String hashedPassword) throws SQLException {
        JDBIConnector.me().get().useHandle((handle) -> {
            handle.createUpdate("UPDATE users SET password = ? WHERE email = ?")
                    .bind(0, hashedPassword)
                    .bind(1, email)
                    .execute();
        });
    }

    public void resetPasswordByPhone(String phone, String hashedPassword) throws SQLException {
        JDBIConnector.me().get().useHandle((handle) -> {
            handle.createUpdate("UPDATE users SET password = ? WHERE email = ?")
                    .bind(0, hashedPassword)
                    .bind(1, phone)
                    .execute();
        });
    }

    public User CheckLoginAdmin(String username, String password) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE username = ? AND role_id = ?")
                    .bind(0, username)
                    .bind(1, 2) // 2 là roleId của admin (hoặc giá trị mà bạn gán cho admin)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });

        if (users.size() != 1) {
            return null;
        } else {
            User user = users.get(0);
            String hashedPasswordFromDatabase = user.getPassword();
            return username.equals(user.getUsername()) && BCrypt.checkpw(password, hashedPasswordFromDatabase) ? user : null;
        }
    }

    public void addAdmin(String username, String email, String password, int roleId, String phone) throws SQLException {
        if (CheckExistUser(email)) {

            System.out.println("Email đã tồn tại trong hệ thống.");
        } else {
            if (username != null) {
                Date dateCreated = new Date();

                JDBIConnector.me().get().withHandle((handle) -> {
                    return handle.createUpdate("INSERT INTO users (id, username, email, password, verify_status,date_created, role_id,remaining, fullName,phone) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)")
                            .bind(0, this.GetId() + 1)
                            .bind(1, username)
                            .bind(2, email)
                            .bind(3, password)
                            .bind(4, "verified")
                            .bind(5, dateCreated)
                            .bind(6, roleId)
                            .bind(7, 10)
                            .bind(8, username)
                            .bind(9,phone)
                            .execute();
                });
                System.out.println("Admin đã được thêm vào cơ sở dữ liệu.");
            } else {
                System.out.println("Tên người dùng không hợp lệ.");
            }
        }
    }


    public  boolean changePermission(int roleId, int userId){
        try{
            String sql = "update users  set role_id = ? where id = ?";
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setInt(1, roleId);
            preparedStatement.setInt(2, userId);
            int check  =  preparedStatement.executeUpdate();
            return check > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }


    // remaining
    public int getRemaining(String identifier) {
        try {
            // Kiểm tra identifier có phải là email hay không (chứa @)
            if (identifier.contains("@")) {
                return getRemainingByEmail(identifier);
            } else {
                return getRemainingByPhone(identifier);
            }
        } catch (Exception e) {
            return 5; // Giá trị mặc định
        }
    }
    public int getRemainingByEmail(String email) {
        int remaining= 0 ;
        try{
            String sql = "select remaining from users where email = ?";
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                remaining= resultSet.getInt("remaining");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  remaining;
    }
    public int getRemainingByPhone(String phone) {
        int remaining= 0 ;
        try{
            String sql = "select remaining from users where phone = ?";
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                remaining= resultSet.getInt("remaining");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  remaining;
    }
    public void updateRemaining( String email, int remaining) {
        try{
            String sql = "update users set remaining = ? where email = ?";
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setInt(1, remaining);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void resetRemain(int userId){
        try{
            String sql = "update users set remaining = 10 where id = ?";
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserIdByEmail(String email){
        int id= 0 ;
        try{
            String sql = "select id from users where email = ?";
            PreparedStatement preparedStatement = DBCPDataSource.preparedStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id= resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  id;
    }

    //islock
    public boolean isLocked(String identifier) {
        try {
            // Kiểm tra identifier có phải là email hay không (chứa @)
            if (identifier.contains("@")) {
                return isLockedByEmail(identifier);
            } else {
                return isLockedByPhone(identifier);
            }
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isLockedByEmail(String email) {
        String query = "SELECT locked_until FROM users WHERE email = ?";
        try (PreparedStatement ps = DBCPDataSource.preparedStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Timestamp lockedUntil = rs.getTimestamp("locked_until");
                if (lockedUntil != null && lockedUntil.after(new Timestamp(System.currentTimeMillis()))) {
                    return true; // Tài khoản vẫn đang bị khóa
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isLockedByPhone(String phone) {
        String query = "SELECT locked_until FROM users WHERE phone = ?";
        try (PreparedStatement ps = DBCPDataSource.preparedStatement(query)) {
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Timestamp lockedUntil = rs.getTimestamp("locked_until");
                if (lockedUntil != null && lockedUntil.after(new Timestamp(System.currentTimeMillis()))) {
                    return true; // Tài khoản vẫn đang bị khóa
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //lockAccount
    public void lockAccount(String identifier) {
        try {
            // Kiểm tra identifier có phải là email hay không (chứa @)
            if (identifier.contains("@")) {
                lockAccountByEmail(identifier);
            } else {
                lockAccountByPhone(identifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lockAccountByEmail(String email) {
        String query = "UPDATE users SET locked_until = NOW() + INTERVAL 5 MINUTE WHERE email = ?";
        try (PreparedStatement ps = DBCPDataSource.preparedStatement(query)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lockAccountByPhone(String phone) {
        String query = "UPDATE users SET locked_until = NOW() + INTERVAL 5 MINUTE WHERE phone = ?";
        try (PreparedStatement ps = DBCPDataSource.preparedStatement(query)) {
            ps.setString(1, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void resetAttempts(String identifier) {
        if (identifier.contains("@")) {
            resetAttemptsByEmail(identifier);
        } else {
            resetAttemptsByPhone(identifier);
        }
    }

    public void resetAttemptsByEmail(String email) {
        String query = "UPDATE users SET remaining = 10, locked_until = NULL WHERE email = ?";
        try (PreparedStatement ps = DBCPDataSource.preparedStatement(query)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetAttemptsByPhone(String phone) {
        String query = "UPDATE users SET remaining = 10, locked_until = NULL WHERE phone = ?";
        try (PreparedStatement ps = DBCPDataSource.preparedStatement(query)) {
            ps.setString(1, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void resetAttempts(String email) {
//        String query = "UPDATE users SET remaining = 10, locked_until = NULL WHERE email = ?";
//        try (
//             PreparedStatement ps = DBCPDataSource.preparedStatement(query)) {
//            ps.setString(1, email);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public long getLockTime(String email) {
        long lockTime = 0;
        String sql = "SELECT locked_until FROM users WHERE email = ?";
        try (
                PreparedStatement ps = DBCPDataSource.preparedStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lockTime = rs.getTimestamp("locked_until").getTime();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lockTime;
    }


}