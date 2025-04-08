<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.finallaptrinhweb.config.GoogleOAuthConfig" %>
<%@ page import="com.example.finallaptrinhweb.db.DbProperties" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/sign/form.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <title>Đăng nhập</title>
    <style>
        /* CSS cho reCAPTCHA */
        .body{
            margin: 100px 0px;
        }
        .captcha-container {
            margin: 15px 0;
            display: flex;
            justify-content: center;
        }

        .g-recaptcha {
            transform-origin: left top;
            /* Bạn có thể điều chỉnh scale nếu cần thiết */
            /* transform: scale(0.95); */
        }

        .captcha-error {
            color: red;
            font-size: 13px;
            margin: 5px 0px;
            text-align: center;
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
                    <a href="<%= GoogleOAuthConfig.getAuthorizationUrl() %>">
                        <img src="assets/img/formIcon/google.jpg" alt="Google">
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