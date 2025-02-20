
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/contact/contact.css">
    <link rel="stylesheet" href="css/HomePage/styles.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png" sizes="192x192" />
    <link rel="stylesheet" href="css/header&footer.css" />
    <title>Liên hệ</title>
</head>

<body>
<div class="website-wrapper">
    <jsp:include page="header.jsp"/>
    <!-- Map Begin -->
    <div class="body">
        <div class="col-lg-12">
            <div class="map">
                <iframe
                        src="${applicationScope.address.get(0).map}"
                        height="500" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
            </div>
            <!-- Map End -->
            <!-- From Begin-->
            <div class="elementor-container">
                <div class="elementor-column1 elementor-col-50">
                    <div class="elementor-widget-wrap1">
                        <div class="elementor-element">
                            <div class="elementor-widget-container">
                                <h2 class="elementor-heading-titler">ĐÓNG GÓP Ý KIẾN</h2>
                            </div>
                        </div>
                        <div class="elementor-element elementor">
                            <div class="elementor-widget-container">
                                 <form class="elementor-form" method="post" action="${pageContext.request.contextPath}/user/feedback">
                                    <div class="elementor-field-type-text">
                                        <label for="form-name" class="elementor-label">
                                            Họ tên </label>
                                        <input size="1" type="text" name="name" id="form-name" class="elementor-field"
                                               placeholder="Họ tên" required="required" aria-required="true">
                                    </div>
                                    <div class="elementor-field-type-email">
                                        <label for="form-email" class="elementor-label">
                                            Email </label>
                                        <input size="1" type="email" name="email" id="form-email" class="elementor-field"
                                               placeholder="Email" required="required" aria-required="true">
                                        <c:if test="${not empty emailError}">
                                            <div id="emailError" class="error-message" style="color: red;">${emailError}</div>
                                        </c:if>
                                    </div>
                                     <div class="elementor-field-type-textarea">
                                        <label for="form-message" class="elementor-label">
                                            Đóng góp ý kiến </label>
                                        <textarea class="elementor-field-textual" name="form_fields[message]"
                                                  id="form-message" rows="4" placeholder="Nội dung đóng góp" required="required"
                                                  aria-required="true"></textarea>
                                         <c:if test="${not empty contentError}">
                                             <div id="contentError" class="error-message" style="color: red;">${contentError}</div>
                                         </c:if>
                                    </div>
                                    <div class="elementor-field-group">
                                        <button type="submit" class="elementor-button" onclick="showErrorMessage()">
                                        <span>
                                            <span class="elementor-button-icon">
                                            </span>
                                            <span class="elementor-button-text">Gửi</span>
                                        </span>
                                        </button>
                                    </div>
                                </form>
                                <c:if test="${not empty feedbackMessage}">
                                    <div id="feedbackMessage" class="feedback-message" style="color: Green;">${feedbackMessage}</div>
                                </c:if>
                                <c:if test="${not empty errorMessage}">
                                    <div id="errorMessage" class="error-message"  style="color: red;">${errorMessage}</div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="elementor-column1 elementor-col-50">
                    <div class="elementor-widget-wrap2">
                        <div class="elementor-element">
                            <div class="elementor-widget-container ">
                                <div class="title-wrapper">
                                    <div class="liner-continer">
                                        <h2 class="woodmart-title-container">THÔNG TIN LIÊN HỆ</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <section class="wd-negative-gap">
                            <div class="elementor-container at">
                                <div class="elementor-column1 elementor-col-50">
                                    <div class="elementor-widget-wrapd">
                                        <div class="elementor-element">
                                            <div class="elementor-widget-container">
                                                <div class="info-box-wrapper">
                                                    <div class="wd-info-box">
                                                        <div class="info-box-content">
                                                            <div class="info-box-inner"
                                                                 data-elementor-setting-key="content">
                                                                <p><span style="color: #333333;"><strong>Địa
                                                                        chỉ:</strong></span> ${applicationScope.address.get(0).address}</p>
                                                                <p><span style="color: #333333;"><strong>Điện
                                                                        thoại:</strong></span> 0${applicationScope.address.get(0).phone}</p>
                                                                <p><span
                                                                        style="color: #333333;"><strong>Email:</strong></span>
                                                                    ${applicationScope.address.get(0).email}</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="elementor-column1">
                                    <div class="elementor-widget-wrap">
                                        <div class="elementor-element">
                                            <div class="elementor-widget-container">
                                                <div class="info-box-wrapper">
                                                    <div class="wd-info-box ">
                                                        <div class="info-box-content">
                                                            <div class="info-box-inner ">
                                                                <p><b>THỜI GIAN LÀM VIỆC</b></p>
                                                                <p>Thứ 2 - Thứ 6: 08h00 - 17h00</p>
                                                                <p>Thứ bảy: Nghỉ</p>
                                                                <p>Chủ nhật: Nghỉ</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <div class="icon-infor d-flex px-5 py-5 my-3 border-top justify-content-center">
                            <a href="${applicationScope.social_media.facebook}" class="icon-link"><span><i class="fab fa-facebook-f fa-lg"></i></span></a>
                            <a href="${applicationScope.social_media.twitter}" class="icon-link"><span><i class="fab fa-twitter fa-lg"></i></span></a>
                            <a href="${applicationScope.social_media.instagram}" class="icon-link"><span><i class="fab fa-instagram fa-lg"></i></span></a>
                            <a href="${applicationScope.social_media.youtube}" class="icon-link"><span><i class="fab fa-youtube fa-lg"></i></span></a>
                            <a href="${applicationScope.social_media.pinterest}" class="icon-link"><span><i class="fab fa-pinterest fa-lg"></i></span></a>
                            <a href="${applicationScope.social_media.linkedin}"class="icon-link"><span><i class="fab fa-linkedin fa-lg"></i></span></a>
                        </div>


                    </div>
                </div>

                <!-- Contact Form End -->
            </div>
        </div>

    </div>
    <jsp:include page="footer.jsp"/>


    <script>
        // Ẩn thông báo lỗi khi trang được tải
        window.onload = function() {
            var errorMessage = document.getElementById('errorMessage');
            errorMessage.innerHTML = "${emailError}";
            errorMessage.style.display = '${not empty emailError ? "block" : "none"}';

            var contentError = document.getElementById('contentError');
            contentError.innerHTML = "${contentError}";
            contentError.style.display = '${not empty contentError ? "block" : "none"}';
        }

        // Hàm ẩn thông báo lỗi
        function hideErrorMessage() {
            var errorMessage = document.getElementById('errorMessage');
            errorMessage.style.display = 'none';

            var contentError = document.getElementById('contentError');
            contentError.style.display = 'none';
        }
    </script>


    <script>
        // Ẩn thông báo lỗi khi trang được tải
        window.onload = function() {
            var errorMessage = document.getElementById('errorMessage');
            errorMessage.style.display = 'none';
        }

        // Hàm hiển thị thông báo lỗi khi cần thiết
        function showErrorMessage() {
            var errorMessage = document.getElementById('errorMessage');
            errorMessage.style.display = 'block';
        }
    </script>




    <script>
        window.addEventListener('scroll', () => {
            var header = document.querySelector('.container')
            header.classList.toggle('sticky', window.scrollY > 100)
        })
    </script>
</div>
</body>

</html>
