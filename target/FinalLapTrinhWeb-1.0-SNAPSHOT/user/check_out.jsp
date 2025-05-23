  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <%@ page import="com.example.finallaptrinhweb.model.Util" %>
  <%@ page import="com.example.finallaptrinhweb.model.Cart" %>
  <%@ page import="com.example.finallaptrinhweb.model.CartItem" %>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%
    Cart cart = (Cart) session.getAttribute("cart");
  %>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/thuvien/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="css/thuvien/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/thuvien/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/cart/checkout.css" type="text/css" />
    <link rel="stylesheet" href="css/header&footer.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Checkout</title>

  </head>
  <body>
  <jsp:include page="header.jsp"/>
  <div class="page-title" style="
              background-image: url(https://tienthangvet.vn/wp-content/uploads/title-tag-tien-thang-vet-tsd1.jpg);
            ">
    <div class="container">
      <h1 class="title">Thanh toán</h1>
    </div>
  </div>
  <section class="checkout spad">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <h6>
            <span class="icon_tag_alt"></span> Đã có mã giảm giá?
            <a href="cart">nhấn tại đây</a> để lấy mã giảm giá
          </h6>
        </div>
      </div>
      <h4>Chi tiết đơn hàng</h4>
      <form id="checkoutForm" action="order-handle"  method="post">
        <div class="row">
          <div class="col-lg-8 col-md-6">
            <div class="row">
              <div class="col-lg-6">
                <div class="checkout__input">
                  <p>Họ và tên đệm<span>*</span></p>
                  <input type="text" id="firstName" name="firstName" required />
                  <span class="error-message"></span>
                </div>
              </div>
              <div class="col-lg-6">
                <div class="checkout__input">
                  <p>Tên<span>*</span></p>
                  <input type="text" id="lastName" name="lastName" required />
                  <span class="error-message"></span>
                </div>
              </div>
            </div>

            <div class="checkout__input">
              <p>Tỉnh / Thành phố<span>*</span></p>
              <select class="select-location" id="city" name="city" required></select>
              <span class="error-message"></span>
            </div>

            <div class="checkout__input">
              <p>Huyện / Quận<span>*</span></p>
              <select class="select-location" id="district" name="district" required></select>
              <span class="error-message"></span>
            </div>

            <div class="checkout__input">
              <p>Địa chỉ<span>*</span></p>
              <input type="text" id="addressLine1" name="addressLine1" placeholder="Số nhà / Đường" class="checkout__input__add" required />
              <span class="error-message"></span>

              <select class="select-location" id="addressLine2" name="addressLine2" required>
                <option value="">Chọn Xã / Phường / Thị trấn</option>
              </select>
              <span class="error-message"></span>
            </div>

            <div class="row">
              <div class="col-lg-6">
                <div class="checkout__input">
                  <p>Điện thoại<span>*</span></p>
                  <input type="number" id="phoneNumber" name="phoneNumber" required />
                  <span class="error-message"></span>
                </div>
              </div>
              <div class="col-lg-6">
                <div class="checkout__input">
                  <p>Email<span>*</span></p>
                  <input type="email" id="email" name="email" required />
                  <span id="emailErrorMessage" class="error-message"></span>
                </div>
              </div>
            </div>
            <div class="checkout__input">
              <p>Mã giảm</p>
              <input type="text" id="discount" name="discount" />
            </div>
          </div>
          <div class="col-lg-4 col-md-6">
            <div class="checkout__order">
            <div >
              <h2>Thông tin sản phẩm trong giỏ hàng:</h2>
              <input  name="quantity" style="display: none" id="quantity" />
              <c:forEach var="item" items="${cart.products.values()}">
                <div class="checkout__order__subtotal">
                  <p>Tên sản phẩm: ${item.product.productName}</p>
                  <p>Giá bán: ${ Util.formatCurrency(item.product.price) } VND</p>
  <%--                <p>Số lượng: ${ item.quantity }</p>--%>

                </div>
              </c:forEach>
              <div class="checkout__order__subtotal">
                <p id="totalAmount"></p>
                <input  name="totalAmount" style="display: none;" id="totalAmount-input"/>
              </div>
              <div class="checkout__order__subtotal">
                <p  id="fee">Phí vận chuyển: 0 VND</p>
                <input name="fee"  style="display: none;" id="fee-input"/>
              </div>
              <div class="checkout__order__subtotal">
                <p  id="discount-fee">Giảm giá: 0 VND</p>
                <input name="discounts_id"  style="display: none;" id="discount-fee-input"/>
              </div>
              <div class="checkout__order__total">
                <p id="totalPayment" style="color: red;">Tổng tiền thanh toán: 0 VND</p>
              </div>
            </div>
            <div class="checkout__input__checkbox" >
              <span  class="payment-validation-message"></span>
              <label for="cash">
                Cash on delivery (COD)
                <input type="checkbox" id="cash" name="cash" value="COD" class="payment-option" />
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="checkout__input__checkbox">
              <label for="momo">
                MOMO
                <input type="checkbox" id="momo" name="momo" class="payment-option" data-toggle="collapse" data-target="#momo-code" />
                <span class="checkmark"  ></span>
              </label>
            </div>

            <div>
              <form id="paymentValidationMessage" class="payment-validation-message">
                <!-- ... Form content ... -->
                <button id="validateAndSubmitBtn" type="button" type="submit"  class="site-btn">Đặt hàng</button>
              </form>
            </div>
          </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal" id="orderSuccessModal" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Đặt hàng thành công!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <p>Cảm ơn bạn đã đặt hàng! Chúng tôi sẽ liên hệ với bạn sớm nhất có thể.</p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
              </div>
            </div>
          </div>
        </div>

        <!-- Modal for Momo QR Code -->
        <div class="modal" id="momoQrCodeModal" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Momo QR Code</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <!-- Momo QR Code Image -->
                <img id="momoQrCode" src="assets/img/qr_code/qr_code1.jpg" alt="Mã QR Momo" style="max-width: 100%; height: auto; display: block; margin: 0 auto;"/>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
              </div>
            </div>
          </div>
        </div>

      </form>
    </div>
  </section>



  </div>
  <script src="js/thuvien/jquery-3.3.1.min.js"></script>
  <script src="js/thuvien/bootstrap.min.js"></script>
  <script src="js/thuvien/main.js"></script>
  <style>
    .error-message{
      color: red;
      font-size: 12px;
    }
  </style>

  <!-- Thêm vào cuối thẻ head -->
  <script>

    function validateForm() {
      // Reset tất cả các thông báo lỗi
      $(".error-message").text("");


      // Kiểm tra và hiển thị thông báo lỗi
      var isValid = true;

      if ($("#firstName").val().trim() === "") {
        $("#firstName + .error-message").text("Họ và tên đệm không được để trống");
        isValid = false;
      }

      if ($("#lastName").val().trim() === "") {
        $("#lastName + .error-message").text("Tên không được để trống");
        isValid = false;
      }

      if ($("#addressLine1").val().trim() === "") {
        $("#addressLine1 + .error-message").text("Địa chỉ không được để trống");
        isValid = false;
      }

      if ($("#addressLine2").val().trim() === "") {
        $("#addressLine2 + .error-message").text("Địa chỉ không được để trống");
        isValid = false;
      }

      if ($("#district").val().trim() === "") {
        $("#district + .error-message").text("Huyện / Quận không được để trống");
        isValid = false;
      }

      if ($("#city").val().trim() === "") {
        $("#city + .error-message").text("Tỉnh / Thành phố không được để trống");
        isValid = false;
      }

      if ($("#phoneNumber").val().trim() === "") {
        $("#phoneNumber + .error-message").text("Điện thoại không được để trống");
        isValid = false;
      }

      var emailRegex = /\S+@\S+\.\S+/;
      if (!emailRegex.test($("#email").val().trim())) {
        $("#emailErrorMessage").text("Email không hợp lệ");
        isValid = false;
      }

      return isValid;
    }

    $(document).ready(function () {
      $("#validateAndSubmitBtn").click(function () {
        var isValidForm = validateForm(); // Kiểm tra hợp lệ của form

        if (isValidForm) {
          var isMomoChecked = $("#momo").prop("checked");
          var isCashChecked = $("#cash").prop("checked");

          if (!isMomoChecked && !isCashChecked) {
            // Hiển thị thông báo đỏ nếu form không hợp lệ
            $(".payment-validation-message").text("Vui lòng chọn một phương thức thanh toán (COD hoặc MOMO).").show();
          } else if (isCashChecked) {
            $("#checkoutForm").submit()
          } else if (isMomoChecked) {
            // Nếu đã chọn Momo, hiển thị mã QR code
            // $("#momoQrCodeModal").modal("show");

            const totalAmount = document.getElementById("totalPayment").dataset.id
            console.log("totalAmount: " + totalAmount)
            console.log(totalAmount)
            fetch("http://localhost:8080/FinalLapTrinhWeb_war/payment-momo", {
              method: "POST",
              headers: {
                "Content-Type": "application/x-www-form-urlencoded"
              },
              body: `amount=${totalAmount}`
            });

          }
        }
      });

      $("#momo").change(function () {
        if ($(this).prop("checked")) {
          $("#cash").prop("checked", false);
          $(".payment-validation-message").hide();
        }
      });

      $("#cash").change(function () {
        if ($(this).prop("checked")) {
          $("#momo").prop("checked", false);
          $(".payment-validation-message").hide();
        }
      });

      $("#orderSuccessModal").on("hidden.bs.modal", function () {
        $("#checkoutForm").submit();
      });

      // Xử lý sự kiện hidden.bs.modal của momoQrCodeModal
      $("#momoQrCodeModal").on("hidden.bs.modal", function () {
        $("#checkoutForm").submit();
      });


      // Thêm sự kiện change cho email để ẩn thông báo lỗi khi người dùng sửa nội dung
      $("#email").change(function () {
        $("#emailErrorMessage").text("");
      });
    });
  </script>
  <script>

  </script>


  <jsp:include page="footer.jsp"/>
  <script src="js/checkout/fee.js"></script>
  </body>

  </html>