<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 4/11/2025
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đơn hàng của bạn</title>
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
<jsp:include page="header.jsp"/>
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
                        <c:forEach var="o" items="${orders}">`
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
<jsp:include page="footer.jsp"/>
</body>
</html>
