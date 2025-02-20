<%@ page import="java.util.Date" %>
<%@ page import="com.example.finallaptrinhweb.model.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi-VN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>${title}</title>
    <link href="assets/img/icon/icon-logo.png" rel="shortcut icon">
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
                                    <li class="breadcrumb-item"><a href="coupon-code">Mã giảm giá</a></li>
                                    <li class="breadcrumb-item active">${title}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /Page Header -->

                    <div class="card">
                        <div class="card-body">

                            <!-- Form -->
                            <c:if test="${type.equals('add')}">
                                <form action="add-coupon?action=add" method="post" accept-charset="UTF-8">
                                    <div class="form-group" style="display: none">
                                        <input class="form-control" type="text" value="add" name="type" required>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Tên</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="name" required>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Phần trăm khuyến mãi</label>
                                        <div class="col-md-10">
                                            <input type="number" class="form-control" min="0" max="100" name="percent" required>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Ngày hết hạn</label>
                                        <div class="col-md-10">
                                            <input type="date" class="form-control" name="date_end" required>
                                        </div>
                                    </div>
                                    <div class="mt-4">
                                        <button class="btn btn-primary">Lưu</button>
                                        <a href="coupon-code" class="btn btn-link">Hủy</a>
                                    </div>
                                </form>
                            </c:if>
                            <!-- /Form -->

                            <!-- Form -->
                            <c:if test="${type.equals('edit')}">
                            <form action="add-coupon?action=edit&id=${discount.id}" method="post">
                                <div class="form-group" style="display: none">
                                    <input class="form-control" type="text" value="add" name="type" required>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Tên</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="name" value="${discount.discountType}" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Phần trăm khuyến mãi</label>
                                    <div class="col-md-10">
                                        <input type="number" class="form-control" name="percent" min="0" max="100" value="${discount.discountValue}" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Ngày hết hạn</label>
                                    <div class="col-md-10">
                                        <input type="date" class="form-control" name="date_end" value="${discount.expirationDate}" required>
                                    </div>
                                </div>
                                <div class="mt-4">
                                    <button class="btn btn-primary">Lưu</button>
                                    <a href="coupon-code" class="btn btn-link">Hủy</a>
                                </div>
                            </form>
                            </c:if>
                            <!-- /Form -->

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

<!-- Slimscroll JS -->
<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom JS -->
<script src="assets/js/admin.js"></script>

</body>

</html>
