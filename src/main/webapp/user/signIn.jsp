<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.finallaptrinhweb.config.GoogleOAuthConfig" %>
<%@ page import="com.example.finallaptrinhweb.db.DbProperties" %>
<%@ page import="com.example.finallaptrinhweb.config.FacebookOAuthConfig" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
<%--    <link rel="stylesheet" href="css/sign/form.css"/>--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <title>Đăng nhập</title>
    <style>

        .body{
            margin: 150px 0px;
        }

        .form-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: calc(100vh - 300px);
            margin: 40px 0;
        }

        .sign-in-form {
            background: #ffffff;
            padding: 35px 40px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
            text-align: center;
            max-width: 450px;
            width: 100%;
            transition: all 0.3s ease;
        }

        .sign-in-form:hover {
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.12);
        }

        .sign-in-form h2 {
            font-size: 28px;
            margin-bottom: 25px;
            color: #333;
            font-weight: 600;
            position: relative;
            padding-bottom: 12px;
        }

        .sign-in-form h2:after {
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

        .input-group {
            margin-bottom: 20px;
            position: relative;
        }

        .input-group input {
            width: 100%;
            padding: 14px 15px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            outline: none;
            font-size: 15px;
            transition: all 0.3s ease;
            background-color: #f9f9f9;
        }

        .input-group input:focus {
            border-color: #66b840;
            box-shadow: 0 0 0 3px rgba(102, 184, 64, 0.15);
            background-color: #fff;
        }

        .input-group input::placeholder {
            color: #999;
        }

        /* Cải thiện nút đăng nhập */
        .sign-in-form button {
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

        .sign-in-form button:hover {
            background-color: #58a035;
            transform: translateY(-2px);
            box-shadow: 0 6px 12px rgba(102, 184, 64, 0.25);
        }

        .sign-in-form button:active {
            transform: translateY(1px);
        }

        .forgot-password {
            margin-top: 18px;
            font-size: 14px;
        }

        .forgot-password a {
            color: #5c5c5c;
            text-decoration: none;
            transition: color 0.2s ease;
        }

        .forgot-password a:hover {
            color: #66b840;
            text-decoration: underline;
        }

        /* Cải thiện OR divider */
        .or {
            margin: 25px 0;
            position: relative;
            text-align: center;
        }

        .or span {
            display: inline-block;
            padding: 0 15px;
            background: #fff;
            position: relative;
            z-index: 2;
            color: #777;
            font-size: 14px;
        }

        .or:after {
            content: "";
            width: 100%;
            height: 1px;
            position: absolute;
            top: 50%;
            left: 0;
            background: #e0e0e0;
        }

        /* Cải thiện Social Login Buttons */
        .social-icons {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin: 25px 0;
        }

        .social-icons .google-btn,
        .social-icons .facebook-btn {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 12px 20px;
            border-radius: 8px;
            text-decoration: none;
            font-size: 15px;
            font-weight: 500;
            transition: all 0.3s ease;
            flex: 1;
            border: 1px solid #e0e0e0;
        }

        .social-icons .google-btn {
            background-color: #fff;
            color: #444;
        }

        .social-icons .facebook-btn {
            background-color: #1877f2;
            color: white;
        }

        .social-icons .google-btn:hover {
            background-color: #f5f5f5;
            border-color: #ddd;
        }

        .social-icons .facebook-btn:hover {
            background-color: #166fe5;
        }

        .social-icons i {
            margin-right: 10px;
            font-size: 18px;
        }

        /* Cải thiện Register Link */
        .register-link {
            font-size: 15px;
            margin-top: 25px;
            color: #666;
        }

        .register-link a {
            text-decoration: none;
            color: #66b840;
            font-weight: 600;
            transition: color 0.2s ease;
        }

        .register-link a:hover {
            text-decoration: underline;
        }

        /* Cải thiện ReCAPTCHA */
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

        /* Responsive design */
        @media (max-width: 576px) {
            .sign-in-form {
                padding: 25px 20px;
            }

            .social-icons {
                flex-direction: column;
            }

            .social-icons .google-btn,
            .social-icons .facebook-btn {
                width: 100%;
                margin-bottom: 10px;
            }
        }


    </style>
</head>

<body>
<div class="website-wrapper">
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="form-container">
            <form class="sign-in-form" method="post" action="signin">
                <h2>Đăng Nhập</h2>
                <% String error = (String) request.getAttribute("wrongInfor");%>
                <% if (error != null) {%>
                <p class="error-message"><%=error%></p>
                <% } %>

                <div class="input-group">
                    <input type="email" id="email" name="email" placeholder="Email" required>
                    <div id="email-error" style="color: red;"></div>
                </div>

                <div class="input-group">
                    <input type="password" id="password" name="password" placeholder="Mật khẩu" required>
                    <div id="password-error" style="color: red;"></div>
                </div>

                <!-- reCAPTCHA container -->
                <div class="captcha-container">
                    <div class="g-recaptcha" data-sitekey="<%= DbProperties.RECAPTCHA_SITE_KEY %>"></div>
                </div>

                <% String captchaError = (String) request.getAttribute("error");%>
                <% if (captchaError != null) {%>
                <p class="captcha-error"><%=captchaError%></p>
                <% } %>
                <div id="recaptcha-error" class="captcha-error" style="display: none;">Vui lòng xác nhận reCAPTCHA</div>

                <button type="submit">Đăng Nhập</button>

                <div class="forgot-password">
                    <a href="forgotPass.jsp">Quên mật khẩu?</a>
                </div>

                <div class="or"><span>Hoặc</span></div>

                <div class="social-icons">
                    <a href="<%= GoogleOAuthConfig.getAuthorizationUrl() %>" class="google-btn">
                        <i class="fab fa-google"></i>
                        <span>Google</span>
                    </a>
                    <a href="<%= FacebookOAuthConfig.getAuthorizationUrl() %>" class="facebook-btn">
                        <i class="fab fa-facebook"></i>
                        <span>Facebook</span>
                    </a>
                </div>

                <div class="register-link">
                    Bạn chưa có tài khoản? <a href="signUp.jsp">Đăng ký</a>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>

<script src="js/sign/scipts.js"></script>

<script>
    // JavaScript để kiểm tra reCAPTCHA trước khi gửi form
    document.querySelector('.sign-in-form').addEventListener('submit', function(e) {
        var response = grecaptcha.getResponse();
        if(response.length === 0) {
            e.preventDefault();
            document.getElementById('recaptcha-error').style.display = 'block';
            // Cuộn đến thông báo lỗi
            // document.getElementById('recaptcha-error').scrollIntoView({ behavior: 'smooth' });
        } else {
            document.getElementById('recaptcha-error').style.display = 'none';
        }
    });

    // Ẩn thông báo lỗi reCAPTCHA khi người dùng bắt đầu xác minh
    grecaptcha.ready(function() {
        grecaptcha.render('g-recaptcha', {
            'callback': function() {
                document.getElementById('recaptcha-error').style.display = 'none';
            }
        });
    });
</script>

</body>
</html>