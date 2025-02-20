<%--
  Created by IntelliJ IDEA.
  User: DINHTUNG
  Date: 20/01/2024
  Time: 8:20 CH
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h3 class="page-title">Thêm Admin</h3>
                <ul class="breadcrumb">
                  <li class="breadcrumb-item"><a href="admin-list.html">Danh sách admin</a></li>
                  <li class="breadcrumb-item active">Thêm admin</li>
                </ul>
              </div>
            </div>
          </div>
          <!-- /Page Header -->

          <div class="card">
            <div class="card-body">
              <!-- Form -->
              <form action="add-admin" method="post" accept-charset="UTF-8" id="login-form">
                  <div class="form-group row">
                    <label class="col-form-label col-md-2">Tên người dùng</label>
                    <div class="col-md-10">
                      <input type="text" class="form-control" name="username">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-form-label col-md-2">Email</label>
                    <div class="col-md-10">
                      <input type="email" class="form-control" name="email">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-form-label col-md-2">Mật khẩu</label>
                    <div class="col-md-10">
                      <input id="pass" type="password" required class="form-control" name="pass">
                      <label style="color: red" id="pw-notice">Mật khẩu phải dài từ 8 kí tự và chứa cả chữ in hoa và số</label>
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-form-label col-md-2">Điện thoại</label>
                    <div class="col-md-10">
                      <input type="tel" class="form-control" name="phone" required>
                    </div>
                  </div>
                  <div class="mt-4">
                    <button class="btn btn-primary" type="submit">Lưu</button>
                    <a href="list-admin" class="btn btn-link">Hủy</a>
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

<!-- Custom JS -->
<script src="assets/js/admin.js"></script>

</body>

</html>
