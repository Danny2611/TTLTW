
<%@ page import="java.util.Date" %>
<%@ page import="com.example.finallaptrinhweb.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<!DOCTYPE html>
<html lang="vi-VN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>Khách hàng</title>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192" />

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
<%--<c:set var="m" value="users"></c:set>--%>
<%--<%System.out.println(pageContext.getAttribute("m"));%>--%>
<% System.out.println("Co dang vao product.jsp");%>
<div class="main-wrapper">
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="page-wrapper">
        <div class="content container-fluid">
            <!-- Page Header -->
            <div class="page-header">
                <div class="row">
                    <div class="col">
                        <h3 class="page-title">Khách hàng</h3>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->

            <!-- Search Filter -->
            <div class="card filter-card" id="filter_inputs">
                <div class="card-body pb-0">
                    <form action="admin-page/product" method="post">
                        <div class="row filter-row">
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input class="form-control" type="text" name="product-id">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group">
                                    <label>Tên</label>
                                    <input class="form-control" type="text" name="product-name">
                                </div>
                            </div>
                            <!-- Thay đổi theo danh sách đề mục -->
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group">
                                    <button class="btn btn-primary btn-block" type="submit">Chọn</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- /Search Filter -->
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-hover table-center mb-0 datatable">

                                    <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Tên</th>
                                        <th>Email</th>
                                        <th>Ngày tạo</th>
                                        <th>Điện thoại</th>
                                        <th>Hoạt động</th>
                                    </tr>
                                    </thead>

                                    <!-- Thêm vào nội dung ở đây -->
                                    <tbody>
                                    <c:forEach var="p" items="${users}">
                                        <c:set var="date_created" value="${p.date_created}"></c:set>
                                        <tr>
                                            <td>${p.id}</td>
                                            <td>${p.fullName}</td>
                                            <td>${p.email}</td>
                                            <td>${p.date_created}</td>
                                            <td>${p.phone}</td>

                                            <td>
                                                <div class="status-toggle">
                                                    <input id="service_${p.id}" class="check" type="checkbox" ${p.verifyStatus eq 'verified' ? 'checked' : ''}>
                                                    <label for="service_${p.id}" class="checktoggle">checkbox</label>
                                                </div>
                                                    <%-- <a href="edit_user?type=enteredit&id=${p.id}" class="btn btn-sm bg-success-light ">	<i class="far fa-edit mr-1"></i> Sửa</a>--%>
                                        <%--   <a href="#" style="margin-top: 5px;color: red" class="btn btn-outline-danger btn-sm"
                                              onclick="confirmDelete(${p.id})">
                                               <i class="fa fa-trash-o"></i> Xóa
                                           </a> --%>

                                       </td>
                                   </tr>
                               </c:forEach>
                               <%System.out.println("DA xuong toi day");%>
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
<script>
function confirmDelete(userId) {
   if (confirm("Bạn có chắc chắn muốn xóa người dùng này không?")) {
       // Chuyển hướng đến Servlet để xóa người dùng
       window.location.href = "./delete_user?id=" + userId;
   }
}
</script>
</body>
</html>
