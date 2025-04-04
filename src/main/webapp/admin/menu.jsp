
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!-- Header -->
<div class="header">
    <div class="header-left">
        <a href="dashboard" class="logo logo-small">
            <img src="assets/img/logo-icon.png" alt="Logo" width="30" height="30">
        </a>
    </div>
    <a href="javascript:void(0);" id="toggle_btn">
        <i class="fas fa-align-left"></i>
    </a>
    <a class="mobile_btn" id="mobile_btn" href="javascript:void(0);">
        <i class="fas fa-align-left"></i>
    </a>

    <ul class="nav user-menu">

        <!-- User Menu -->
        <li class="nav-item dropdown">
            <a href="javascript:void(0)" class="dropdown-toggle user-link  nav-link" data-toggle="dropdown">
						<span class="user-img">
							<img class="rounded-circle" src="assets/img/user.jpg" width="40"
                                 alt="Admin">
						</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right">
                <a class="dropdown-item" href="">Thông tin cá nhân</a>
                <a class="dropdown-item" href="signout">Đăng xuất</a>
            </div>
        </li>
        <!-- /User Menu -->

    </ul>
</div>
<!-- /Header -->

<!-- Sidebar -->
<div class="sidebar" id="sidebar" style="height: auto">
    <div class="sidebar-logo">
        <a href="dashboard">
            <img src="assets/img/logo-icon.png" class="img-fluid" alt="">
        </a>
    </div>
    <div class="sidebar-inner slimscroll">
        <div id="sidebar-menu" class="sidebar-menu">
            <ul>
                <li>
                    <a href="dashboard"
                       <c:if test="${current_page=='dashboard'}">class="active"</c:if>>
                        <i class="fas fa-columns"></i> <span>Dashboard</span></a>
                </li>
                <li class="submenu">
                    <a href="#"><i class="fas fa-tasks"></i> <span>Quản lí</span> <span class="menu-arrow"></span></a>
                    <ul style="display: none;">
                        <li><a href="category"
                               <c:if test="${current_page=='category'}">class="active"</c:if>>Danh mục</a></li>
                        <li><a href="product"
                               <c:if test="${current_page=='product'}">class="active"</c:if>>Sản phẩm</a></li>
                        <li><a href="coupon-code"
                               <c:if test="${current_page=='coupon-code'}">class="active"</c:if>>Mã giảm giá</a></li>
                    </ul>
                </li>

                <li class="submenu">
                    <a href="#"><i class="fas fa-shopping-cart"></i><span>Bán hàng</span> <span
                            class="menu-arrow"></span></a>
                    <ul style="display: none;">
                        <li><a href="total-report"
                               <c:if test="${current_page=='total-report'}">class="active"</c:if>>Danh sách đặt hàng</a>
                        </li>
                    </ul>
                </li>

                <li class="submenu">
                    <a href="#"><i class="fas fa-users"></i><span>Người dùng</span> <span class="menu-arrow"></span></a>
                    <ul style="display: none;">
                        <li><a href="users"
                               <c:if test="${current_page=='users'}">class="active"</c:if>>Khách hàng</a></li>
                        <li><a href="list-admin"
                               <c:if test="${current_page=='admin-list'}">class="active"</c:if>>Danh sách admin</a></li>
                            <li><a href="supplier"
                               <c:if test="${current_page=='supplier'}">class="active"</c:if>>Nhà cung cấp</a></li>
                        <li><a href="contact"
                               <c:if test="${current_page=='feedbackList'}">class="active"</c:if>>Ý kiến khách hàng</a></li>
                    </ul>
                </li>
                <li <c:if test="${current_page=='setting'}">class="active"</c:if>>
                    <a href="setting"><i class="fas fa-cog"></i> <span>Thiết lập</span></a>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- /Sidebar -->

