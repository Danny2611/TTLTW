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
    <title>Thêm sản phẩm</title>
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
                                <h3 class="page-title">Thêm sản phẩm</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="product.jsp">Sản phẩm</a></li>
                                    <li class="breadcrumb-item active">Thêm sản phẩm</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /Page Header -->

                    <div class="card">
                        <div class="card-body">

                            <!-- Form -->
                            <form action="add-product?type=add" method="post" enctype="multipart/form-data">
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2 table-nowrap">Tên sản phẩm</label>
                                    <div class="col-md-10">
                                        <input name="name" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2 table-nowrap">Giá sản phẩm</label>
                                    <div class="col-md-10">
                                        <input name="price" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Danh mục</label>
                                    <div class="col-md-10">
                                        <input name="cate" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Số Lượng</label>
                                    <div class="col-md-10">
                                        <input name="quantity" type="text" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Công dụng</label>
                                    <div class="col-md-10">
                                        <input name="purpose" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Chỉ định</label>
                                    <div class="col-md-10">
                                        <input name="contrain" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Tồn</label>
                                    <div class="col-md-10">
                                        <input name="stock" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Thành phần</label>
                                    <div class="col-md-10">
                                        <input name="ingre" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Liều lượng</label>
                                    <div class="col-md-10">
                                        <input name="dosage" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Hướng dẫn</label>
                                    <div class="col-md-10">
                                        <input name="instruc" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Thời hạn</label>
                                    <div class="col-md-10">
                                        <input name="period" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2 table-nowrap">Loại sản phẩm</label>
                                    <div class="col-md-10">
                                        <input name="type" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Bảo quản</label>
                                    <div class="col-md-10">
                                        <input name="store" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2 table-nowrap">Nhà cung cấp</label>
                                    <div class="col-md-10">
                                        <input name="idsup" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2 table-nowrap">Ảnh sản phẩm</label>
                                    <div class="col-md-10">
                                        <input name="image" type="file" class="form-control">
                                    </div>
                                </div>
                                <div class="mt-4">
                                    <button class="btn btn-primary">Thêm</button>
                                    <a href="product" class="btn btn-link">Hủy</a>
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

<!-- jQuery -->
<script src="assets/js/jquery-3.5.0.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="assets/js/popper.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

<!-- Select2 JS -->
<script src="assets/js/select2.min.js"></script>

<!-- Custom JS -->
<script src="assets/js/admin.js"></script>

<%--Sweetalert--%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    const urlParams = new URLSearchParams(window.location.search);
    const success = urlParams.get('success');

    if (success === 'true') {
        Swal.fire({
            title: 'Thành công!',
            text: 'Thêm sản phẩm thành công.',
            icon: 'success',
            confirmButtonText: 'OK'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = 'product';
            }
        });
    }
</script>



</body>

</html>