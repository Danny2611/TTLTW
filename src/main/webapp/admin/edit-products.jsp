<%--
  Created by IntelliJ IDEA.
  User: DINHTUNG
  Date: 17/01/2024
  Time: 4:14 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.finallaptrinhweb.model.ProductGroups" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi-VN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>Sửa sản phẩm</title>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">

    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">

    <!-- Animate CSS -->
    <link rel="stylesheet" href="assets/css/animate.min.css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="assets/css/admin.css">

    <!-- Select CSS -->
    <link rel="stylesheet" href="assets/css/select2.min.css">
</head>

<body>
<div class="main-wrapper">

    <jsp:include page="menu.jsp"></jsp:include>

    <div class="page-wrapper">
        <div class="content container-fluid">
            <div class="row">
                <div class="col-xl-8 offset-xl-2">

                    <!-- Page Header -->
                    <div class="page-header">
                        <div class="row">
                            <div class="col">
                                <h3 class="page-title">${title}</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="product">Sản phẩm</a></li>
                                    <li class="breadcrumb-item active">${title}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /Page Header -->
                    <div class="card">
                        <div class="card-body">
                            <form action="edit-product?type=edit&id=${product.id}" method="post">
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2 table-nowrap">Mã sản phẩm</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="productId" value="${product.id}" readonly>
                                    </div>
                                </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2 table-nowrap">Tên sản phẩm</label>
                                        <div class="col-md-10">
                                            <input name="productName" type="text" class="form-control"
                                                   value="${product.productName}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Danh mục</label>
                                        <div class="col-md-10">
                                            <input name="categoryId" type="number" class="form-control"
                                                   value="${product.categoryId}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Giá</label>
                                        <div class="col-md-10">
                                            <input name="price" type="text" class="form-control"
                                                   value="${product.price}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Số Lượng</label>
                                        <div class="col-md-10">
                                            <input name="quantity" type="number" class="form-control"
                                                   value="${product.quantity}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Công dụng</label>
                                        <div class="col-md-10">
                                            <input name="purpose" type="text" class="form-control"
                                                   value="${product.purpose}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Chỉ định</label>
                                        <div class="col-md-10">
                                            <input name="contraindications" type="text" class="form-control"
                                                   value="${product.contraindications}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Tồn</label>
                                        <div class="col-md-10">
                                            <input name="stockQuantity" type="text" class="form-control"
                                                   value="${product.stockQuantity}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Thành phần</label>
                                        <div class="col-md-10">
                                            <input name="ingredients" type="text" class="form-control"
                                                   value="${product.ingredients}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Liều lượng</label>
                                        <div class="col-md-10">
                                            <input name="dosage" type="text" class="form-control"
                                                   value="${product.dosage}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Hướng dẫn</label>
                                        <div class="col-md-10">
                                            <input name="instructions" type="text" class="form-control"
                                                   value="${product.instructions}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2 table-nowrap">Thời hạn</label>
                                        <div class="col-md-10">
                                            <input name="warrantyPeriod" type="text" class="form-control"
                                                   value="${product.warrantyPeriod}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Đối tượng</label>
                                        <div class="col-md-10">
                                            <input name="productType" type="text" class="form-control"
                                                   value="${product.productType}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Bảo quản</label>
                                        <div class="col-md-10">
                                            <input name="storageCondition" type="text" class="form-control"
                                                   value="${product.storageCondition}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2 table-nowrap">Nhà cung cấp</label>
                                        <div class="col-md-10">
                                            <input name="supplierId" type="text" class="form-control"
                                                   value="${product.supplierId}">
                                        </div>
                                    </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Ảnh sản phẩm</label>
                                    <div class="col-md-10">
                                        <label for="image" style="cursor: pointer;">
                                            <!-- Hiển thị ảnh hiện tại -->
                                            <img src="${product.imageUrl}" alt="Ảnh sản phẩm" style="max-height: 150px; border: 1px solid #ddd;">
                                        </label>
                                        <input type="file" id="image" name="image" class="form-control" style="display: none;">
                                        <input type="hidden" name="currentImageUrl" value="${product.imageUrl}">
                                    </div>
                                </div>

                                <div class="mt-4">
                                        <button class="btn btn-primary">Lưu thay đổi</button>
                                        <a href="product" class="btn btn-link">Hủy</a>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="assets/js/jquery-3.5.0.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="assets/js/popper.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

<!-- Select2 JS -->
<script src="assets/js/select2.min.js"></script>

<!-- Custom JS -->
<script src="assets/js/admin.js"></script>

</body>

</html>
