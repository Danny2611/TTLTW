<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.finallaptrinhweb.model.Util" %>
<html>
<head>
    <link rel="stylesheet" href="css/HomePage/styles.css"/>
    <link rel="stylesheet" href="css/wishList/wishList.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
          integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css"
          integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- Swiper CSS -->
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
    <title>WishList</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="body">
    <section id="section4">
        <div class="wrapper-content">
            <div class="wrapper-heading">
                <div class="heading">
                    <h1>SẢN PHẨM YÊU THÍCH</h1>
                </div>
            </div>
            <div class="wrapper-container">
                <div class="container wrap">
                    <c:choose>
                        <c:when test="${empty wishlist}">
                            <div class="no-products">
                                <i class="fa-solid fa-heart-crack"></i>
                                <p>Không có sản phẩm nào trong danh sách yêu thích.</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="p" items="${wishlist}">
                                <div class="item">
                                    <div>
                                        <div class="product-element-top">
                                            <a href="${pageContext.request.contextPath}/user/product?id=${p.id}">
                                                <img src="${pageContext.request.contextPath}/${p.imageUrl}"
                                                     alt="">
                                            </a>
                                        </div>
                                        <div class="product-element-bottom">
                                            <a href="${pageContext.request.contextPath}/user/product?id=${p.id}">
                                                    ${p.productName}
                                            </a>
                                        </div>
                                        <div class="product-element">
                                            <div class="price-wrap">
                                                <div class="price">${Util.formatCurrency(p.price)}</div>
                                                <div class="unit">VND</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="wd-buttons wd-pos-r-t">
                                        <div class="wd-add-btn wd-action-btn wd-style-icon wd-add-cart-icon">
                                            <a data-id="${p.id}" class="add-cart-btn button product_type_simple add-to-cart-loop" aria-label="">
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
                                            <button class="wishlist-remove-btn" data-product-id="${p.id}" style="background: none; border: none; cursor: pointer;">
                                        <span class="wd-tooltip-label">
                                            <i class="fa-solid fa-heart" style="color: red"></i>
                                        </span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>

    <section id="section10">
        <div class="product-recommendation">
            <h2 class="recommendation-title">Sản phẩm gợi ý</h2>
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <c:forEach var="entry" items="${productsByCategory}">
                        <c:forEach var="product" items="${entry.value}" varStatus="status" begin="0" end="9">
                            <div class="swiper-slide">
                                <div class="product-card">
                                    <a href="${pageContext.request.contextPath}/user/product?id=${product.id}">
                                        <img src="${pageContext.request.contextPath}/${product.imageUrl}" alt="${product.productName}" class="product-image">
                                    </a>
                                    <div class="product-info">
                                        <div class="product-name">${product.productName}</div>
                                        <div class="blockInfo" style="display: flex; margin-top: 30px">
                                            <div class="product-price">${Util.formatCurrency(product.price)} VND</div>
                                            <div class="product-actions">
                                                <a data-id="${product.id}" class="add-cart-btn button product_type_simple add-to-cart-loop">
                                                    <i class="fas fa-shopping-cart"></i>
                                                </a>
                                                <a class="wishlist-btnn" data-product-id="${product.id}">
                                                        <%--                                                <c:choose>--%>
                                                        <%--                                                    <c:when test="${wishlistIds != null && wishlistIds.contains(product.id)}">--%>
                                                        <%--                                                        <i class="fa-solid fa-heart" style="color: red"></i>--%>
                                                        <%--                                                    </c:when>--%>
                                                        <%--                                                    <c:otherwise>--%>
                                                        <%--                                                        <i class="fa-regular fa-heart"></i>--%>
                                                        <%--                                                    </c:otherwise>--%>
                                                        <%--                                                </c:choose>--%>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:forEach>
                </div>
                <div class="swiper-button-next" style="color: #d1d1b3; height: 50px; width: 50px; border-radius: 50%; font-size: 20px"></div>
                <div class="swiper-button-prev" style="color: #d1d1b3; height: 50px; width: 50px; border-radius: 50%; font-size: 20px"></div>
            </div>
        </div>
    </section>

</div>
<jsp:include page="footer.jsp"/>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script>
    const swiper = new Swiper('.swiper-container', {
        slidesPerView: 3,
        spaceBetween: 20,
        loop: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        breakpoints: {
            320: {
                slidesPerView: 1,
            },
            768: {
                slidesPerView: 2,
            },
            1024: {
                slidesPerView: 3,
            },
        },
    });
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    $(document).ready(function () {
        $(document).on("click", ".wishlist-remove-btn", function () {
            let productId = $(this).data("product-id");
            let button = $(this);

            Swal.fire({
                title: "Bạn có chắc muốn xóa?",
                text: "Sản phẩm sẽ bị xóa khỏi danh sách yêu thích!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#d33",
                cancelButtonColor: "#3085d6",
                confirmButtonText: "Xóa",
                cancelButtonText: "Hủy"
            }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/user/wishlist",
                        data: {productId: productId},
                        success: function (response) {
                            console.log("Xóa thành công:", response);
                            Swal.fire({
                                title: "Đã xóa!",
                                text: "Sản phẩm đã được xóa khỏi danh sách yêu thích.",
                                icon: "success",
                                timer: 1500,
                                showConfirmButton: false
                            });

                            // Xóa sản phẩm khỏi danh sách
                            button.closest(".item").fadeOut(300, function () {
                                $(this).remove();
                            });
                            reloadWishlistSwiper();
                        },
                        error: function (xhr, status, error) {
                            console.error("Lỗi khi xóa:", error);
                            Swal.fire("Lỗi!", "Không thể xóa sản phẩm. Vui lòng thử lại!", "error");
                        }
                    });
                }
            });
        });
    });

    function reloadWishlistSwiper() {
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/user/wishlist",
            success: function (data) {
                $(".wrapper-container").html($(data).find(".wrapper-container").html());
                let newContent = $(data).find(".swiper-wrapper").html(); // Lấy nội dung mới
                let swiperContainer = $(".swiper-container")[0].swiper; // Lấy Swiper instance

                if (swiperContainer) {
                    swiperContainer.destroy(true, true); // Hủy Swiper trước khi thay đổi nội dung
                }

                $(".swiper-wrapper").html(newContent); // Cập nhật nội dung mới

                // Khởi động lại Swiper sau khi cập nhật
                new Swiper(".swiper-container", {
                    slidesPerView: 3,
                    spaceBetween: 20,
                    navigation: {
                        nextEl: ".swiper-button-next",
                        prevEl: ".swiper-button-prev",
                    },
                    pagination: {
                        el: ".swiper-pagination",
                        clickable: true,
                    },
                    loop: true
                });
            },
            error: function () {
                console.error("Không thể tải danh sách yêu thích.");
            }
        });
    }


</script>
<script>
    $(document).ready(function () {
        $(".add-cart-btn").click(function () {
            let productId = $(this).data("id")
            console.log(productId)

            //     call api
            fetch(`${pageContext.request.contextPath}/api/cart`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ productId: productId })
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert("Thêm sản phẩm vào giỏ hàng thành công!");
                    } else {
                        alert("Lỗi: " + data.message);
                    }
                })
                .catch(error => console.error("Lỗi hệ thống:", error));

        })
    });
</script>
`
<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        // Xử lý thêm/xóa wishlist--%>
<%--        $(".wishlist-btnn").click(function () {--%>
<%--            let productId = $(this).data("product-id");--%>
<%--            let icon = $(this).find("i");--%>
<%--            let isInWishlist = icon.hasClass("fa-solid");--%>

<%--            Swal.fire({--%>
<%--                title: isInWishlist ? "Bạn có chắc muốn xóa?" : "Thêm vào danh sách yêu thích?",--%>
<%--                text: isInWishlist ? "Sản phẩm sẽ bị xóa khỏi wishlist!" : "Sản phẩm sẽ được thêm vào wishlist!",--%>
<%--                icon: "warning",--%>
<%--                showCancelButton: true,--%>
<%--                confirmButtonColor: isInWishlist ? "#d33" : "#3085d6",--%>
<%--                cancelButtonColor: "#3085d6",--%>
<%--                confirmButtonText: isInWishlist ? "Xóa" : "Thêm",--%>
<%--                cancelButtonText: "Hủy"--%>
<%--            }).then((result) => {--%>
<%--                if (result.isConfirmed) {--%>
<%--                    $.ajax({--%>
<%--                        type: "POST",--%>
<%--                        url: "${pageContext.request.contextPath}/user/wishlist",--%>
<%--                        data: { productId: productId },--%>
<%--                        success: function (response) {--%>
<%--                            if (response.status === "success") {--%>
<%--                                if (response.action === "added") {--%>
<%--                                    icon.removeClass("fa-regular").addClass("fa-solid").css("color", "red");--%>
<%--                                } else {--%>
<%--                                    icon.removeClass("fa-solid").addClass("fa-regular").css("color", "black");--%>
<%--                                    $(`.item[data-product-id="${productId}"]`).fadeOut(300, function () {--%>
<%--                                        $(this).remove();--%>
<%--                                    });--%>
<%--                                }--%>

<%--                                Swal.fire({--%>
<%--                                    title: response.action === "added" ? "Đã thêm!" : "Đã xóa!",--%>
<%--                                    text: response.action === "added" ? "Sản phẩm đã được thêm vào wishlist." : "Sản phẩm đã bị xóa khỏi wishlist.",--%>
<%--                                    icon: "success",--%>
<%--                                    timer: 1500,--%>
<%--                                    showConfirmButton: false--%>
<%--                                });--%>

<%--                                // Gọi hàm load lại danh sách wishlist--%>
<%--                                reloadWishlist();--%>
<%--                            }--%>
<%--                        },--%>
<%--                        error: function () {--%>
<%--                            Swal.fire("Lỗi!", "Cần đăng nhập để thực hiện dịch vụ", "error");--%>
<%--                        }--%>
<%--                    });--%>
<%--                }--%>
<%--            });--%>
<%--        });--%>

<%--        // Xử lý xóa wishlist ở danh sách yêu thích--%>
<%--        $(".wishlist-remove-btn").click(function () {--%>
<%--            let productId = $(this).data("product-id");--%>
<%--            let button = $(this);--%>

<%--            Swal.fire({--%>
<%--                title: "Bạn có chắc muốn xóa?",--%>
<%--                text: "Sản phẩm sẽ bị xóa khỏi danh sách yêu thích!",--%>
<%--                icon: "warning",--%>
<%--                showCancelButton: true,--%>
<%--                confirmButtonColor: "#d33",--%>
<%--                cancelButtonColor: "#3085d6",--%>
<%--                confirmButtonText: "Xóa",--%>
<%--                cancelButtonText: "Hủy"--%>
<%--            }).then((result) => {--%>
<%--                if (result.isConfirmed) {--%>
<%--                    $.ajax({--%>
<%--                        type: "POST",--%>
<%--                        url: "${pageContext.request.contextPath}/user/wishlist",--%>
<%--                        data: { productId: productId },--%>
<%--                        success: function (response) {--%>
<%--                            if (response.status === "success") {--%>
<%--                                Swal.fire({--%>
<%--                                    title: "Đã xóa!",--%>
<%--                                    text: "Sản phẩm đã được xóa khỏi danh sách yêu thích.",--%>
<%--                                    icon: "success",--%>
<%--                                    timer: 1500,--%>
<%--                                    showConfirmButton: false--%>
<%--                                });--%>

<%--                                button.closest(".item").fadeOut(300, function () {--%>
<%--                                    $(this).remove();--%>
<%--                                });--%>

<%--                                // Gọi hàm load lại danh sách wishlist--%>
<%--                                reloadWishlist();--%>
<%--                            }--%>
<%--                        },--%>
<%--                        error: function (xhr, status, error) {--%>
<%--                            console.error("Lỗi khi xóa:", error);--%>
<%--                            Swal.fire("Lỗi!", "Không thể xóa sản phẩm. Vui lòng thử lại!", "error");--%>
<%--                        }--%>
<%--                    });--%>
<%--                }--%>
<%--            });--%>
<%--        });--%>

<%--        // Hàm load lại danh sách wishlist mà không cần reload trang--%>
<%--        function reloadWishlist() {--%>
<%--            $.ajax({--%>
<%--                type: "GET",--%>
<%--                url: "${pageContext.request.contextPath}/user/wishlist",--%>
<%--                success: function (data) {--%>
<%--                    $(".wrapper-container").html($(data).find(".wrapper-container").html()); // Chỉ thay nội dung wishlist--%>
<%--                },--%>
<%--                error: function () {--%>
<%--                    console.error("Không thể tải danh sách yêu thích.");--%>
<%--                }--%>
<%--            });--%>
<%--        }--%>


<%--    });--%>

<%--</script>--%>
</body>
</html>
