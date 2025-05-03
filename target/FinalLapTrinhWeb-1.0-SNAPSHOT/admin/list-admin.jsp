
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi-VN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>Danh sách admin</title>
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

    <!-- Animate CSS -->
    <link rel="stylesheet" href="assets/css/animate.min.css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="assets/css/admin.css">
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">


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
                        <h3 class="page-title">Danh sách admin</h3>
                    </div>
                    <div class="col-auto text-right">
                        <a href="add-admin.jsp" class="btn btn-success add-button ml-3">
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->

            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-hover table-center mb-0 datatable">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên</th>
                                        <th>Email</th>
                                        <th>Điện thoại</th>
                                        <th>Quyền</th>
                                        <th class="text-right">Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="user" items="${admins}">
                                        <tr>
                                            <td>${user.id}</td>
                                            <td>${user.fullName}</td>
                                            <td>${user.email}</td>
                                            <td>${user.phone}</td>
                                            <td style="display: flex; gap: 5px">
                                                <c:forEach var="role" items="${roles}">
                                                    <button
                                                            type="button"
                                                            data-bs-toggle="modal"
                                                            data-bs-target="#myModal"
                                                            onclick="changePermission(${role.getId()}, ${user.getId()})"
                                                            style="border: none;outline: none ;padding: 5px 10px; color: white; background-color: ${role.getId() ==user.getRoleId() ? '#66b840' : 'gray'}; border-radius: 5px; cursor: pointer; display: inline-block; margin: 5px;"
                                                    >
                                                            ${role.getRoleName()}
                                                    </button>
                                                </c:forEach>

                                            </td>

                                            <td class="text-right">
                                                <a href="edit-admin?type=enterEdit&id=${user.id}"
                                                   class="btn btn-sm bg-success-light "> <i
                                                        class="far fa-edit mr-1"></i> Sửa</a>
                                                    <%--                                            <a href="edit-product.jsp" style="margin-top: 5px;color: red "--%>
                                                    <%--                                               class="btn btn-outline-danger btn-sm"><i class="fa fa-trash-o"></i>--%>
                                                    <%--                                                Xóa</a>--%>
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
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myModalLabel">Thông báo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn có muốn thay đổi quyền ?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                <button onclick="confirmChange()" type="button" class="btn btn-primary">OK</button>
            </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="assets/js/jquery-3.5.0.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="assets/js/popper.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

<!-- Datatables JS -->
<script src="assets/plugins/datatables/datatables.min.js"></script>

<!-- Select2 JS -->
<script src="assets/js/select2.min.js"></script>

<!-- Custom JS -->
<script src="assets/js/admin.js"></script>
<!-- Bootstrap 5 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    let selectedRoleId = null;
    let selectedAdminId = null;
    const changePermission = (roleId, adminId) =>{
        selectedRoleId = roleId;
        selectedAdminId = adminId;
    }

    const confirmChange = async  ()=>{
        if(selectedRoleId !== null) {
            console.log("Role ID:", selectedRoleId);
            console.log("Admin ID:", selectedAdminId);

            const data = fetch("http://localhost:8080/FinalLapTrinhWeb_war/admin/change-permission", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({roleId: selectedRoleId, "userId": selectedAdminId})
            },

            ).then(res =>res.json())
            .then(
                data=>{
                    console.log(data)
                    if (data.status ==="success") {
                        alert("Cập nhật quyền thành công!");

                        // Cập nhật giao diện, đổi màu quyền mới được chọn
                        document.querySelectorAll("button").forEach(btn => {
                            if (btn.getAttribute("onclick")?.includes(selectedRoleId)) {
                                btn.style.backgroundColor = "#66b840"; // Màu xanh lá
                            } else {
                                btn.style.backgroundColor = "gray";
                            }
                        });
                    } else {
                        alert("Cập nhật quyền thất bại. Vui lòng thử lại!");
                    }

                    // Đóng modal
                    var myModal = bootstrap.Modal.getInstance(document.getElementById("myModal"));
                    myModal.hide();
                }
            )
            .catch(error => {
                console.error("Lỗi khi gọi API:", error);
                alert("Có lỗi xảy ra. Vui lòng thử lại!");
            });
        }
    }
</script>

</body>

</html>
