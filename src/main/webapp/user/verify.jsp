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
    <title>Đăng nhập</title>
</head>

<body>
<div class="website-wrapper">
    <jsp:include page="header.jsp"/>

    <div class="body">
        <div class="form-container">
            <form class="sign-in-form" method="post" action="verify">
                <h2>Xác thực</h2>
                <% String error = (String) request.getAttribute("wrongAuthCode");%>
                <% if (error != null) {%>
                <p style="color: red; margin-bottom: 10px"><%=error%>
                </p>
                <% } %>
                <div class="input-group">
                    <input type="text" name="verifycode" placeholder="Vui lòng nhập mã xác thực" required>
                </div>
                <button>Gửi</button>
            </form>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>

<script src="js/sign/scipts.js"></script>

</body>

</html>