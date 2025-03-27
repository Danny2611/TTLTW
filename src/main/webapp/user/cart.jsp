<%@ page import="com.example.finallaptrinhweb.model.Cart" %>
<%@ page import="com.example.finallaptrinhweb.model.CartItem" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <title>Giỏ hàng</title>

    <!-- CSS Libraries -->
    <link rel="stylesheet" href="css/thuvien/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/cart/cart.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <!-- Favicon -->
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png" sizes="192x192"/>
    <script src="./js/cart.js"></script>
</head>

<body>
<jsp:include page="header.jsp"/>
<div class="container" id="cart-container"></div>
<jsp:include page="footer.jsp"/>
</body>

</html>
