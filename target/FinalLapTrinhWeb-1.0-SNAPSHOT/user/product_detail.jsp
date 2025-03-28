<%--
  Created by IntelliJ IDEA.
  User: DINHTUNG
  Date: 01/12/2023
  Time: 3:57 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.finallaptrinhweb.model.Util" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/products/detailproduct.css"/>

    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <title>Chi tiết sản phẩm</title>
<%--    internal css--%>
    <style>
        .comment{
            display: flex;
            gap: 8px;
            align-items: center;
        }
        .mb-5 {
            margin-bottom: 5px;
        }
        .d-flex{
            display: flex;
        }
        .flex-column {
            flex-direction: column;
        }
        .text-yl {
            color:#EAB308;
        }
        .btn-comment {
            background-color: #66b840;
            color: white;
            border: none;
            outline: none;
            height: 46px;
            border-radius: 2px;
            text-align: center;
            padding: 0 30px;
        }
        .w-50{
            width: 50%;
        }
        .flex-1{
            flex: 1;
        }
        .w-500px{
            width: 500px;
        }
        .p-3{
            padding: 3px;
        }
        .h-120px{
            height: 120px;
        }
        .label-comment {
            font-size: 14px;
            margin-bottom:5px;
            display: block;
        }
        .stars {
            display: flex;
            gap: 5px;
            cursor: pointer;
        }

        .fa-star {
            color: rgb(128, 128, 128); /* Màu mặc định */
        }

        .fa-star.selected {
            color: gold; /* Màu vàng khi được chọn */
        }
    </style>
</head>

<body>
<div class="website-wrapper">
    <jsp:include page="header.jsp"/>

    <div class="body">
        <div class="wrapper-content">
            <div class="content">
                <div class="single-breadcrumbs-wrapper">
                    <div class="container">
                        <div class="wd-breadcrumbs">
                            <nav class="woocommerce-breadcrumb">
                                <a href="" class="breadcrumb-link">
                                    Trang chủ
                                </a>
                                <a href="" class="breadcrumb-link">
                                    Sản phẩm
                                </a>
                                <span class="breadcrumb-last">
                                      <span class="breadcrumb-last"> ${product.productName}</span>
                                    </span>
                            </nav>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="product-images">
                        <div class="product-images-inner">
                            <div class="col-12">
                                <div class="owl-stage">
                                    <div class="item" style="width: 575px; height: 575px">
                                        <div class="product-image-wrap">
                                            <img src="${pageContext.request.contextPath}/${listImg[0]}"
                                                 class="wp-post-image wp-post-image"/>
                                        </div>
                                    </div>
                                    <div class="product-additional-galleries">
                                        <a id="see-more"
                                           href="${pageContext.request.contextPath}/${listImg[0]}"
                                           class="woodmart-show-product-gallery">
                                            <i class="fa-solid fa-compress"></i>
                                            <span class="see-more">Click to enlarge</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="owl-stage-outer" style="margin-top: 10px">
                                    <div class="owl-stage">
                                        <c:forEach var="url" items="${listImg}">
                                            <div class="item" style="width: 145.25px">
                                                <div class="product-image-thumbnail">
                                                    <img width="150" height="150"
                                                         src="${pageContext.request.contextPath}/${url}"/>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="detail-product">
                        <div class="summary-inner set-mb-l reset-last-child">
                            <div class="wd-product-brands">
                                <a href="">
                                    <img src="${supplierImgUrl}"
                                         alt="Supplier Image"/>
                                </a>
                            </div>
                            <h1 class="product_title entry-title wd-entities-title">
                                ${product.productName}
                            </h1>
                            <div class="price-wrapper">
                                <div class="price">${Util.formatCurrency(product.price)}</div>
                                <div class="unit">VND</div>
                            </div>
                            <div
                                    class="wd-compare-btn product-compare-button wd-action-btn wd-style-text wd-compare-icon">
                                <a href="">
                                    <i class="fa-solid fa-shuffle"></i>
                                    <span>So sánh</span>
                                </a>
                            </div>
                            <div class="wd-wishlist-btn wd-action-btn wd-style-text wd-wishlist-icon">
                                <a class="wishlist-btn" data-product-id="${product.id}">
                                    <c:choose>
                                        <c:when test="${wishlistProductIds != null && wishlistProductIds.contains(product.id)}">
                                            <i class="fa-solid fa-heart" style="color: red"></i>
                                            <span style="color: red">Yêu thích</span>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fa-regular fa-heart"></i>
                                            <span style="color: black">Yêu thích</span>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                            <div class="product_meta">
                                    <span class="posted_in"><span class="meta-label">Danh mục:</span>
                                        <a href="" rel="tag">Thuốc
                                            thú y</a></span>
                            </div>
                            <div class="container">
                                <a style="color: #fff;" href="addtocart?id=${product.id}">
                                    <button class="add-to-cart-button">
                                        <svg class="add-to-cart-box box-1" width="24" height="24" viewBox="0 0 24 24"
                                             fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <rect width="24" height="24" rx="2" fill="#ffffff"/>
                                        </svg>
                                        <svg class="add-to-cart-box box-2" width="24" height="24" viewBox="0 0 24 24"
                                             fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <rect width="24" height="24" rx="2" fill="#ffffff"/>
                                        </svg>
                                        <svg class="cart-icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                             viewBox="0 0 24 24" fill="none" stroke="#ffffff" stroke-width="2"
                                             stroke-linecap="round" stroke-linejoin="round">
                                            <circle cx="9" cy="21" r="1"></circle>
                                            <circle cx="20" cy="21" r="1"></circle>
                                            <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6">
                                            </path>
                                        </svg>
                                        <svg class="tick" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                             viewBox="0 0 24 24">
                                            <path fill="none" d="M0 0h24v24H0V0z"/>
                                            <path fill="#ffffff"
                                                  d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zM9.29 16.29L5.7 12.7c-.39-.39-.39-1.02 0-1.41.39-.39 1.02-.39 1.41 0L10 14.17l6.88-6.88c.39-.39 1.02-.39 1.41 0 .39.39.39 1.02 0 1.41l-7.59 7.59c-.38.39-1.02.39-1.41 0z"/>
                                        </svg>
                                        <span class="add-to-cart">Thêm vào giỏ hàng</span>
                                        <span class="added-to-cart">Đã thêm</span>
                                    </button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="wrapper-content">
            <div class="product-tabs-wrapper">
                <div class="container">
                    <div class="poduct-tabs-inner">
                        <div class="woocommerce-tabs wc-tabs-wrapper tabs-layout-tabs" data-state="first"
                             data-layout="tabs">
                            <div class="wd-nav-wrapper wd-nav-tabs-wrapper text-center">
                                <ul class="wd-nav wd-nav-tabs wd-icon-pos-left tabs wc-tabs wd-style-underline-reverse"
                                    role="tablist">
                                    <li class="description_tab active" id="tab-title-description" role="tab"
                                        aria-controls="tab-description">
                                        <a class="wd-nav-link" href="#tab-description">
                                                <span class="nav-link-text wd-tabs-title">
                                                    Mô tả
                                                </span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="wd-accordion-item">
                                <div class="entry-content woocommerce-Tabs-panel woocommerce-Tabs-panel--description wd-active panel wc-tab"
                                     id="tab-description" role="tabpanel" aria-labelledby="tab-title-description"
                                     data-accordion-index="description" style="display: block">
                                    <div class="wc-tab-inner">
                                        <div data-elementor-type="product-post" data-elementor-id="37778"
                                             class="elementor elementor-37778">
                                            <section
                                                    class="wd-negative-gap elementor-section elementor-top-section elementor-element elementor-element-493d2218 elementor-section-boxed elementor-section-height-default elementor-section-height-default wd-section-disabled"
                                                    data-id="493d2218" data-element_type="section">
                                                <div class="elementor-container elementor-column-gap-default">
                                                    <div class="elementor-column elementor-col-100 elementor-top-column elementor-element elementor-element-56ddef75"
                                                         data-id="56ddef75" data-element_type="column">
                                                        <div
                                                                class="elementor-widget-wrap elementor-element-populated">
                                                            <div class="elementor-element elementor-element-1400a071 color-scheme-inherit text-left elementor-widget elementor-widget-text-editor"
                                                                 data-id="1400a071" data-element_type="widget"
                                                                 data-widget_type="text-editor.default">
                                                                <div class="elementor-widget-container">
                                                                    <h1>Công dụng:</h1>
                                                                    <p>
                                                                        ${product.purpose}
                                                                    </p>
                                                                    <h1>Thành phần:</h1>
                                                                    <p>${product.ingredients}</p>
                                                                    <h2>Liều lượng: ${product.dosage} </h2>
                                                                    <h2>Hướng dẫn sử dụng:</h2>
                                                                    <p>
                                                                        ${product.instructions}
                                                                    </p>
                                                                    <h2>Đối tượng: ${product.productType} </h2>
                                                                    <h2>CHỐNG CHỈ ĐỊNH:</h2>
                                                                    <p>
                                                                         ${product.contraindications}<br/>
                                                                    </p>
                                                                    <h2>
                                                                        BẢO QUẢN<br/> ${product.storageCondition}<br/>
                                                                    </h2>
                                                                    <h2>Hạn sử dụng: ${product.warrantyPeriod}</h2>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="wrapper-content">
            <div class="container related-and-upsells">
                <h3 class="title slider-title">Bình luận về sản phẩm</h3>
                <div class="d-flex">

                    <div class="flex-1">
                        <div class="comment mb-5">
                            <div class="image bg-secondary rounded-circle"
                                 style="width: 50px; height: 50px;
               background-image: url('https://images.unsplash.com/photo-1528287942171-fbe365d1d9ac?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&w=1200&cs=srgb&ixid=eyJhcHBfaWQiOjE0NTg5fQ');
               background-size: cover; background-position: center;">
                            </div>
                            <div class="d-flex flex-column gap-2 ms-3">
                                <h4 class="fs-4 fw-bold mb-5">Đặng Hữu Quý</h4>
                                <div>
                                    <i class="fa fa-star text-warning"></i>
                                    <i class="fa fa-star text-warning"></i>
                                    <i class="fa fa-star text-warning"></i>
                                    <i class="fa fa-star text-warning"></i>
                                    <i class="fa fa-star text-warning"></i>
                                </div>
                                <p>2-12-2025</p>
                                <p class="mb-0">Sản phẩm rất tốt</p>
                            </div>
                        </div>
                        <c:forEach var="commnent" items="${comments}">
                            <div class="comment mb-5">
                                <div class="image bg-secondary rounded-circle"
                                     style="width: 50px; height: 50px;
               background-image: url('https://images.unsplash.com/photo-1528287942171-fbe365d1d9ac?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&w=1200&cs=srgb&ixid=eyJhcHBfaWQiOjE0NTg5fQ');
               background-size: cover; background-position: center;">
                                </div>
                                <div class="d-flex flex-column gap-2 ms-3">
                                    <h4 class="fs-4 fw-bold mb-5">${commnent.name}</h4>
                                    <div>
                                        <c:forEach var="i" begin="1" end="${commnent.star}">
                                            <i class="fa fa-star text-yl"></i>
                                        </c:forEach>
                                    </div>
                                    <p>${commnent.createdAt}</p>
                                    <p class="comment-content mb-0">${commnent.content}</p>

                                    <c:if test="${sessionScope.auth.id == commnent.userId}">
                                        <p
                                           onclick="editComment(this,'${commnent.id}')"
                                           style="cursor: pointer; text-align: right;color: #0e3cc8;" class="change-content">Chỉnh sửa ${commnent.id}
                                        </p>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                    <div class="w-50">
                        <c:if test="${isBought}">
                            <form id="form-comment" method="post" action="./comment">
                                <div>
                                    <label for="content" class="label-comment">Bình luận về sản phẩm</label>
                                    <textarea   name="content"  class="p-3 h-120px w-500px" id="content"></textarea>
                                    <input name="star-value" type="hidden" value="0" id="star-value"/>
                                    <input name="productId" type="hidden" value=${product.id} />
                                </div>
                                <div class="stars">
                                    <!-- Tạo 5 sao -->
                                    <i class="fa fa-star star" data-value="1"></i>
                                    <i class="fa fa-star star" data-value="2"></i>
                                    <i class="fa fa-star star" data-value="3"></i>
                                    <i class="fa fa-star star" data-value="4"></i>
                                    <i class="fa fa-star star" data-value="5"></i>
                                </div>

                                <button style="margin-top: 10px" class="btn-comment" type="submit">Bình luận</button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>
        <div class="wrapper-content">
            <div class="container related-and-upsells">
                <div class="related-products">
                    <h3 class="title slider-title">Có thể bạn cũng thích</h3>
                    <div class="products">
                        <div class="wrapper-container">
                            <div class="container">
                                <c:forEach var="similarProduct" items="${similarProducts}">
                                    <div class="item">
                                        <div>
                                            <div class="product-element-top">
                                                <a href="${pageContext.request.contextPath}/user/product?id=${similarProduct.id}">
                                                    <img src="${pageContext.request.contextPath}/${similarProduct.imageUrl}"
                                                         alt="">
                                                </a>
                                            </div>
                                            <div class="product-element-bottom">
                                                <a href="${pageContext.request.contextPath}/user/product?id=${similarProduct.id}">
                                                        ${similarProduct.productName}
                                                </a>
                                            </div>
                                            <div class="product-element">
                                                <div class="price-wrap">
                                                    <div class="price">${Util.formatCurrency(similarProduct.price)}</div>
                                                    <div class="unit">VND</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wd-buttons wd-pos-r-t">
                                            <div class="wd-add-btn wd-action-btn wd-style-icon wd-add-cart-icon">
                                                <a href="${pageContext.request.contextPath}/user/addtocart?id=${similarProduct.id}" class="button product_type_simple add-to-cart-loop">
                                            <span>
                                                <i class="fa-solid fa-cart-shopping"></i>
                                            </span>
                                                </a>
                                            </div>
                                            <div class="quick-view wd-action-btn wd-style-icon wd-quick-view-icon">
                                                <a href="${pageContext.request.contextPath}/user/product?id=${similarProduct.id}" class="open-quick-view quick-view-button">
                                            <span>
                                                <i class="fa-solid fa-magnifying-glass"></i>
                                            </span>
                                                </a>
                                            </div>
                                            <div class="wd-wishlist-btn wd-action-btn wd-style-icon wd-wishlist-icon">
                                                <a class="wishlist-btn" data-product-id="${similarProduct.id}">
                                                    <c:choose>
                                                        <c:when test="${wishlistProductIds != null && wishlistProductIds.contains(similarProduct.id)}">
                                                            <i class="fa-solid fa-heart" style="color: red"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="fa-regular fa-heart"></i>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                                <c:if test="${empty similarProducts}">
                                    <div class="no-products-message">

                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>

<script src="js/detailProduct/scripts.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    $(document).ready(function () {
        $(".wishlist-btn").click(function () {
            let productId = $(this).data("product-id");
            let icon = $(this).find("i");
            let text = $(this).find("span");
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
                                    text.css("color", "red");
                                } else {
                                    icon.removeClass("fa-solid").addClass("fa-regular").removeAttr("style");
                                    text.removeAttr("style");
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

<script>
    const stars = document.querySelectorAll('.star');
    const input = document.getElementById("star-value");
    stars.forEach(star => {
        star.addEventListener('click', function() {
            const selectedValue = this.getAttribute('data-value');
            input.value= selectedValue;
            stars.forEach(star => {
                const starValue = star.getAttribute('data-value');
                if (starValue <= selectedValue) {
                    star.classList.add('selected');
                } else {
                    star.classList.remove('selected');
                }
            });
        });
    });
</script>
<script>
    const contentBox = document.getElementById("content") //gián dô thẻ textarae
    const form = document.getElementById("form-comment")

    const editComment = (el,commentId)=>{
        let commentParent = el.closest(".comment")
        let content = commentParent.querySelector(".comment-content").innerText
        contentBox.value=content
        let oldInput = document.getElementById("commentId");
        if (oldInput) {
            oldInput.remove();
        }

    //     create Input
        commentIdInput = document.createElement("input");
        commentIdInput.type = "hidden";
        commentIdInput.name = "commentId";
        commentIdInput.id = "commentId";
        commentIdInput.value = commentId;
        form.appendChild(commentIdInput);
        console.log("commentId" , commentId)
        form.action = `./comment/edit`;
    }

//


</script>
</body>

</html>