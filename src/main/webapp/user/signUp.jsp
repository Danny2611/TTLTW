<%@ page import="com.example.finallaptrinhweb.db.DbProperties" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Đăng ký</title>
    <style>
        .body{
            margin: 100px 0px;
        }

        /* CSS cho reCAPTCHA */
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
            <form class="signup-form" method="post" action="./signup">
                <h2>Đăng Ký</h2>
                <% String error = (String) request.getAttribute("wrongInfor");%>
                <% if (error != null) {%>
                <p style="color: red;margin-bottom: 10px"><%=error%>
                </p>
                <% } %>
                <div class="input-group">
                    <input type="text" id="username" name="username" placeholder="Tên người dùng" required>
                </div>
                <div class="input-group">
                    <input type="email" id="email-forgot" name="email" placeholder="Email" required>
                </div>
                <div id="email-error" style="color: red;"></div>
                <div class="input-group">
                    <input type="password" placeholder="Nhập mật khẩu mới" name="password" required>
                </div>
                <div class="input-group">
                    <input type="password" placeholder="Nhập lại mật khẩu mới" name="repassword" required>
                </div>
                <div class="terms-checkbox">
                    <input type="checkbox" id="agree-terms" name="agree-terms" required>
                    <label for="agree-terms">Tôi đồng ý với điều khoản dịch vụ và chính sách bảo mật</label>
                </div>
                <input type="hidden" name="role" value="1">

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
    <jsp:include page="loader.jsp" />
    <jsp:include page="footer.jsp"/>
</div>


<script src="js/sign/scipts.js"></script>
<script>
    // JavaScript để kiểm tra reCAPTCHA trước khi gửi form
    document.querySelector('.signup-form').addEventListener('submit', function(e) {
        var response = grecaptcha.getResponse();
        if(response.length === 0) {
            e.preventDefault();
            document.getElementById('recaptcha-error').style.display = 'block';
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
<script>
    document.querySelector('.signup-form').addEventListener('submit', function () {
        document.querySelector('.overlay_loader').style.display = 'flex';
    });
</script>

</body>

</html>