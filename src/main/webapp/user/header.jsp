<%@ page import="com.example.finallaptrinhweb.model.User" %>
<%@ page import="com.example.finallaptrinhweb.model.Cart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% User user = (User) session.getAttribute("auth");%>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link rel="stylesheet" href="css/header&footer.css">
</head>
<header class="header">
    <div class="container">
        <div class="header-left">
            <a href="home">
                <img src="https://tienthangvet.vn/wp-content/uploads/logo-tien-thang-vet.jpg" alt=""/>
            </a>
        </div>
        <div class="header-center">
            <div class="header-nav" role="navigation" aria-label="Main navigation">
                <ul class="menu">
                    <li class="menu-item">
                        <a href="home"><span class="nav-link-text">Thuốc Y The Pet</span></a>
                    </li>
                    <li class="menu-item">
                        <a href="./introduce.jsp"><span class="nav-link-text">Giới thiệu</span></a>
                    </li>
                    <li class="menu-item">
                        <a href="products"><span class="nav-link-text">Sản phẩm</span></a>
                        <div class="container">
                            <ul class="sub-menu">
                                <li class="menu-item">
                                    <a href="products?group=Thức%20ăn%20chăn%20nuôi">Thức ăn chăn nuôi</a>
                                </li>
                                <li class="menu-item">
                                    <a href="products?group=Chăm%20sóc%20thú%20cưng">Chăm sóc thú cưng</a>
                                </li>
                                <li class="menu-item">
                                    <a href="products?group=Thuốc%20thú%20y">Thuốc thú y<i class="fa-solid fa-angle-right"></i></a>
                                    <ul class="sub-sub-menu">
                                        <li class="menu-item">
                                            <a href="products?category=Kháng%20sinh">Thuốc kháng sinh</a>
                                        </li>
                                        <li class="menu-item">
                                            <a href="products?category=Sát%20trùng">Thuốc sát trùng</a>
                                        </li>
                                        <li class="menu-item">
                                            <a href="products?category=Vắc%20xin">Vắc xin phòng bệnh</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="menu-item">
                        <a href="feedback"><span class="nav-link-text">Liên hệ</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="header-right">
            <form role="search" method="get" class="searchform" action=products?action=search data-thumbnail="1" data-price="1"
                  data-post_type="product" data-count="20" data-sku="0" data-symbols_count="3">
                <input type="text" id="searchTerm" name="searchTerm" class="s" placeholder="Tìm kiếm sản phẩm" value="" aria-label="Search"
                       title="Search for products" required=""/>
                <input type="hidden" name="post_type" value="product"/>
                <button type="submit" class="searchsubmit">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>
            <div class="action">
                <div class="cart" style="margin: 0 30px 0 30px;">
                    <%
                        Cart cart = (Cart) session.getAttribute("cart");
                        if (cart != null) {
                    %>
                    <span class="count"><%=cart.getTotalQuantity()%></span>
                    <%}%>
                    <a href="cart">
                        <i class="fa-solid fa-cart-shopping material-icons"></i>
                    </a>
                </div>
                <%if (user == null) { %>
                <a class="sign-in" href="signIn.jsp">Đăng nhập</a>
                <%} else {%>
                <div class="user-dropdown">
                    <i class="fas fa-user fa-2x" style="color: #66b840" id="user-icon"></i>
                    <div class="user-dropdown-content" id="user-dropdown-content">
                        <ul class="user-menu">
                            <li class="user-menu-item">
                                <a href="updateinfouser"><span class="nav-link-text">Quản lý thông tin cá
                                                nhân</span></a>
                            </li>
                            <li class="user-menu-item">
                                <a href="signout"><span class="nav-link-text">Đăng xuất</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </div>

</header>


