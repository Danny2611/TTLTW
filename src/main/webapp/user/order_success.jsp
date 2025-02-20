<%--
  Created by IntelliJ IDEA.
  User: jonny
  Date: 03/01/2024
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/products/styles.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <title>Đặt Hàng Thành Công</title>
    <style>
        .container-success {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            font-size: 20px;
            margin: 140px 0;
        }

        .container-success h1 {
            color: #333;
            font-size: 20px;
        }

        .container-success p {
            color: #555;
            font-size: 16px;
            margin-top: 16px;
        }

        .container-success ul {
            list-style-type: none;
            padding: 0;
        }

        .container-success li {
            margin-bottom: 10px;
        }

        .container-success a {
            display: inline-block;
            margin-top: 15px;
            font-size: 16px;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #66b840;
            border-radius: 5px;
        }

        .container-success a:hover {
            background-color: #66b840;
        }
    </style>
</head>
<body>

    <jsp:include page="header.jsp"/>
    <div class="container-success">
        <h1>Đơn Hàng Đã Đặt Thành Công</h1>

        <p>Cảm ơn bạn đã đặt hàng. Đơn hàng của bạn đã được ghi nhận và sẽ được xử lý trong thời gian sớm nhất.</p>

        <!-- Nút Trở về Trang Chủ -->
        <a href="${pageContext.request.contextPath}/user/home">Trở về Trang Chủ</a>

        <!-- Nút Mua Sắm Tiếp -->
        <a href="${pageContext.request.contextPath}/user/products">Mua Sắm Tiếp</a>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>

</body>
</html>
