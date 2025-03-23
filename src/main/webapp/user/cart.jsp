<%@ page import="com.example.finallaptrinhweb.model.Cart" %>
<%@ page import="com.example.finallaptrinhweb.model.CartItem" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.finallaptrinhweb.model.Util" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>

    <link rel="stylesheet" href="css/thuvien/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/cart/cart.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <title>Giỏ hàng</title>
</head>

<body>
<div class="website-wrapper">
    <jsp:include page="header.jsp"/>
    <div class="page-title" style="
            background-image: url(https://tienthangvet.vn/wp-content/uploads/title-tag-tien-thang-vet-tsd1.jpg);
          ">
        <div class="container">
            <h1 class="title">Giỏ hàng</h1>
        </div>
    </div>


    <!-- Breadcrumb Section End -->
    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <%
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null || cart.isEmpty()) {
        %>
        <h1 style="text-align: center">Vui lòng mua sắm</h1>
        <%} else {%>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                            <tr>
                                <th class="shoping__product">Sản phẩm</th>
                                <th>Giá bán</th>
                                <th>Số lượng</th>
                                <th>Tổng</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (CartItem item : cart.getProducts().values()) {
                            %>
                            <tr>
                                <td class="shoping__cart__item">
                                    <img src="${pageContext.request.contextPath}/<%=item.getProduct().getImageUrl()%>" alt="">

                                    <h5><%=item.getProduct().getProductName()%>
                                    </h5>
                                </td>
                                <td class="shoping__cart__price">
                                    <%= Util.formatCurrency(item.getProduct().getPrice()) %> VND
                                </td>
                                <td class="shoping__cart__quantity">
                                    <div class="quantity"
                                         style="align-items: center;display: flex;justify-content: center;">
                                        <div class="pro-qty"
                                             style="display: flex;justify-content: center;align-items: center;">
                                            <a href="javascript:void(0);" class="update-cart-btn" data-action="decrement" data-id="<%= item.getProduct().getId() %>">-</a>
                                            <p ><%= item.getQuantity() %></p>
                                            <a href="javascript:void(0);" class="update-cart-btn" data-action="increment" data-id="<%= item.getProduct().getId() %>">+</a>
                                        </div>
                                    </div>
                                </td>
                                <td class="shoping__cart__total">
                                    <%= Util.formatCurrency(item.getTotalPrice()) %> VND
                                </td>
                                <td class="shoping__cart__item__close">
                                    <a style="font-size: 18px;background-color: white"
                                       href="updatecart?action=delete&id=<%=item.getProduct().getId()%>">X</a>
                                </td>
                            </tr>
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="products" class="primary-btn cart-btn">TIẾP TỤC MUA SẮM</a>
                    </div>
                </div>
                <div class="custom-row">
                    <div class="custom-col">
                        <div class="shoping__continue">
                            <div class="shoping__discount">
                                <h5>Mã giảm giá</h5>
                                <form action="addtocart">
                                    <input type="text" name="discount" placeholder="Nhập mã giãm giá mua hàng">
                                    <button class="site-btn">SỬ DỤNG MÃ</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="custom-col">
                        <div class="shoping__checkout">
                            <h5>TỔNG TIỀN GIỎ HÀNG</h5>
                            <ul>
                                <li>Giảm<span>${ Util.formatCurrency(cart.totalPrice - cart.priceSaled) } VND</span></li>
                                <li>Tổng<span>${ Util.formatCurrency(cart.priceSaled) } VND</span></li>
                            </ul>

                            <c:choose>
                                <c:when test="${empty sessionScope.auth}">
                                    <a href="signIn.jsp" class="primary-btn">Vui lòng đăng nhập để thực hiện thanh toán</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="checkout" class="primary-btn">TIẾN HÀNH THANH TOÁN</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <%}%>
    </section>


    <jsp:include page="footer.jsp"/>
</div>
<script>
    window.addEventListener('scroll', () => {
        var header = document.querySelector('.container')
        header.classList.toggle('sticky', window.scrollY > 100)
    })




</script>
<script>


    // Hàm gọi API cập nhật giỏ hàng
    // function updateCart(action, productId, amount = 0, currentQuantity = 0) {
    //     fetch('/user/updatecart', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/x-www-form-urlencoded'
    //         },
    //         body: new URLSearchParams({
    //             action: action,
    //             id: productId,
    //             amount: amount,
    //             currentQuantity: currentQuantity
    //         })
    //     })
    //         .then(response => response.json())
    //         .then(data => {
    //             if (data.status === "success") {
    //                 console.log("✅ Cập nhật giỏ hàng:", data.message);
    //                 location.reload(); // Reload lại trang để cập nhật UI
    //             } else {
    //                 alert("Lỗi: " + data.message);
    //             }
    //         })
    //         .catch(error => console.error("❌ Lỗi kết nối:", error));
    // }

</script>

</body>

</html>