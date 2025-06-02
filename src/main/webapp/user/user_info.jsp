<%@ page import="com.example.finallaptrinhweb.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.finallaptrinhweb.dao.UserDAO" %>
<% User user = (User) session.getAttribute("auth");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.finallaptrinhweb.model.Util" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="css/thuvien/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/thuvien/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/user/user.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <title>Trang cá nhân</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<%
    boolean hasResetPasswordError = request.getAttribute("oldPassError") != null ||
            request.getAttribute("newPassError") != null ||
            request.getAttribute("reNewPassError") != null;
%>
<div class="website-wrapper">
    <jsp:include page="header.jsp"/>
    <div class="page-title" style="
            background-image: url(https://tienthangvet.vn/wp-content/uploads/title-tag-tien-thang-vet-tsd1.jpg);
          ">
        <div class="container">
            <h1 class="title">Trang cá nhân</h1>
        </div>
    </div>
    <div class="container">
        <div class="row my-2 user__border">
            <div class="col-lg-8 order-lg-2">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a href="#profile" data-toggle="tab" class="nav-link nav-link-2 <%= hasResetPasswordError ? "" : "active" %>">Tài Khoản</a>
                    </li>
                    <li class="nav-item">
                        <a href="#messages" data-toggle="tab" class="nav-link nav-link-2 <%= hasResetPasswordError ? "active" : "" %>">Đổi Mật Khẩu</a>
                    </li>
                    <li class="nav-item">
                        <a href="#edit" data-toggle="tab" class="nav-link nav-link-2 ">Đơn Hàng</a>
                    </li>
                </ul>
                <div class="tab-content py-4">
                    <div class="tab-pane <%= hasResetPasswordError ? "" : "active" %>" id="profile">
                        <!-- <h5 class="mb-3">Thông Tin Tài Khoản</h5> -->
                        <div class="row">
                            <div class="col-md-12">
                                <header>
                                    <h1>HỒ SƠ CỦA TÔI</h1>
                                    <div class="content">Quản lý thông tin hồ sơ để bảo mật tài khoản</div>
                                </header>

                                <form class="formAcount validate clearfix" method="post" action="updateinfouser">
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label"> Họ tên: <span>(*)</span></label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="text" id="fullName" name="fullName"
                                                       value="<%=user.getFullName() == null ? "": user.getFullName()%>"
                                                       placeholder="Họ tên"
                                                       class="validate[required,minSize[4],maxSize[32]] form-control input-sm"
                                                       required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label">Ngày sinh: <span></span></label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="date" id="birthday" name="birthday"
                                                       value="<%=user.getDateOfBirth() == null ? "": user.getDateOfBirth()%>"
                                                       placeholder="Ngày sinh"
                                                       class="validate[required] form-control input-sm" required>
                                            </div>
                                        </div>
                                    </div>
                                    <% if (user.getPhone() != null) { %>
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label">Điện thoại: <span>(*)</span></label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="text" id="mobile" name="phone"
                                                       value="<%=user.getPhone() == null ? "": user.getPhone()%>"
                                                       placeholder="Điện thoại"
                                                       class="validate[required,custom[phone]] form-control input-sm"
                                                       required

                                                >
                                            </div>
                                        </div>
                                    </div>
                                    <% } %>

                                    <% if (user.getEmail() != null) { %>
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label">Email: <span>(*)</span></label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="text" name="email"
                                                       value="<%=user.getEmail() == null ? "": user.getEmail()%>"
                                                       placeholder="Email"
                                                       class="validate[required,custom[email]] form-control input-sm"
                                                       required>
                                            </div>
                                        </div>
                                    </div>
                                    <% } %>

                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label">Tỉnh/Thành phố
                                                <span>(*)</span></label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="text" name="city"
                                                       value="<%=user.getCity() == null ? "": user.getCity()%>"
                                                       placeholder="Thành phố"
                                                       class="validate[required,custom[email]] form-control input-sm"
                                                       required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label">Quận/ Huyện: <span>(*)</span></label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="text" id="district" name="district"
                                                       value="<%=user.getDistrict() == null ? "": user.getDistrict()%>"
                                                       placeholder="Quận/ Huyện"
                                                       class="validate[required,custom[email]] form-control input-sm"
                                                >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label">Xã/ Phường/ Thị trấn: <span>(*)</span></label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="text" id="ward" name="ward"
                                                       value="<%=user.getWard() == null ? "": user.getWard()%>"
                                                       placeholder="Xã/ Phường/ Thị trấn"
                                                       class="validate[required,custom[email]] form-control input-sm"
                                                >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label">Địa chỉ chi tiết:
                                                <span>(*)</span></label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="text" id="address" name="address"
                                                       value="<%=user.getDetail_address() == null ? "": user.getDetail_address()%>"
                                                       placeholder="Địa chỉ chi tiết"
                                                       class="validate[required] form-control input-sm">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label"></label>
                                            <div class="col-lg-6 col-md-9">
                                                <button class="btn-update">CẬP NHẬT</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!--/row-->
                    </div>

                    <div class="tab-pane <%= hasResetPasswordError ? "active" : "" %>" id="messages">
                        <!-- <h5 class="mb-3">Thông Tin Tài Khoản</h5> -->
                        <div class="row">
                            <div class="col-md-12">
                                <header>
                                    <h1>THAY ĐỔI MẬT KHẨU</h1>
                                    <div class="content">Bạn nên cập nhật mật khẩu thường xuyên vì lí do bảo mật</div>
                                </header>
                                <%
                                    // Kiểm tra xem user có mật khẩu hay không (đăng nhập bằng Google)
                                    String userPassword = UserDAO.getInstance().getPasswordById(user.getId());
                                    boolean hasPassword = userPassword != null && !userPassword.isEmpty();
                                %>
                                <form id="formAcount" class="formAcount_resetPass validate clearfix" method="post" action="resetpassword">
                                    <% String success = (String) request.getAttribute("successMessage"); %>
                                    <% if (success != null) { %>
                                    <p style="color: #7cb342; margin-bottom: 10px"><%=success%></p>
                                    <% } %>

                                    <% if (hasPassword) { %>
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label"> Mật khẩu cũ: </label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="password" name="pass" class="form-control input-sm" required>
                                                <% if (request.getAttribute("oldPassError") != null) { %>
                                                <p style="color: red;"><%= request.getAttribute("oldPassError") %></p>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>
                                    <% } else { %>
                                    <!-- Thông báo cho người dùng biết họ đang thiết lập mật khẩu lần đầu -->
                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <div class="col-md-9 offset-md-3">
                                                <p style="color: #2196F3; margin-bottom: 15px;">Bạn đang thiết lập mật khẩu lần đầu tiên cho tài khoản này.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <% } %>

                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label"> Mật khẩu mới: </label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="password" name="newpass" class="form-control input-sm" required>
                                                <% if (request.getAttribute("newPassError") != null) { %>
                                                <p style="color: red;"><%= request.getAttribute("newPassError") %></p>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label"> Xác nhận mật khẩu: </label>
                                            <div class="col-lg-6 col-md-9">
                                                <input type="password" name="renewpass" class="form-control input-sm" required>
                                                <% if (request.getAttribute("reNewPassError") != null) { %>
                                                <p style="color: red;"><%= request.getAttribute("reNewPassError") %></p>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group clearfix">
                                        <div class="row">
                                            <label class="col-md-3 control-label"></label>
                                            <div class="col-lg-6 col-md-9">
                                                <!-- Thêm hidden field để biết có phải là thiết lập mật khẩu lần đầu không -->
                                                <input type="hidden" name="isFirstTimeSetup" value="<%= !hasPassword %>">
                                                <button class="btn-update">LƯU</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!--/row-->
                    </div>

                    <div class="tab-pane myorder__style" id="edit">
                        <!-- <div class="heading">Đơn hàng của tôi</div> -->
                        <div class="inner">
                            <table>
                                <thead>
                                <tr>
                                    <th>Mã đơn hàng</th>
                                    <th>Ngày mua</th>
                                    <th>Thanh toán</th>
                                    <th>Trạng thái đơn hàng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${order}" var="o">
                                    <c:if test="${not (o.status eq 'Đã hủy' or o.status eq 'Bị từ chối')}">
                                        <tr>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/user/order_detail?id=${o.id}">${o.id}</a>
                                            </td>
                                            <td>${Util.formatTimestamp(o.dateCreated)}</td>
                                            <td>
                                                <c:if test="${o.payment}">
                                                    Momo
                                                </c:if>
                                                <c:if test="${not o.payment}">
                                                    Tiền Mặt
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${o.status eq 'Chờ xử lý'}">
                                                    Chờ xử lý
                                                </c:if>
                                                <c:if test="${o.status eq 'Bị từ chối'}">
                                                    Bị từ chối
                                                </c:if>
                                                <c:if test="${o.status eq 'Đã hủy'}">
                                                    Đã hủy
                                                </c:if>
                                                <c:if test="${o.status eq 'Đang giao hàng'}">
                                                    Đang giao hàng
                                                </c:if>
                                                <c:if test="${o.status eq 'Giao hàng thành công'}">
                                                    Giao hàng thành công
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>

                    <!--/row-->
                </div>

            </div>
            <div class="col-lg-4 order-lg-1 text-center img-2">
                <div class="img-ava">
                    <img id="avatarImg"
                         src="${avata == null ? 'https://tienthangvet.vn/wp-content/uploads/logo-tien-thang-vet.jpg' : avata}"
                         class="mx-auto img-fluid img-circle d-block" alt="avatar">
                    <label class="load-ava">
                        <span class="custom-file-control">Đổi Ảnh</span>
                        <input type="file" id="file" class="custom-file-input" accept="image/*">
                    </label>
                </div>
                <h6 class="mt-2">Nhóm 30</h6>
            </div>

        </div>
    </div>
    <jsp:include page="loader.jsp" />
    <jsp:include page="footer.jsp"/>
</div>
<script src="js/thuvien/jquery-3.3.1.min.js"></script>
<script src="js/thuvien/bootstrap.min.js"></script>
<script src="js/thuvien/main.js"></script>
<script>
    window.addEventListener('scroll', () => {
        var header = document.querySelector('.container-2')
        header.classList.toggle('sticky', window.scrollY > 100)
    })
</script>
<script>
    $(document).ready(function () {
        $("#formAcount").validate({
            rules: {
                fullname: {
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },
                cityID: {
                    required: true
                },
                district: {
                    required: true
                },
                ward: {
                    required: true
                },
                address: {
                    required: true
                }
            },
            messages: {
                fullname: {
                    required: "Xin vui lòng nhập tên"
                },
                email: {
                    required: "Xin vui lòng nhập email",
                    email: "Email không hợp lệ, xin vui lòng nhập lại"
                },
                cityID: {
                    required: "Xin chọn tỉnh/thành phố"
                },
                district: {
                    required: "Xin vui lòng nhập quận/huyện"
                },
                ward: {
                    required: "Xin vui lòng nhập xã/ phường/ thị trấn"
                },
                address: {
                    required: "Xin vui lòng nhập địa chỉ"
                }
            }
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>$(document).ready(function () {
    $(".formAcount").on("submit", function (event) {
        event.preventDefault();

        var formData = $(this).serialize();

        $.ajax({
            type: "POST",
            url: "updateinfouser",
            data: formData,
            dataType: "json",
            success: function (response) {
                if (response.status === "success") {
                    Swal.fire({
                        title: "Thành công!",
                        text: response.message,
                        icon: "success",
                        confirmButtonText: "OK"
                    }).then(() => {
                        // location.reload();
                    });
                } else {
                    Swal.fire({
                        title: "Lỗi!",
                        text: response.message,
                        icon: "error",
                        confirmButtonText: "OK"
                    });
                }
            },
            error: function () {
                Swal.fire({
                    title: "Lỗi!",
                    text: "Có lỗi xảy ra, vui lòng thử lại!",
                    icon: "error",
                    confirmButtonText: "OK"
                });
            }
        });
    });
});
</script>



<script>
    const loader = document.querySelector(".overlay_loader");
    document.getElementById("file").addEventListener("change", function(event) {
        let file = event.target.files[0];
        if (file) {
            let formData = new FormData();
            formData.append("file", file);
            loader.style.display = "flex";

            fetch("/updateAvatar", {
                method: "POST",
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    loader.style.display = "none";
                    if (data.status === "success") {
                        document.querySelector(".img-ava img").src = data.avatarUrl;
                        Swal.fire({
                            title: "Thành công!",
                            text: "Ảnh đại diện đã được cập nhật!",
                            icon: "success",
                            confirmButtonText: "OK"
                        });
                    } else {
                        Swal.fire({
                            title: "Lỗi!",
                            text: data.message,
                            icon: "error",
                            confirmButtonText: "OK"
                        });
                    }
                })
                .catch(error => console.error("Lỗi:", error));
        }
    });

</script>

<script>
    $(document).ready(function () {
        $("input[name='newpass']").on("input", function () {
            const password = $(this).val();
            $.post("/api/users/validate-password", { password: password }, function (res) {
                const $errorContainer = $("input[name='newpass']").next("p");
                $errorContainer.remove(); // xóa lỗi cũ

                if (!res.valid) {
                    const errorMsg = "<p style='color:red'>" + res.errors[0] + "</p>";
                    $("input[name='newpass']").after(errorMsg);
                }
            }, "json");
        });

        $("input[name='renewpass']").on("input", function () {
            const password = $("input[name='newpass']").val();
            const confirmPassword = $(this).val();
            const $errorContainer = $(this).next("p");
            $errorContainer.remove(); // xóa lỗi cũ

            if (password !== confirmPassword) {
                $(this).after("<p class='text-danger' style='color:red'>Mật khẩu xác nhận không trùng khớp.</p>");
            }
        });
    });
</script>
<script>$(document).ready(function () {
    $(".formAcount_resetPass").on("submit", function (event) {
        event.preventDefault();

        var formData = $(this).serialize();

        $.ajax({
            type: "POST",
            url: "resetpassword",
            data: formData,
            dataType: "json",
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            success: function (response) {
                // Xóa thông báo lỗi cũ nếu có
                $(".formAcount_resetPass p.text-danger").remove();
                $("input[name='pass'], input[name='newpass'], input[name='renewpass']").each(function () {
                    $(this).next("p").remove();
                });


                if (response.status === "success") {
                    Swal.fire({
                        title: "Thành công!",
                        text: response.message,
                        icon: "success",
                        confirmButtonText: "OK"
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = "updateinfouser";
                        }
                    });
                } else {
                    // Nếu có lỗi cụ thể từ server thì hiển thị chúng
                    if (response.errors) {
                        if (response.errors.oldPassError) {
                            $("input[name='pass']").after("<p class='text-danger' style='color:red'>" + response.errors.oldPassError + "</p>");
                        }
                        if (response.errors.newPassError) {
                            $("input[name='newpass']").after("<p class='text-danger' style='color:red'>" + response.errors.newPassError + "</p>");
                        }
                        if (response.errors.reNewPassError) {
                            $("input[name='renewpass']").after("<p class='text-danger' style='color:red'>" + response.errors.reNewPassError + "</p>");
                        }
                    }

                    // Popup chung báo lỗi
                    Swal.fire({
                        title: "Lỗi!",
                        text: response.message,
                        icon: "error",
                        confirmButtonText: "OK"
                    });
                }
            },
            error: function () {
                Swal.fire({
                    title: "Lỗi!",
                    text: "Có lỗi xảy ra, vui lòng thử lại!",
                    icon: "error",
                    confirmButtonText: "OK"
                });
            }
        });

    });
});
</script>


</body>
</html>