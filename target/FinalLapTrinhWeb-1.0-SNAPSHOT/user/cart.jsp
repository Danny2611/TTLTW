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
<%--    <section class="shoping-cart spad">--%>
<%--        <%--%>
<%--            Cart cart = (Cart) session.getAttribute("cart");--%>
<%--            System.out.println("Cart in session: " + cart);--%>
<%--            if (cart == null || cart.isEmpty()) {--%>
<%--        %>--%>
<%--        <h1 style="text-align: center">Vui lòng mua sắm</h1>--%>
<%--        <%} else {%>--%>
<%--        <div  id="cart-container" class="container">--%>
<%--            <div class="row">--%>
<%--                <div class="col-lg-12">--%>
<%--                    <div class="shoping__cart__table">--%>
<%--                        <table>--%>
<%--                            <thead>--%>
<%--                            <tr>--%>
<%--                                <th class="shoping__product">Sản phẩm</th>--%>
<%--                                <th>Giá bán</th>--%>
<%--                                <th>Số lượng</th>--%>
<%--                                <th>Tổng</th>--%>
<%--                                <th></th>--%>
<%--                            </tr>--%>
<%--                            </thead>--%>
<%--                            <tbody>--%>
<%--                            <%--%>
<%--                                for (CartItem item : cart.getProducts().values()) {--%>
<%--                            %>--%>
<%--                            <tr>--%>
<%--                                <td class="shoping__cart__item">--%>
<%--                                    <img src="${pageContext.request.contextPath}/<%=item.getProduct().getImageUrl()%>" alt="">--%>

<%--                                    <h5><%=item.getProduct().getProductName()%>--%>
<%--                                    </h5>--%>
<%--                                </td>--%>
<%--                                <td class="shoping__cart__price">--%>
<%--                                    <%= Util.formatCurrency(item.getProduct().getPrice()) %> VND--%>
<%--                                </td>--%>
<%--                                <td class="shoping__cart__quantity">--%>
<%--                                    <div class="quantity"--%>
<%--                                         style="align-items: center;display: flex;justify-content: center;">--%>
<%--                                        <div class="pro-qty"--%>
<%--                                             style="display: flex;justify-content: center;align-items: center;">--%>
<%--                                            <a style="display: block;padding: 5px 16px;font-size: 20px;" href="javascript:void(0);" class="update-cart-btn" id="decrement" data-id="<%= item.getProduct().getId() %>">-</a>--%>
<%--                                            <p style="padding: 5px 10px;"><%= item.getQuantity() %></p>--%>
<%--                                            <a style="display: block;padding: 5px 16px;font-size: 20px;"  href="javascript:void(0);" class="update-cart-btn" id="increment" data-id="<%= item.getProduct().getId() %>">+</a>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                                <td class="shoping__cart__total">--%>
<%--                                    <%= Util.formatCurrency(item.getProduct().getPrice() * item.getQuantity()) %> VND--%>
<%--                                </td>--%>
<%--                                <td class="shoping__cart__item__close">--%>
<%--                                    <a style="font-size: 18px;background-color: white"--%>
<%--                                       href="updatecart?action=delete&id=<%=item.getProduct().getId()%>">X</a>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <%}%>--%>
<%--                            </tbody>--%>
<%--                        </table>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="row">--%>
<%--                <div class="col-lg-12">--%>
<%--                    <div class="shoping__cart__btns">--%>
<%--                        <a href="products" class="primary-btn cart-btn">TIẾP TỤC MUA SẮM</a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="custom-row">--%>
<%--                    <div class="custom-col">--%>
<%--                        <div class="shoping__continue">--%>
<%--                            <div class="shoping__discount">--%>
<%--                                <h5>Mã giảm giá</h5>--%>
<%--                                <form action="addtocart">--%>
<%--                                    <input type="text" name="discount" placeholder="Nhập mã giãm giá mua hàng">--%>
<%--                                    <button class="site-btn">SỬ DỤNG MÃ</button>--%>
<%--                                </form>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="custom-col">--%>
<%--                        <div class="shoping__checkout">--%>
<%--                            <h5>TỔNG TIỀN GIỎ HÀNG</h5>--%>
<%--                            <ul>--%>
<%--&lt;%&ndash;                                <li>Giảm<span>${ Util.formatCurrency(cart.totalPrice - cart.priceSaled) } VND</span></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <li>Tổng<span>${ Util.formatCurrency(cart.priceSaled) } VND</span></li>&ndash;%&gt;--%>
<%--                            </ul>--%>

<%--                            <c:choose>--%>
<%--                                <c:when test="${empty sessionScope.auth}">--%>
<%--                                    <a href="signIn.jsp" class="primary-btn">Vui lòng đăng nhập để thực hiện thanh toán</a>--%>
<%--                                </c:when>--%>
<%--                                <c:otherwise>--%>
<%--                                    <a href="checkout" class="primary-btn">TIẾN HÀNH THANH TOÁN</a>--%>
<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--        <%}%>--%>
<%--    </section>--%>
        <div  id="cart-container" class="container">
    <jsp:include page="footer.jsp"/>
</div>
<script>
    window.addEventListener('scroll', () => {
        var header = document.querySelector('.container')
        header.classList.toggle('sticky', window.scrollY > 100)
    })




</script>
<script>
<%--    Increase quantity--%>
const increment = document.getElementById("increment");
increment.onclick = async  ()=>{
    const productId = increment.getAttribute("data-id");
    console.log(productId);

//     Call api

}

</script>
<script>
    async function fetchCart() {
        try {
            const response = await fetch(`${pageContext.request.contextPath}/api/cart`);
            const cart = await response.json();
            console.log("cart" , cart)
            const cartContainer = document.getElementById('cart-container');

            if (cart.cartItems.length === 0) {
                cartContainer.innerHTML = '<h3>Giỏ hàng trống!</h3>';
                return;
            }

            let cartHTML = `
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
                    <tbody>`;

            cart.cartItems.forEach(item => {
                cartHTML += `
                <tr>
                    <td class="shoping__cart__item">
                        <img src="${pageContext.request.contextPath}/${item.imageUrl}" alt="${item.productName}">
                        <h5>${item.productName}</h5>
                    </td>
                    <td class="shoping__cart__price">${item.price.toLocaleString()} VND</td>
                    <td class="shoping__cart__quantity">
                        <div class="quantity" style="display: flex; align-items: center; justify-content: center;">
                            <a href="javascript:void(0);" class="update-cart-btn" data-action="decrement" data-id="${item.productId}" style="padding: 5px 16px; font-size: 20px;">-</a>
                            <p style="padding: 5px 10px;">${item.quantity}</p>
                            <a href="javascript:void(0);" class="update-cart-btn" data-action="increment" data-id="${item.productId}" style="padding: 5px 16px; font-size: 20px;">+</a>
                        </div>
                    </td>
                    <td class="shoping__cart__total">${item.totalPrice.toLocaleString()} VND</td>
                    <td class="shoping__cart__item__close">
                        <a href="javascript:void(0);" class="delete-cart-btn" data-id="${item.productId}" style="font-size: 18px; background-color: white;">X</a>
                    </td>
                </tr>`;
            });

            cartHTML += `
                    </tbody>
                </table>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <a href="products" class="primary-btn cart-btn">TIẾP TỤC MUA SẮM</a>
                </div>
                <div class="custom-col">
                    <div class="shoping__checkout">
                        <h5>TỔNG TIỀN GIỎ HÀNG</h5>
                        <ul>
                            <li>Tổng: <span>${cart.totalAmount.toLocaleString()} VND</span></li>
                        </ul>
                        ${cart.isAuthenticated ?
                            '<a href="checkout" class="primary-btn">TIẾN HÀNH THANH TOÁN</a>' :
                            '<a href="signIn.jsp" class="primary-btn">Vui lòng đăng nhập để thanh toán</a>'}
                    </div>
                </div>
            </div>`;

            cartContainer.innerHTML = cartHTML;

            // Gán sự kiện cho các nút tăng/giảm/xóa
            attachCartEvents();

        } catch (error) {
            console.error('Error fetching cart:', error);
            document.getElementById('cart-container').innerHTML = '<h3>Không thể tải giỏ hàng.</h3>';
        }
    }

    // Hàm xử lý tăng/giảm số lượng và xóa sản phẩm
    function attachCartEvents() {
        document.querySelectorAll('.update-cart-btn').forEach(btn => {
            btn.addEventListener('click', async () => {
                const action = btn.getAttribute('data-action');
                const productId = btn.getAttribute('data-id');
                await updateCart(productId, action);
            });
        });

        document.querySelectorAll('.delete-cart-btn').forEach(btn => {
            btn.addEventListener('click', async () => {
                const productId = btn.getAttribute('data-id');
                await deleteFromCart(productId);
            });
        });
    }

    // Gọi API cập nhật số lượng
    async function updateCart(productId, action) {
        try {
            await fetch(`${pageContext.request.contextPath}/api/cart/update`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ productId, action })
            });
            fetchCart(); // Tải lại giỏ hàng
        } catch (error) {
            console.error('Error updating cart:', error);
        }
    }

    // Gọi API xóa sản phẩm khỏi giỏ hàng
    async function deleteFromCart(productId) {
        try {
            await fetch(`${pageContext.request.contextPath}/api/cart/delete`, {
                method: 'DELETE',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ productId })
            });
            fetchCart(); // Tải lại giỏ hàng
        } catch (error) {
            console.error('Error deleting item from cart:', error);
        }
    }

    fetchCart();

</script>

</body>

</html>