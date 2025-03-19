<%--
  Created by IntelliJ IDEA.
  User: DINHTUNG
  Date: 30/11/2023
  Time: 8:49 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.finallaptrinhweb.model.Product" %>
<%@ page import="com.example.finallaptrinhweb.dao.ProductDAO" %>
<%@ page import="com.example.finallaptrinhweb.model.Product" %>
<%@ page import="com.example.finallaptrinhweb.model.Util" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/products/styles.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <title>Sản phẩm</title>
</head>

<body>
<div class="website-wrapper">
    <jsp:include page="header.jsp"/>

    <div class="body">
        <div class="page-title" style="
            background-image: url(https://tienthangvet.vn/wp-content/uploads/title-tag-tien-thang-vet-tsd1.jpg);
          ">
<%--            <div class="container">--%>
<%--                <h1 class="title">--%>
<%--                    &lt;%&ndash; Hiển thị thông báo tùy thuộc vào biến từ khóa tìm kiếm &ndash;%&gt;--%>
<%--                    <% String searchTerm = (String) request.getAttribute("searchTerm"); %>--%>
<%--                    <% if (searchTerm != null && !searchTerm.isEmpty()) { %>--%>
<%--                    Kết quả tìm kiếm cho: <%= searchTerm %>--%>
<%--                    <% } else { %>--%>
<%--                    Sản phẩm--%>
<%--                    <% } %>--%>
<%--                </h1>--%>
<%--            </div>--%>
        </div>
        <div class="container">
            <div class="columns">
                <!-- Sidebar -->
                <aside class="sidebar">
                    <!-- Danh mục sản phẩm -->
                    <div class="widget-area">
                        <div id="categories-1" class="widget">
                            <span class="widget-title">Danh mục sản phẩm</span>
                            <ul class="wd-swatches-filter wd-filter-list wd-labels-on wd-size-normal wd-layout-list wd-text-style-1 wd-bg-style-4 wd-shape-round wd-scroll-content">
                                <c:forEach var="group" items="${groups}">
                                    <li class="wc-layered-nav-term wd-swatch-wrap">
                                        <a href="products?group=${group.key}" class="layered-nav-link">
                                            <span class="wd-swatch wd-bg"></span>
                                            <span class="wd-filter-lable layer-term-lable">${group.key}</span>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                        <div id="categories-2" class="widget">
                            <span class="widget-title">Nhóm sản phẩm</span>
                            <div class="wd-scroll" style="max-height: 280px;overflow: auto;">
                                <ul class="wd-swatches-filter wd-filter-list wd-labels-on wd-size-normal wd-layout-list wd-text-style-1 wd-bg-style-4 wd-shape-round wd-scroll-content">
                                    <c:forEach var="object" items="${objects}">
                                        <li class="wc-layered-nav-term">
                                            <a rel="nofollow noopener"
                                               href="products?category=${object.key}"
                                               class="layered-nav-link">
                                                <span class="wd-filter-lable layer-term-lable">${object.key}</span>
                                            </a>
                                            <span class="count">${object.value}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>

                        <div id="categories-3" class="widget">
                            <span class="widget-title">Lọc theo đối tượng</span>
                            <div class="wd-scroll" style="max-height: 280px;overflow: auto;">
                                <ul class="wd-swatches-filter wd-filter-list wd-labels-on wd-size-normal wd-layout-list wd-text-style-1 wd-bg-style-4 wd-shape-round wd-scroll-content">
                                    <c:forEach var="type" items="${proTypes}">
                                        <li class="wc-layered-nav-term">
                                            <a rel="nofollow noopener"
                                               href="products?type=${type.key}"
                                               class="layered-nav-link">
                                                <span class="wd-filter-lable layer-term-lable">${type.key}</span>
                                            </a>
                                            <span class="count">${type.value}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </aside>
                <!-- Content -->
                <div class="content">
                    <!-- Breadcrumbs -->
                    <div class="shop-loop-head">
                        <div class="wd-shop-tools">
                            <div class="wd-breadcrumbs">
                                <nav class="woocommerce-breadcrumb">
                                    <a href="" class="breadcrumb-link">Trang chủ</a>
                                    <a href="" class="breadcrumb-link">Sản phẩm</a>

                                    <c:choose>
                                        <c:when test="${isFilteringByGroup}">
                                            <a href="" class="breadcrumb-link">Đã lọc theo danh mục sản phẩm</a>
                                        </c:when>
                                        <c:when test="${not empty filteredProducts}">
                                            <a href="" class="breadcrumb-link">Đã lọc theo đối
                                                tượng: ${filteredProducts[0].productType}</a>
                                        </c:when>
                                        <c:when test="${not empty selectedCategory}">
                                            <a href="" class="breadcrumb-link"> ${selectedCategory}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <% String url = (String) request.getAttribute("url"); %>
                                            <% if (url != null && !url.isEmpty()) { %>
                                            <a href="" class="breadcrumb-link">Kết quả tìm kiếm cho: <%= url %>
                                            </a>
                                            <% } %>
                                        </c:otherwise>
                                    </c:choose>
                                </nav>
                            </div>
                        </div>
                    </div>

                    <!-- Danh sách sản phẩm -->
                    <div class="wrapper-container">
                        <div class="container">
                            <c:choose>
                                <c:when test="${not empty noProductsFound}">
                                    <div class="no-products-found">
                                        <p>Xin lỗi, không tìm thấy sản phẩm nào có tên này.</p>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="product" items="${product}">
                                        <div class="item">
                                            <!-- Hiển thị thông tin sản phẩm -->
                                            <div>
                                                <div class="product-element-top">
                                                    <a href="${pageContext.request.contextPath}/user/product?id=${product.id}">
                                                        <img src="${pageContext.request.contextPath}/${product.imageUrl}"
                                                             alt="">
                                                    </a>
                                                </div>
                                                <div class="product-element-bottom">
                                                    <a href="${pageContext.request.contextPath}/user/product?id=${product.id}">
                                                            ${product.productName}
                                                    </a>
                                                </div>
                                                <div class="product-element">
                                                    <div class="price-wrap">
                                                        <div class="price">${Util.formatCurrency(product.price)}</div>
                                                        <div class="unit">VND</div>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="wd-buttons wd-pos-r-t">
                                                <div class="wd-add-btn wd-action-btn wd-style-icon wd-add-cart-icon">
                                                    <a href="addtocart?id=${product.id}" class="button product_type_simple add-to-cart-loop" aria-label="">
                                                        <span>
                                                          <i class="fa-solid fa-cart-shopping"></i>
                                                        </span>
                                                    </a>
                                                </div>
                                                <div class="quick-view wd-action-btn wd-style-icon wd-quick-view-icon">
                                                    <a href="" class="open-quick-view quick-view-button">
                                                        <span>
                                                          <i class="fa-solid fa-magnifying-glass"></i>
                                                        </span>
                                                    </a>
                                                </div>
                                                <div class="wd-wishlist-btn wd-action-btn wd-style-icon wd-wishlist-icon">
                                                    <a class="wd-tltp wd-tooltip-inited wishlist-btn" data-product-id="${product.id}" data-added-text="Browse Wishlist">
                                                        <span class="wd-tooltip-label">
                                                            <c:choose>
                                                                <c:when test="${wishlistProductIds != null && wishlistProductIds.contains(product.id)}">
                                                                    <i class="fa-solid fa-heart" style="color: red"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <i class="fa-regular fa-heart"></i>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <!-- Phân trang -->
                    <!-- Phân trang -->
                    <div class="pagination">
                        <ul class="pagination-wrapper">

                            <c:if test="${currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/user/products?page=${currentPage - 1}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${currentPage >= 4 && totalPages > 15}">
                                <li class="page-item disabled">
                                    <span class="page-link ellipsis">...</span>
                                </li>
                            </c:if>

                            <!-- Hiển thị các trang -->
                            <c:forEach begin="${currentPage - 2 > 0 ? currentPage - 2 : 1}"
                                       end="${currentPage + 2 <= totalPages ? currentPage + 2 : totalPages}" var="page">
                                <li class="page-item ${page == currentPage ? 'active' : ''}">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/user/products?page=${page}">${page}</a>
                                </li>
                            </c:forEach>

                            <c:if test="${currentPage + 2 < totalPages && totalPages > 15}">
                                <li class="page-item disabled">
                                    <span class="page-link ellipsis">...</span>
                                </li>
                            </c:if>

                            <c:if test="${currentPage < totalPages}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/user/products?page=${currentPage + 1}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
    <script src="../"></script>
    <script>
        window.addEventListener("scroll", () => {
            var header = document.querySelector(".container");
            header.classList.toggle("sticky", window.scrollY > 100);
        })
    </script>


    <%--DUOC - check ajax thêm xóa sản phẩm khỏi wishlist--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        $(document).ready(function () {
            $(".wishlist-btn").click(function () {
                let productId = $(this).data("product-id");
                let icon = $(this).find("i");
                let isInWishlist = icon.hasClass("fa-solid");

                Swal.fire({
                    title: isInWishlist ? "Bạn có chắc muốn xóa?" : "Thêm vào danh sách yêu thích?",
                    text: isInWishlist ? "Sản phẩm sẽ bị xóa khỏi wishlist!" : "Sản phẩm sẽ được thêm vào wishlist!",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: isInWishlist ? "#d33" : "#3085d6",
                    cancelButtonColor: "#3085d6",
                    confirmButtonText: isInWishlist ? "Xóa" : "Thêm",
                    cancelButtonText: "Hủy"
                }).then((result) => {
                    if (result.isConfirmed) {
                        $.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath}/user/wishlist",
                            data: {productId: productId},
                            success: function (response) {
                                if (response.status === "success") {
                                    if (response.action === "added") {
                                        icon.removeClass("fa-regular").addClass("fa-solid").css("color", "red");
                                    } else {
                                        icon.removeClass("fa-solid").addClass("fa-regular").css("color", "black");
                                    }
                                    Swal.fire({
                                        title: response.action === "added" ? "Đã thêm!" : "Đã xóa!",
                                        text: response.action === "added" ? "Sản phẩm đã được thêm vào wishlist." : "Sản phẩm đã bị xóa khỏi wishlist.",
                                        icon: "success",
                                        timer: 1500,
                                        showConfirmButton: false
                                    });
                                }
                            },
                            error: function () {
                                Swal.fire("Lỗi!", "Cần đăng nhập để thực hiện dịch vụ", "error");
                            }
                        });
                    }
                });
            });
        });
    </script>
</body>
<style>
    span.page-link.ellipsis {
        text-decoration: none;
        border: 1px solid #dee2e6;
        padding: 6px 12px;
    }
</style>

</html>