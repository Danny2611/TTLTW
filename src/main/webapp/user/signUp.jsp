<%@ page import="com.example.finallaptrinhweb.db.DbProperties" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <title>Đăng ký</title>
    <style>
        .body {
            margin: 150px 0px;
        }

        .form-container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: calc(100vh - 300px);
            margin: 40px 0;
        }

        .signup-form {
            background: #ffffff;
            padding: 35px 40px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
            text-align: center;
            max-width: 500px;
            width: 100%;
            transition: all 0.3s ease;
        }

        .signup-form:hover {
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.12);
        }

        .signup-form h2 {
            font-size: 28px;
            margin-bottom: 25px;
            color: #333;
            font-weight: 600;
            position: relative;
            padding-bottom: 12px;
        }

        .signup-form h2:after {
            content: '';
            position: absolute;
            width: 60px;
            height: 3px;
            background: #66b840;
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
            border-radius: 2px;
        }

        .error-message {
            background-color: #ffebee;
            color: #d32f2f;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 15px;
            font-size: 14px;
            border-left: 4px solid #d32f2f;
        }

        /* Tab styles */
        .tab-container {
            display: flex;
            margin-bottom: 25px;
            border-radius: 8px;
            background-color: #f5f5f5;
            padding: 4px;
        }

        .tab {
            flex: 1;
            padding: 12px 20px;
            text-align: center;
            cursor: pointer;
            border-radius: 6px;
            transition: all 0.3s ease;
            font-weight: 500;
            color: #666;
        }

        .tab.active {
            background-color: #66b840;
            color: white;
            box-shadow: 0 2px 4px rgba(102, 184, 64, 0.3);
        }

        .tab:hover:not(.active) {
            background-color: #e8e8e8;
        }

        /* Tab content */
        .tab-content {
            display: none;
        }

        .tab-content.active {
            display: block;
        }

        .input-group {
            margin-bottom: 20px;
            position: relative;
        }

        .input-group i {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #999;
            z-index: 2;
        }

        .input-group input {
            width: 100%;
            padding: 14px 15px 14px 45px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            outline: none;
            font-size: 15px;
            transition: all 0.3s ease;
            background-color: #f9f9f9;
            box-sizing: border-box;
        }

        .input-group input:focus {
            border-color: #66b840;
            box-shadow: 0 0 0 3px rgba(102, 184, 64, 0.15);
            background-color: #fff;
        }

        .input-group input:focus + i,
        .input-group input:not(:placeholder-shown) + i {
            color: #66b840;
        }

        .input-group input::placeholder {
            color: #999;
        }

        /* Password requirements */
        .password-requirements {
            background-color: #f8f9fa;
            border: 1px solid #e9ecef;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
            text-align: left;
            font-size: 13px;
            color: #666;
        }

        .password-requirements ul {
            margin: 8px 0 0 0;
            padding-left: 20px;
        }

        .password-requirements li {
            margin-bottom: 4px;
        }

        /* Terms checkbox */
        .terms-checkbox {
            display: flex;
            align-items: flex-start;
            margin-bottom: 20px;
            text-align: left;
            font-size: 14px;
        }

        .terms-checkbox input[type="checkbox"] {
            margin-right: 10px;
            margin-top: 2px;
            transform: scale(1.2);
            accent-color: #66b840;
        }

        .terms-checkbox label {
            color: #666;
            line-height: 1.4;
        }

        /* Submit button */
        .signup-form button {
            width: 100%;
            background-color: #66b840;
            color: #fff;
            border: none;
            padding: 14px;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(102, 184, 64, 0.2);
        }

        .signup-form button:hover {
            background-color: #58a035;
            transform: translateY(-2px);
            box-shadow: 0 6px 12px rgba(102, 184, 64, 0.25);
        }

        .signup-form button:active {
            transform: translateY(1px);
        }

        /* Login link */
        .login-link {
            font-size: 15px;
            margin-top: 25px;
            color: #666;
        }

        .login-link a {
            text-decoration: none;
            color: #66b840;
            font-weight: 600;
            transition: color 0.2s ease;
        }

        .login-link a:hover {
            text-decoration: underline;
        }

        /* reCAPTCHA */
        .captcha-container {
            margin: 20px 0;
            display: flex;
            justify-content: center;
        }

        .captcha-error {
            color: #d32f2f;
            font-size: 13px;
            margin: 5px 0;
            text-align: center;
        }

        .g-recaptcha {
            transform-origin: left top;
        }

        /* Error messages */
        #email-error,
        #phone-error,
        #password-match-error {
            color: #d32f2f;
            font-size: 12px;
            text-align: left;
            margin-top: 5px;
        }

        /* Responsive design */
        @media (max-width: 576px) {
            .signup-form {
                padding: 25px 20px;
                margin: 20px 10px;
            }

            .tab-container {
                margin-bottom: 20px;
            }

            .tab {
                padding: 10px 15px;
                font-size: 14px;
            }

            .input-group input {
                padding: 12px 12px 12px 40px;
            }

            .password-requirements {
                padding: 12px;
                font-size: 12px;
            }

            .terms-checkbox {
                font-size: 13px;
            }

            .g-recaptcha {
                transform: scale(0.85);
            }
        }

        @media (max-width: 320px) {
            .g-recaptcha {
                transform: scale(0.75);
            }
        }

        /* Animation for tab switching */
        .tab-content {
            animation: fadeIn 0.3s ease-in-out;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* Focus states for better accessibility */
        .tab:focus {
            outline: 2px solid #66b840;
            outline-offset: 2px;
        }

        .terms-checkbox input[type="checkbox"]:focus {
            outline: 2px solid #66b840;
            outline-offset: 2px;
        }
    </style>
</head>

<body>
<div class="website-wrapper">
    <jsp:include page="header.jsp"/>

    <div class="body">
        <div class="form-container">
            <form class="signup-form" method="post" action="./signup">
                <h2>Đăng Ký</h2>
                <% String error = (String) request.getAttribute("wrongInfor");%>
                <% if (error != null) {%>
                <p style="color: red;margin-bottom: 10px"><%=error%></p>
                <% } %>

                <!-- Tabs for switching between email and phone registration -->
                <div class="tab-container">
                    <div class="tab active" id="email-tab" onclick="switchTab('email')">Email</div>
                    <div class="tab" id="phone-tab" onclick="switchTab('phone')">Số điện thoại</div>
                </div>

                <!-- Email registration fields -->
                <div class="tab-content active" id="email-content">
                    <div class="input-group">
                        <i class="fas fa-user"></i>
                        <input type="text" id="username-email" name="username" placeholder="Tên người dùng" required>
                    </div>
                    <div class="input-group">
                        <i class="fas fa-envelope"></i>
                        <input type="email" id="email-input" name="email" placeholder="Email" required>
                    </div>
                    <div id="email-error" style="color: red; font-size: 12px;"></div>
                </div>

                <!-- Phone registration fields -->
                <div class="tab-content" id="phone-content">
                    <div class="input-group">
                        <i class="fas fa-user"></i>
                        <input type="text" id="username-phone" name="username-phone" placeholder="Tên người dùng">
                    </div>
                    <div class="input-group">
                        <i class="fas fa-phone"></i>
                        <input  id="phone-input" name="phone" placeholder="Số điện thoại (VD: 0912345678)" pattern="[0-9]{10}">
                    </div>
                    <div id="phone-error" style="color: red; font-size: 12px;"></div>
                </div>

                <!-- Common fields for both registration methods -->
                <div class="input-group">
                    <i class="fas fa-lock"></i>
                    <input type="password" id="password" name="password" placeholder="Mật khẩu" required>
                </div>

                <div class="password-requirements">
                    Mật khẩu phải đáp ứng các yêu cầu sau:
                    <ul>
                        <li>Ít nhất 8 ký tự</li>
                        <li>Bao gồm ít nhất 1 chữ số</li>
                        <li>Bao gồm ít nhất 1 chữ in hoa</li>
                        <li>Bao gồm ít nhất 1 ký tự đặc biệt</li>
                    </ul>
                </div>

                <div class="input-group">
                    <i class="fas fa-lock"></i>
                    <input type="password" id="repassword" name="repassword" placeholder="Nhập lại mật khẩu" required>
                </div>
                <div id="password-match-error" style="color: red; font-size: 12px;"></div>

                <div class="terms-checkbox">
                    <input type="checkbox" id="agree-terms" name="agree-terms" required>
                    <label for="agree-terms">Tôi đồng ý với điều khoản dịch vụ và chính sách bảo mật</label>
                </div>

                <!-- Hidden fields for form submission -->
                <input type="hidden" name="role" value="1">
                <input type="hidden" id="signup-method" name="method" value="email">

                <!-- reCAPTCHA container -->
                <div class="captcha-container">
                    <div class="g-recaptcha" data-sitekey="<%= DbProperties.RECAPTCHA_SITE_KEY %>"></div>
                </div>

                <% String captchaError = (String) request.getAttribute("captchaError");%>
                <% if (captchaError != null) {%>
                <p class="captcha-error"><%=captchaError%></p>
                <% } %>
                <div id="recaptcha-error" class="captcha-error" style="display: none;">Vui lòng xác nhận reCAPTCHA</div>

                <button type="submit">Đăng Ký</button>
                <div class="login-link">Bạn đã có tài khoản? <a href="signIn.jsp">Đăng nhập</a></div>
            </form>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</div>

<script src="js/sign/scipts.js"></script>
<script>
    // JavaScript để kiểm tra reCAPTCHA trước khi gửi form
    document.querySelector('.signup-form').addEventListener('submit', function(e) {
        // Kiểm tra reCAPTCHA
        var recaptchaResponse = grecaptcha.getResponse();
        if(recaptchaResponse.length === 0) {
            e.preventDefault();
            document.getElementById('recaptcha-error').style.display = 'block';
            return;
        } else {
            document.getElementById('recaptcha-error').style.display = 'none';
        }

        // Kiểm tra mật khẩu trùng khớp
        const password = document.getElementById('password').value;
        const repassword = document.getElementById('repassword').value;

        if (password !== repassword) {
            e.preventDefault();
            document.getElementById('password-match-error').textContent = 'Mật khẩu không trùng khớp!';
            return;
        } else {
            document.getElementById('password-match-error').textContent = '';
        }

        // Kiểm tra định dạng email khi đăng ký bằng email
        if (document.getElementById('signup-method').value === 'email') {
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            const email = document.getElementById('email-input').value;

            if (!emailPattern.test(email)) {
                e.preventDefault();
                document.getElementById('email-error').textContent = 'Email không hợp lệ!';
                return;
            } else {
                document.getElementById('email-error').textContent = '';
            }
        }

        // Kiểm tra định dạng số điện thoại khi đăng ký bằng số điện thoại
        if (document.getElementById('signup-method').value === 'phone') {
            const phonePattern = /^0\d{9}$/;
            const phone = document.getElementById('phone-input').value;

            if (!phonePattern.test(phone)) {
                e.preventDefault();
                document.getElementById('phone-error').textContent = 'Số điện thoại không hợp lệ! Phải bắt đầu bằng số 0 và có 10 chữ số.';
                return;
            } else {
                document.getElementById('phone-error').textContent = '';
            }
        }
    });

    // Kiểm tra độ mạnh của mật khẩu khi nhập
    document.getElementById('password').addEventListener('input', function() {
        const password = this.value;
        const passwordMatch = document.getElementById('password-match-error');

        if (document.getElementById('repassword').value !== '' &&
            document.getElementById('repassword').value !== password) {
            passwordMatch.textContent = 'Mật khẩu không trùng khớp!';
        } else {
            passwordMatch.textContent = '';
        }
    });

    document.getElementById('repassword').addEventListener('input', function() {
        const repassword = this.value;
        const password = document.getElementById('password').value;
        const passwordMatch = document.getElementById('password-match-error');

        if (repassword !== password) {
            passwordMatch.textContent = 'Mật khẩu không trùng khớp!';
        } else {
            passwordMatch.textContent = '';
        }
    });

    // Chuyển đổi giữa tab đăng ký bằng email và số điện thoại
    function switchTab(method) {
        const emailTab = document.getElementById('email-tab');
        const phoneTab = document.getElementById('phone-tab');
        const emailContent = document.getElementById('email-content');
        const phoneContent = document.getElementById('phone-content');
        const methodInput = document.getElementById('signup-method');

        const usernameEmail = document.getElementById('username-email');
        const usernamePhone = document.getElementById('username-phone');

        if (method === 'email') {
            emailTab.classList.add('active');
            phoneTab.classList.remove('active');
            emailContent.classList.add('active');
            phoneContent.classList.remove('active');

            // Cập nhật name attribute cho đúng
            usernameEmail.setAttribute('name', 'username');
            usernamePhone.setAttribute('name', 'username-phone');

            // Set required chỉ cho các trường đang hiển thị
            usernameEmail.setAttribute('required', 'required');
            document.getElementById('email-input').setAttribute('required', 'required');
            usernamePhone.removeAttribute('required');
            document.getElementById('phone-input').removeAttribute('required');

            methodInput.value = 'email';
        } else {
            phoneTab.classList.add('active');
            emailTab.classList.remove('active');
            phoneContent.classList.add('active');
            emailContent.classList.remove('active');

            // Cập nhật name attribute cho đúng
            usernameEmail.setAttribute('name', 'username-email');
            usernamePhone.setAttribute('name', 'username');

            // Set required chỉ cho các trường đang hiển thị
            usernamePhone.setAttribute('required', 'required');
            document.getElementById('phone-input').setAttribute('required', 'required');
            usernameEmail.removeAttribute('required');
            document.getElementById('email-input').removeAttribute('required');

            methodInput.value = 'phone';
        }
    }
</script>
</body>
</html>