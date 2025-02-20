<%--
  Created by IntelliJ IDEA.
  User: DINHTUNG
  Date: 09/01/2024
  Time: 3:03 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi-VN">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0" />
  <title>Thiết lập</title>
  <link href="assets/img/icon/icon-logo.png" rel="shortcut icon" />
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css" />

  <!-- Fontawesome CSS -->
  <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css" />
  <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />

  <!-- Animate CSS -->
  <link rel="stylesheet" href="assets/css/animate.min.css" />

  <!-- Main CSS -->
  <link rel="stylesheet" href="assets/css/admin.css" />
</head>

<body>
<div class="main-wrapper">

  <jsp:include page="menu.jsp"></jsp:include>
  <div class="page-wrapper">
    <div class="content container-fluid">
      <!-- Page Header -->
      <div class="page-header">
        <div class="row">
          <div class="col-12">
            <h3 class="page-title">Thiết lập địa chỉ</h3>
          </div>
        </div>
      </div>
      <!-- /Page Header -->

      <ul class="nav nav-tabs menu-tabs">

        <ul class="nav nav-tabs menu-tabs">
          <li class="nav-item">
            <a class="nav-link" href="setting">Thiết lập chung</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link">Địa chỉ</a>
          </li>
        </ul>
      </ul>

      <div class="row">
        <div class="col-xl-3 col-lg-4 col-md-4 settings-tab">
          <div class="card">
            <div class="card-body">
              <div class="nav flex-column">
                <a class="nav-link active" data-toggle="tab" href="#main-address">Địa chỉ chính</a>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-9 col-lg-8 col-md-8">
          <div class="card">
            <div class="card-body p-0">
              <div class="tab-content pt-0">
                <!-- General Settings -->
                <div id="main-address" class="tab-pane active">
                  <div class="card mb-0">
                    <div class="card-header">
                      <h4 class="card-title">Địa chỉ chính</h4>
                    </div>
                    <div class="card-body">
                      <form action="setting-address" method="post">
                        <div class="form-group" style="display: none;">
                          <label>Type</label>
                          <input type="text" value="main-address" class="form-control"
                                 name="type" />
                        </div>
                        <div class="form-group">
                          <label>Địa chỉ</label>
                          <input type="text" class="form-control" name="address" value="${applicationScope.address.get(0).address}" />
                        </div>
                        <div class="form-group">
                          <label>Điện thoại</label>
                          <input type="number" class="form-control" name="phone" value="${applicationScope.address.get(0).phone}"/>
                        </div>
                        <div class="form-group">
                          <label>HotLine</label>
                          <input type="number" class="form-control" name="hotline" value="${applicationScope.address.get(0).hotline}"/>
                        </div>
                        <div class="form-group">
                          <label>Email</label>
                          <input type="email" class="form-control" name="email" value="${applicationScope.address.get(0).email}"/>
                        </div>
                        <div class="form-group">
                          <label>Giờ mở cửa</label>
                          <input type="text" class="form-control" name="timeOpen" value="${applicationScope.address.get(0).timeOpen}"/>
                        </div>
                        <div class="form-group">
                          <label>Địa chỉ bản đồ</label>
                          <input type="text" class="form-control" name="map" value="${applicationScope.address.get(0).map}"/>
                        </div>
                        <div class="form-group">
                          <button type="submit" class="btn btn-primary">
                            Lưu thay đổi
                          </button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
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

<!-- Slimscroll JS -->
<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom JS -->
<script src="assets/js/admin.js"></script>
</body>

</html>