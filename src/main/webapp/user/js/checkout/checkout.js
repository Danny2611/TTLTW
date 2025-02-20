
    $(document).ready(function () {
    $("#validateAndSubmitBtn").click(function () {
        if ($("#cash").prop("checked") && !$("#momo").prop("checked")) {
            $("#checkoutForm").submit();
        } else if ($("#momo").prop("checked") && !$("#cash").prop("checked")) {
            $("#momo-payment").modal("show");
            startCountdownTimer();
        } else {
            $("#paymentValidationMessage").text("Vui lòng chọn một phương thức thanh toán (COD hoặc MOMO).").show();
        }
    });

    $("#momo").change(function () {
    if ($(this).prop("checked")) {
    $("#cash").prop("checked", false);
    $("#paymentValidationMessage").hide();
}
});

    $("#cash").change(function () {
    if ($(this).prop("checked")) {
    $("#momo").prop("checked", false);
    $("#paymentValidationMessage").hide();
}
});

    $("#checkoutForm").submit(function (event) {
    event.preventDefault();


    $("#orderSuccessModal").modal("show");
});


    function startCountdownTimer() {
    var countdownDuration = 60;

    var countdownElement = $("#count-down-time");

    var countdownInterval = setInterval(function () {

    countdownElement.text(countdownDuration);
    countdownDuration--;
    if (countdownDuration < 0) {
    clearInterval(countdownInterval);
    $("#momo-payment").modal("hide");
    alert("Time's up! Please try again.");
}
}, 1000);
}

});



    function validateAndSubmit() {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var addressLine1 = document.getElementById("addressLine1").value;
    var addressLine2 = document.getElementById("addressLine2").value;
    var city = document.getElementById("city").value;
    var district = document.getElementById("district").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var email = document.getElementById("email").value;

    // Kiểm tra các trường nhập dữ liệu
    if (firstName.trim() === "" || lastName.trim() === "" || addressLine1.trim() === "" ||
    addressLine2.trim() === "" || city.trim() === "" || district.trim() === "" ||
    phoneNumber.trim() === "" || email.trim() === "") {
    alert("Vui lòng điền đầy đủ thông tin.");
    return;
}

    // Kiểm tra hình thức thanh toán
    var cashChecked = document.getElementById("cash").checked;
    var momoChecked = document.getElementById("momo").checked;

    if (!cashChecked && !momoChecked) {
    alert("Vui lòng chọn một hình thức thanh toán (COD hoặc MOMO).");
    return;
}

    // Thêm logic kiểm tra cho các trường khác

    // Nếu validation thành công, có thể gửi form
    document.getElementById("checkoutForm").submit();
}

