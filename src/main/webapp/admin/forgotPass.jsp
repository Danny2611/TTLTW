<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="assets/css/form.css"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <title>Quên mật khẩu</title>
</head>

<body>
<div class="website-wrapper">

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
                    <input type="email" id="email-forgot" name="email" placeholder="Email" required>
                </div>
                <div id="email-error" style="color: red;"></div>

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
</div>

</body>

</html>