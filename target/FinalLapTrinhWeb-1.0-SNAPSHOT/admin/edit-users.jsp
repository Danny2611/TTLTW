
<%@ page import="com.example.finallaptrinhweb.model.Util" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi-VN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>${title}</title>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192" />
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
            <!-- Page Header -->
            <div class="page-header">
                <div class="row">
                    <div class="col">
                        <h3 class="page-title">${title}</h3>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="categories.html">Người dùng</a></li>
                            <li class="breadcrumb-item active">${title}</li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Thông tin người dùng</h4>
                        </div>
                        <div class="card-body">
                            <form action="edit_user" method="post"
                                  accept-charset="UTF-8">
                                <c:set var="bd" value="${user.getDateOfBirth()}"></c:set>
                                <c:set var="createddate" value="${user.date_created}"></c:set>
                                <div class="form-group" style="display: none">
                                    <input class="form-control" type="text" value="edit" name="type">
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Email</label>
                                    <div class="col-md-10">
                                        <input type="email" class="form-control" name="email" value="${user.email}"  required>
                                    </div>
                                </div>
                                <div class="form-group row" style="display: none" >
                                    <label class="col-form-label col-md-2">Password</label>
                                    <div class="col-md-10" >
                                        <input type="password" class="form-control" name="password" value="${user.password}"  required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Mã người dùng</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="usercode" value="${user.id}" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Tên</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="name" value="${user.username}" >
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Ngày sinh</label>
                                    <div class=" col-md-10">
                                        <input class="form-control" type="date" name="birthday"
                                               value="<%=Util.dateFormatNoTime((Date) pageContext.getAttribute("bd"))%>">

                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Địa chỉ</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="address" value="${user.detail_address}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Ngày tạo</label>
                                    <div class=" col-md-10">
                                        <input class="form-control" type="date" name="createddate"
                                               value="<%=Util.dateFormatNoTime((Date) pageContext.getAttribute("createddate"))%>">
                                    </div>
                                </div>
                                <div class="mt-4">
                                    <button class="btn btn-primary" type="submit">Cập Nhật</button>
                                    <a href="users" class="btn btn-link">Hủy</a>
                                </div>
                            </form>
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
<!-- Custom JS -->
<script src="assets/js/admin.js"></script>
</body>
</html>