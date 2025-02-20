<%@ page import="com.example.finallaptrinhweb.model.ProductGroups" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi-VN">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<title>Thêm danh mục</title>
	<link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png" sizes="192x192" />

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
									<h3 class="page-title">Thêm danh mục</h3>
									<ul class="breadcrumb">
										<li class="breadcrumb-item"><a href="category">Danh mục</a></li>
										<li class="breadcrumb-item active">Thêm danh mục</li>
									</ul>
								</div>
							</div>
						</div>
						<!-- /Page Header -->

						<div class="card">
							<div class="card-body">

								<!-- Form -->
								<form action="add-category?action=add" method="post">
                                    <div class="form-group row">
                                        <label class="col-form-label col-md-2">Tên danh mục</label>
                                        <div class="col-md-10">
                                            <input name="name" type="text" class="form-control">
                                        </div>
									</div>
									<div class="form-group row" style="margin-bottom: 0">
										<label class="col-form-label col-md-2">ID danh mục lớn</label>
										<div class="col-md-10">
											<select name="id">
												<c:forEach var="cate" items="${categoryParent}">
													<option>${cate.id}</option>
												</c:forEach>
											</select>
										</div>
										<ul style="margin-top: 10px;margin-bottom: 0">
											<c:forEach var="cate" items="${categoryParent}">
												<li>${cate.id} : ${cate.groupName}</li>
											</c:forEach>
										</ul>
									</div>
                                    <div class="mt-4">
										<button class="btn btn-primary">Thêm</button>
										<a href="category" class="btn btn-link">Hủy</a>
									</div>
                                </form>
								<!-- /Form -->
								<!-- Form -->

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