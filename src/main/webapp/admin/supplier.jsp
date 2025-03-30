
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">

    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">

    <!-- Datepicker CSS -->
    <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css">

    <!-- Datatables CSS -->
    <link rel="stylesheet" href="assets/plugins/datatables/datatables.min.css">

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
                        <h3 class="page-title">Nhà cung cấp</h3>
                    </div>
                    <div class="col-auto text-right">
                        <%--						<a class="btn btn-white filter-btn" href="javascript:void(0);" id="filter_search">--%>
                        <%--							<i class="fas fa-filter"></i>--%>
                        <%--						</a>--%>
                        <a href="add-supplier?type=enteradd" class="btn btn-primary add-button ml-3">
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->
            <!-- Search Filter -->
            <form action="#" method="post" id="filter_inputs">
                <div class="card filter-card">
                    <div class="card-body pb-0">
                        <div class="row filter-row">
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group">
                                    <label>Tên nhà cung cấp</label>
                                    <input class="form-control" type="text">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group">
                                    <label>Sản phẩm	</label>
                                    <input class="form-control" type="text">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group">
                                    <label>Từ ngày</label>
                                    <div class="cal-icon">
                                        <input class="form-control datetimepicker" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group">
                                    <label>Đến ngày</label>
                                    <div class="cal-icon">
                                        <input class="form-control datetimepicker" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group">
                                    <button class="btn btn-primary btn-block" type="submit">Chọn</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <!-- /Search Filter -->
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-hover table-center mb-0 datatable">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Logo</th>
                                        <th>Tên nhà cung cấp</th>
                                        <th>Địa chỉ</th>
                                        <th>Điện thoại</th>
                                        <th>Email</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${sup_view}" var="su">
                                        <tr>
                                            <td class="truncate-cell">
                                                <span class="truncate-text">${su.id}</span>
                                            </td>
                                            <td><img class="rounded service-img mr-1" src="${su.imageUrl}" alt="Hình ảnh danh mục"></td>
                                            <td class="truncate-cell">
                                                <span class="truncate-text">${su.supplierName}</span>
                                            </td>
                                            <td class="truncate-cell">
                                                <span class="truncate-text  address-text">${su.detailAddress}</span>
                                            </td>
                                            <td class="truncate-cell">
                                                <span class="truncate-text">${su.phone}</span>
                                            </td>
                                            <td class="truncate-cell">
                                                <span class="truncate-text">${su.email}</span>
                                            </td>
                                            <td class="text-right">
                                                <a href="add-supplier?type=enterEdit&id=${su.id}" class="btn btn-sm bg-success-light mr-2">	<i class="far fa-edit mr-1"></i> Sửa</a>
                                                <a href="#" style="margin-top: 5px; color: red" class="btn btn-outline-danger btn-sm"
                                                   onclick="confirmDelete(${su.id}, this)">
                                                    <i class="fa fa-trash-o"></i> Xóa
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

<!-- Custom JS -->
<script src="assets/js/admin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function confirmDelete(supplierId, element) {
        Swal.fire({
            title: "Bạn có chắc chắn muốn xóa?",
            text: "Thao tác này không thể hoàn tác!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: "Xóa",
            cancelButtonText: "Hủy"
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: "./delete-supplier",
                    type: "POST",
                    data: { id: supplierId },
                    success: function(response) {
                        Swal.fire({
                            title: "Đã xóa!",
                            text: "Nhà cung cấp đã bị xóa thành công.",
                            icon: "success",
                            timer: 1500,
                            showConfirmButton: false
                        });

                        // Xóa dòng chứa nhà cung cấp khỏi UI
                        $(element).closest("tr").fadeOut(500, function() {
                            $(this).remove();
                        });
                        updatePaginationInfo();
                    },
                    error: function() {
                        Swal.fire("Lỗi!", "Xóa nhà cung cấp thất bại.", "error");
                    }
                });
            }
        });
    }

    function updatePaginationInfo() {
        var info = dataTable.page.info();
        var total = info.recordsTotal;
        var currentPage = info.page + 1;
        var start = info.start + 1;
        var end = info.end;

        // Nếu là dòng cuối cùng của trang cuối
        if (total % info.length === 0 && currentPage === Math.ceil(total / info.length)) {
            dataTable.page('previous').draw('page');
        }
    }
</script>


</body>

</html>