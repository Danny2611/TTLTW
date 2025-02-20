package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.db.JDBIConnector;
import com.example.finallaptrinhweb.model.User;

import java.sql.SQLException;
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

    public User CheckLogin(String email, String password) throws SQLException {
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

    public boolean CheckExistUser(String email) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE email = ?")
                    .bind(0, email)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return !users.isEmpty();
    }

    public boolean CheckVerifiedStatus(String email) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE email = ? && verify_status = ?")
                    .bind(0, email)
                    .bind(1, "verified")
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return !users.isEmpty();
    }

    public int GetId() throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE id = (SELECT MAX(id) FROM users)")
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return users.get(0).getId();
    }

    public User GetInfor(String email) throws SQLException {
        List<User> users = JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createQuery("SELECT * FROM users WHERE email = ?")
                    .bind(0, email)
                    .mapToBean(User.class)
                    .collect(Collectors.toList());
        });
        return users.get(0);
    }

    public void SignUp(String username, String email, String password, String code, int roleId) throws SQLException {
        Date dateCreated = new Date();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        JDBIConnector.me().get().withHandle((handle) -> {
            return handle.createUpdate("INSERT INTO users (id, username, email, password, verify_status, date_created, role_id) VALUES (?, ?, ?, ?, ?, ?, ?)")
                    .bind(0, this.GetId() + 1)
                    .bind(1, username)
                    .bind(2, email)
                    .bind(3, hashedPassword)
                    .bind(4, code)
                    .bind(5, dateCreated)
                    .bind(6, roleId)
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

    public String getPassword(String email) throws SQLException {
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


    public void updateUserInfor(String email, String fullName, String birthday, String city, String district, String ward, String detail_address, String phone) throws SQLException {
        JDBIConnector.me().get().useHandle((handle) -> {
            handle.createUpdate("UPDATE users SET fullName = ?, dateOfBirth = DATE(?), city = ?, district = ?, ward = ?, detail_address = ?, phone = ? WHERE email = ?")
                    .bind(0, fullName)
                    .bind(1, birthday)
                    .bind(2, city)
                    .bind(3, district)
                    .bind(4, ward)
                    .bind(5, detail_address)
                    .bind(6, phone)
                    .bind(7, email)
                    .execute();
        });
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
    public void resetPassword(String email, String hashedPassword) throws SQLException {
        JDBIConnector.me().get().useHandle((handle) -> {
            handle.createUpdate("UPDATE users SET password = ? WHERE email = ?")
                    .bind(0, hashedPassword)
                    .bind(1, email)
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

    public void addAdmin(String username, String email, String password) throws SQLException {
        // Kiểm tra xem email đã tồn tại trong hệ thống hay chưa
        if (CheckExistUser(email)) {
            // Email đã tồn tại, bạn có thể xử lý tùy thuộc vào yêu cầu cụ thể của bạn
            // Ví dụ: Báo lỗi, không thực hiện thêm admin, ...
            System.out.println("Email đã tồn tại trong hệ thống.");
        } else {
            // Kiểm tra xem username có giá trị hợp lệ hay không
            if (username != null) {
                // Tiếp tục xử lý thêm admin vào cơ sở dữ liệu
                Date dateCreated = new Date();
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

                JDBIConnector.me().get().withHandle((handle) -> {
                    return handle.createUpdate("INSERT INTO users (id, username, email, password, verify_status, date_created, role_id) VALUES (?, ?, ?, ?, ?, ?, ?)")
                            .bind(0, this.GetId() + 1)
                            .bind(1, username)
                            .bind(2, email)
                            .bind(3, hashedPassword)
                            .bind(4, "verified")
                            .bind(5, dateCreated)
                            .bind(6, 2)
                            .execute();
                });
                System.out.println("Admin đã được thêm vào cơ sở dữ liệu.");
            } else {
                // Xử lý lỗi hoặc thông báo nếu username không hợp lệ
                System.out.println("Tên người dùng không hợp lệ.");
            }
        }
    }


}