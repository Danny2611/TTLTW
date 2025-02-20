<%--
  Created by IntelliJ IDEA.
  User: DINHTUNG
  Date: 09/01/2024
  Time: 2:02 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi-VN">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>Thiết lập</title>
    <link href="assets/img/icon/icon-logo.png" rel="shortcut icon">

    <!-- Favicons -->
    <link rel="shortcut icon" href="assets/img/favicon.png">

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
            <!-- Page Header -->
            <div class="page-header">
                <div class="row">
                    <div class="col-12">
                        <h3 class="page-title">Thiết lập chung</h3>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->

            <ul class="nav nav-tabs menu-tabs">
                <ul class="nav nav-tabs menu-tabs">
                    <li class="nav-item active">
                        <a class="nav-link">Thiết lập chung</a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="setting-address">Địa chỉ</a>
                    </li>
                </ul>
            </ul>

            <div class="row">
                <div class="col-xl-8 col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <!-- Form -->
                            <form action="setting" method="post">

                                    <div class="form-group">
                                        <label>Facebook</label>
                                        <input type="text" class="form-control" name="facebook"
                                               value="${applicationScope.social_media.facebook}">
                                    </div>
                                    <div class="form-group">
                                        <label>Instagram</label>
                                        <input type="text" class="form-control" name="instagram"
                                               value="${applicationScope.social_media.instagram}">
                                    </div>
                                    <div class="form-group">
                                        <label>Twitter</label>
                                        <input type="text" class="form-control" name="twitter"
                                               value="${applicationScope.social_media.twitter}">
                                    </div>
                                    <div class="form-group">
                                        <label>Pinterset</label>
                                        <input type="text" class="form-control" name="pinterest"
                                               value="${applicationScope.social_media.pinterest}">
                                    </div>
                                    <div class="form-group">
                                        <label>Youtube</label>
                                        <input type="text" class="form-control" name="youtube"
                                               value="${applicationScope.social_media.youtube}">
                                    </div>
                                    <div class="form-group">
                                        <label>Linkedin</label>
                                        <input type="text" class="form-control" name="linkedin"
                                               value="${applicationScope.social_media.linkedin}">
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary">
                                            Lưu thay đổi
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <!-- /Form -->

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
