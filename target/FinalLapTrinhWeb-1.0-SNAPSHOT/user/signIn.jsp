<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.finallaptrinhweb.config.GoogleOAuthConfig" %>
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
    <title>Đăng nhập</title>
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
                <p style="color: red; margin-bottom: 10px"><%=error%>
                </p>
                <% } %>
                <div class="input-group">
                    <input type="email" id="email" name="email" placeholder="Email" required>
                    <div id="email-error" style="color: red;"></div>

                </div>
                <div class="input-group">
                    <input type="password" id="password" name="password" placeholder="Mật khẩu" required>
                    <div id="password-error" style="color: red;"></div>

                </div>
                <button>Đăng Nhập</button>
                <div class="forgot-password">
                    <a href="forgotPass.jsp">Quên mật khẩu?</a>
                </div>

                <div class="or"><span>Hoặc </span></div>
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

</body>

</html>