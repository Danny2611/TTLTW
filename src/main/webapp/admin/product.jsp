<%@ page import="com.example.finallaptrinhweb.model.Util" %>
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
    <title>Quản lý sản phẩm</title>
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
<% System.out.println("Co dang vao product.jsp");%>
<div class="main-wrapper">
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="page-wrapper">
        <div class="content container-fluid">

            <!-- Page Header -->
            <div class="page-header">
                <div class="row">
                    <div class="col">
                        <h3 class="page-title">Sản phẩm</h3>
                    </div>
                    <div class="col-auto text-right">
                        <button class="btn btn-white filter-btn"  id="filter_search">
                            <i class="fas fa-file-excel"></i>
                        </button>
                        <a href="add-product?type=enterAdd" class="btn btn-primary add-button ml-3">
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
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
                                        <th>Ảnh</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Giá bán</th>
                                        <th class="table-nowrap">Số lượng</th>
                                        <th class="table-nowrap">Nhà cung cấp</th>
                                        <th class="text-center">Hành Động</th>
                                    </tr>
                                    </thead>

                                    <!-- Thêm vào nội dung ở đây -->
                                    <tbody>
                                    <c:forEach var="p" items="${product}">
                                        <c:set var="price" value="${p.price}" />

                                        <tr>
                                            <td>${p.id}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty p.imageUrl and fn:contains(p.imageUrl, 'res.cloudinary.com')}">
                                                        <img class="rounded service-img mr-1" src="${p.imageUrl}" alt="Hình ảnh sản phẩm">
                                                    </c:when>
                                                    <c:when test="${not empty p.imageUrl}">
                                                        <img class="rounded service-img mr-1" src="${pageContext.request.contextPath}/${p.imageUrl}" alt="Hình ảnh sản phẩm">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img class="rounded service-img mr-1" src="${pageContext.request.contextPath}/images/default.png" alt="Hình ảnh mặc định">
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                            <td>${p.productName}</td>
                                            <td class="table-nowrap"><%= Util.formatCurrency((double) pageContext.getAttribute("price")) %> VND</td>
                                            <td>${p.stockQuantity}</td>
                                            <td>${supplierMap[p.supplierId].contactName}</td>
                                            <td class="text-right" style="display: flex; gap: 5px;">
                                                <a href="edit-product?type=enterEdit&id=${p.id}" class="btn btn-sm bg-success-light">
                                                    <i class="far fa-edit mr-1"></i> Sửa
                                                </a>
                                                <a href="#"   onclick="confirmShowOrHiddenProduct(${p.id}, ${p.active ? 'true' : 'false'})" class="d-block text-center btn btn-warning  w-50">
                                                    <c:choose>
                                                        <c:when test="${p.active}">
                                                            <i class="fa fa-eye"></i>
                                                            <p>Đang hiện</p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="fa fa-eye-slash"></i>
                                                            <p>Đang ẩn</p>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </a>
                                                <a href="#" onclick="confirmDeleteProduct(${p.id});" style="margin-top: 5px; color: red;" class="btn btn-outline-danger btn-sm">
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
<div id="excelModal" class="modal" style="display:none; position: fixed; top: 0; left: 0; width: 100%; height: 100%;
    background-color: rgba(0,0,0,0.5); z-index: 9999; justify-content: center; align-items: center;">
    <div style="background: white; padding: 20px; border-radius: 8px; width: 400px; text-align: center;">
        <h4>Nhập file Excel</h4>
        <input type="file" id="excelFileInput" accept=".xlsx, .xls" />
        <br/><br/>
        <button class="btn btn-primary" id="uploadExcelBtn">Nhập</button>
        <button class="btn btn-secondary" onclick="closeModal()">Hủy</button>
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
    function confirmDeleteProduct(productId) {
        if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này không?")) {
            // Chuyển hướng đến Servlet để xóa sản phẩm
            window.location.href = "./delete-product?id=" + productId;
        }
    }
    function confirmShowOrHiddenProduct(productId, active) {
        active = Boolean(active); // Chuyển active thành kiểu Boolean
        console.log("active:", active, "typeof:", typeof active);
        const status = active ? "ẩn" : "hiện"
        if (confirm(`Bạn có muốn thay đổi trạng thái sản phẩm này không?`)) {
            window.location.href = "./active?id=" + productId;
        }
    }


</script>
<script>
    const btnExcel = document.getElementById('filter_search');
    const excelModal = document.getElementById('excelModal');

    btnExcel.addEventListener('click', () => {
        excelModal.style.display = 'flex'; // Hiện modal
    });

    function closeModal() {
        excelModal.style.display = 'none'; // Ẩn modal
    }
    excelModal.addEventListener('click', function(event) {
        if (event.target === excelModal) {
            closeModal();
        }
    });

    document.getElementById('uploadExcelBtn').addEventListener('click', () => {
        const fileInput = document.getElementById('excelFileInput');
        const file = fileInput.files[0];
        if (!file) {
            alert('Vui lòng chọn file Excel!');
            return;
        }
        if (!/\.(xlsx|xls)$/i.test(file.name)) {
            alert('Chỉ chấp nhận file Excel!');
            return;
        }
        // Xử lý file ở đây
        const formData = new FormData();
        formData.append('file', file);

        // Gửi file lên server
        fetch('http://localhost:8080/FinalLapTrinhWeb_war/admin/ImportExcelServlet', {
            method: 'POST',
            body: formData
        })
            .then(res => res.json())
            .then(data => {
                alert('Đã xử lý file Excel thành công!');
                console.log(data); // Có thể hiện danh sách sản phẩm được cập nhật/tạo mới
                closeModal();
            })
            .catch(err => {
                console.error(err);
                alert('Lỗi khi xử lý file Excel!');
            });
    });
</script>

</body>
</html>
