<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/sign/form.css"/> <!-- Đặt tên cho file CSS của bạn -->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <title>Quên mật khẩu</title>
</head>

<body>
<div class="website-wrapper">
    <jsp:include page="header.jsp"/>

    <div class="body">
        <div class="form-container">
            <form class="forgot-password-form" method="post" action="forgotpassword">
                <h2>Quên Mật Khẩu</h2>
                <% String error = (String) request.getAttribute("wrongInfor");%>
                <% if (error != null) {%>
                <p style="color: red"><%=error%>
                </p>
                <% } %>
                <div class="input-group">
                    <input type="text" id="identifier" name="identifier" placeholder="Email hoặc Số điện thoại" required>
                    <div id="identifier-error" style="color: red;"></div>
                </div>

                <div>
                    <button>Đặt lại mật khẩu</button>
                </div>
                <div class="link">
                    <a href="signUp.jsp">Đăng ký</a> <span> Hoặc</span>
                    <a href="signIn.jsp">Đăng nhập</a>
                </div>

            </form>
        </div>

    </div>
    <jsp:include page="footer.jsp"/>
</div>
<script src="js/sign/scipts.js"></script>

</body>

</html>