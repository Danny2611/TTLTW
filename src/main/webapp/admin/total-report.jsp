<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.finallaptrinhweb.model.Util" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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

    <!-- Datatables CSS -->
    <link rel="stylesheet" href="assets/plugins/datatables/datatables.min.css">

    <!-- Datepicker CSS -->
    <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css">

    <!-- Animate CSS -->
    <link rel="stylesheet" href="assets/css/animate.min.css">
    <!-- Select CSS -->
    <link rel="stylesheet" href="assets/css/select2.min.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="assets/css/admin.css">

</head>
<body>
<div class="main-wrapper">
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="page-wrapper">
        <div class="content container-fluid">

            <!-- Page Header -->
            <div class="page-header">
                <div class="row">
                    <div class="col">
                        <h3 class="page-title">${title}</h3>
                    </div>
                </div>
            </div>

            <ul class="nav nav-tabs menu-tabs">
                <li class="nav-item <c:if test="${type_view==all}">active</c:if>">
                    <a class="nav-link" href="total-report">Tất cả</a>
                </li>
                <li class="nav-item <c:if test="${type_view=='Đã hủy'}">active</c:if>">
                    <a class="nav-link" href="total-report?type_view=Đã hủy">Đã hủy </a>
                </li>
                <li class="nav-item <c:if test="${type_view=='Bị từ chối'}">active</c:if>">
                    <a class="nav-link" href="total-report?type_view=Bị từ chối">Bị từ chối </a>
                </li>
                <li class="nav-item <c:if test="${type_view=='Chờ xử lý'}">active</c:if>">
                    <a class="nav-link" href="total-report?type_view=Chờ xử lý">Chờ xử lý </a>
                </li>
                <li class="nav-item <c:if test="${type_view=='Đang giao hàng'}">active</c:if>">
                    <a class="nav-link" href="total-report?type_view=Đang giao hàng">Đang giao hàng </a>
                </li>
                <li class="nav-item <c:if test="${type_view=='Giao hàng thành công'}">active</c:if>">
                    <a class="nav-link" href="total-report?type_view=Giao hàng thành công">Giao hàng thành công </a>
                </li>
            </ul>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-hover table-center mb-0 datatable">
                                    <!-- Thay đổi code ở đây Thay đổi theo file word -->
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Ngày đặt hàng</th>
                                        <th>Khách hàng</th>
                                        <th>Tổng tiền</th>
                                        <th>Trạng thái</th>
                                        <th class="text-right">Hành Động</th>
                                    </tr>
                                    </thead>
                                    <!-- Thêm vào nội dung ở đây -->
                                    <tbody>
                                    <c:forEach var="o" items="${total_report}">
                                        <c:set var="total_pay" value="${o.totalPay}"/>
                                        <c:set var="date_created" value="${o.dateCreated}"/>
                                        <tr>
                                            <td>${o.id}</td>
                                            <td>${Util.dateFormatNoTime(date_created)}</td>
                                            <td>${o.username}</td>
                                            <td>${Util.formatCurrency(total_pay)}</td>
                                            <td>
                                                <c:if test="${o.status=='Đã hủy'}">
                                                    <span class="badge badge-danger">Đã hủy</span>
                                                </c:if>
                                                <c:if test="${o.status=='Bị từ chối'}">
                                                    <span class="badge badge-warning">Bị từ chối</span>
                                                </c:if>
                                                <c:if test="${o.status=='Chờ xử lý'}">
                                                    <span class="badge badge-dark">Chờ xử lý</span>
                                                </c:if>
                                                <c:if test="${o.status=='Đang giao hàng'}">
                                                    <span class="badge badge-info">Đang giao hàng</span>
                                                </c:if>
                                                <c:if test="${o.status=='Giao hàng thành công'}">
                                                    <span class="badge badge-success">Giao hàng thành công</span>
                                                </c:if>
                                            </td>
                                            <td class="text-right">
                                                <a href="view-order?id=${o.id}" class="btn btn-sm bg-info-light">
                                                    <i class="far fa-eye mr-1"></i> Chi tiết
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
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

<!-- Datepicker Core JS -->
<script src="assets/js/moment.min.js"></script>
<script src="assets/js/bootstrap-datetimepicker.min.js"></script>

<!-- Datatables JS -->
<script src="assets/plugins/datatables/datatables.min.js"></script>

<!-- Select2 JS -->
<script src="assets/js/select2.min.js"></script>

<!-- Custom JS -->
<script src="assets/js/admin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${param.update_success == 'true'}">
    <script>
        Swal.fire({
            title: 'Cập nhật thành công!',
            icon: 'success',
            confirmButtonText: 'OK'
        });
    </script>
</c:if>

</body>
</html>
