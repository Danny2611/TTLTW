
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <!-- Select CSS -->
    <link rel="stylesheet" href="assets/css/select2.min.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="assets/css/admin.css">
    <script src="assets/js/tinymce/tinymce.min.js"></script>
    <%--    <script src="assets/js/tinymce.js"></script>--%>
    <script type="text/javascript" src="lib/ckeditor/ckeditor.js"></script>
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
                            <li class="breadcrumb-item"><a href="product.html">Nhà cung cấp</a></li>
                            <li class="breadcrumb-item active">${title}</li>
                        </ul>
                    </div>

                </div>
            </div>
            <!-- /Page Header -->
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <form action="add-supplier" method="post"
                                  accept-charset="UTF-8" enctype="multipart/form-data">
                                <c:if test="${type=='add'}">
                                    <div class="form-group" style="display: none">
                                        <input class="form-control" type="text" value="add" name="type">
                                    </div>
                                    <%--                                    <div class="form-group">--%>
                                    <%--                                        <label>Ảnh</label>--%>
                                    <%--                                        <input class="form-control" type="file" name="img">--%>
                                    <%--                                    </div>--%>
                                    <div class="form-group">
                                        <label>Nhà cung cấp</label>
                                        <input id="name_supplier" class="form-control" type="text" name="name" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Địa chỉ</label>
                                        <input id="address" class="form-control" type="text" name="address" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Điện thoại</label>
                                        <input id="phone" class="form-control" type="text"  name="phone" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input id="email" class="form-control" type="text"  name="email" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Logo</label>
                                        <input class="form-control" type="file" name="logo" accept="image/*">
                                    </div>
                                    <div class="mt-4">
                                        <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
                                        <a href="supplier" class="btn btn-link">Hủy</a>
                                    </div>
                                </c:if>
                                <c:if test="${type=='edit'}">
                                    <div class="form-group" style="display: none">
                                        <input class="form-control" type="text" value="edit" name="type">
                                    </div>
                                    <div class="form-group">
                                        <label>ID</label>
                                        <input class="form-control" type="text" name="id" value="${supplier.id}"
                                               readonly>
                                    </div>
                                    <div class="form-group">
                                        <label>Nhà cung cấp</label>
                                        <input id="name_supplier"class="form-control" type="text" value="${supplier.supplierName}" name="name">
                                    </div>
                                    <div class="form-group">
                                        <label>Địa chỉ</label>
                                        <input id="address" class="form-control" type="text" value="${supplier.detailAddress}" name="address">
                                    </div>
                                    <div class="form-group">
                                        <label>Điện thoại</label>
                                        <input id="phone" class="form-control" type="text" value="${supplier.phone}" name="phone">
                                    </div>
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input id="email" class="form-control" type="text" value="${supplier.email}" name="email">
                                    </div>
                                    <div class="mt-4">
                                        <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
                                        <a href="supplier" class="btn btn-link">Hủy</a>
                                    </div>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script>
            CKEDITOR.replace('content');
        </script>
        <script src="assets/js/jquery-3.5.0.min.js"></script>

        <!-- Bootstrap Core JS -->
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <!-- Select2 JS -->
        <script src="assets/js/select2.min.js"></script>

        <!-- Custom JS -->
        <script src="assets/js/admin.js"></script>
    </div>
</body>
</html>
