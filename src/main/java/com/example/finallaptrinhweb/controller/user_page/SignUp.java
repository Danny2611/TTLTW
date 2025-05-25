package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.config.FirebaseConfig;
import com.example.finallaptrinhweb.controller.user_page.MailService.SendEmail;
import com.example.finallaptrinhweb.dao.UserDAO;
import com.example.finallaptrinhweb.utill.ReCaptchaVerifier;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/signup")
public class SignUp extends HttpServlet {
    public SignUp() {
        System.out.println("SignUp Servlet Initialized");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Get");
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        String signupMethod = request.getParameter("method"); // "email" or "phone"

        String roleParam = request.getParameter("role");
        int roleId = Integer.parseInt(roleParam);

        // Xác thực reCAPTCHA
        boolean isRecaptchaValid = ReCaptchaVerifier.verify(gRecaptchaResponse);
        System.out.println("reCAPTCHA verification result: " + isRecaptchaValid);

        if (!isRecaptchaValid) {
            request.setAttribute("wrongInfor", "Vui lòng xác nhận reCAPTCHA");
            request.getRequestDispatcher("/user/signUp.jsp").forward(request, response);
            return;
        }

        try {
            // Kiểm tra phương thức đăng ký
            if ("email".equals(signupMethod)) {
                handleEmailSignup(request, response, name, email, pass, repass, roleId);
            } else if ("phone".equals(signupMethod)) {
                handlePhoneSignup(request, response, name, phone, pass, repass, roleId);
            } else {
                request.setAttribute("wrongInfor", "Phương thức đăng ký không hợp lệ!");
                request.getRequestDispatcher("/user/signUp.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("wrongInfor", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("/user/signUp.jsp").forward(request, response);
        }
    }

    // Xử lý đăng ký bằng email
    private void handleEmailSignup(HttpServletRequest request, HttpServletResponse response,
                                   String name, String email, String pass, String repass, int roleId)
            throws ServletException, IOException, SQLException {
        SendEmail send = new SendEmail();
        String code = send.getRandomVerifyCode();

        if (repass.equals(pass)) {
            // Kiểm tra độ dài và thành phần của mật khẩu
            if (isStrongPassword(pass) && !UserDAO.getInstance().CheckExistUser(email)) {
                UserDAO.getInstance().SignUp(name, email, pass, code, roleId);
                if (send.sendVerifyCode(email, code)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("authcode", code);
                    session.setAttribute("verifyMethod", "email");
                    response.sendRedirect("./verify.jsp");
                }
            } else {
                request.setAttribute("wrongInfor", "Mật khẩu phải có ít nhất 8 ký tự, bao gồm số, chữ in hoa và ký tự đặc biệt hoặc email đã tồn tại!");
                request.getRequestDispatcher("/user/signUp.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("wrongInfor", "Mật khẩu không trùng khớp!");
            request.getRequestDispatcher("/user/signUp.jsp").forward(request, response);
        }
    }

    // Xử lý đăng ký bằng số điện thoại
    private void handlePhoneSignup(HttpServletRequest request, HttpServletResponse response,
                                   String name, String phone, String pass, String repass, int roleId)
            throws ServletException, IOException, SQLException {
        // Kiểm tra xem số điện thoại đã tồn tại chưa
        if (UserDAO.getInstance().CheckExistUser(phone) && UserDAO.getInstance().CheckPhoneVerifiedStatus(phone)) {
            request.setAttribute("wrongInfor", "Số điện thoại đã được đăng ký!");
            request.getRequestDispatcher("/user/signUp.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu
        if (!repass.equals(pass)) {
            request.setAttribute("wrongInfor", "Mật khẩu không trùng khớp!");
            request.getRequestDispatcher("/user/signUp.jsp").forward(request, response);
            return;
        }

        if (!isStrongPassword(pass)) {
            request.setAttribute("wrongInfor", "Mật khẩu phải có ít nhất 8 ký tự, bao gồm số, chữ in hoa và ký tự đặc biệt!");
            request.getRequestDispatcher("/user/signUp.jsp").forward(request, response);
            return;
        }

        try {
            // Tạo một mã xác nhận ngẫu nhiên (tương tự như cho email)
            // Nhưng thực tế Firebase sẽ gửi OTP riêng của nó
            String verifyCode = String.format("%06d", (int)(Math.random() * 1000000));

            // Tạo người dùng mới với trạng thái chưa xác thực
            UserDAO.getInstance().SignUpWithPhone(name, phone, pass, verifyCode, null, roleId);

            // Lưu thông tin cần thiết vào session
            HttpSession session = request.getSession();
            session.setAttribute("phoneNumber", phone);
            session.setAttribute("authcode", verifyCode);
            session.setAttribute("verifyMethod", "phone");

            // Chuyển hướng đến trang xác thực
            response.sendRedirect("./verifyPhone.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("wrongInfor", "Đăng ký thất bại: " + e.getMessage());
            request.getRequestDispatcher("/user/signUp.jsp").forward(request, response);
        }
    }

    // Phương thức kiểm tra xem mật khẩu có đủ mạnh không
    private boolean isStrongPassword(String s) {
        // Kiểm tra độ dài
        if (s.length() < 8) {
            return false;
        }

        // Kiểm tra chứa số
        if (!s.matches(".*\\d.*")) {
            return false;
        }

        // Kiểm tra chứa chữ in hoa
        if (!s.matches(".*[A-Z].*")) {
            return false;
        }

        // Kiểm tra chứa ký tự đặc biệt
        if (!s.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return false;
        }

        return true;
    }
}