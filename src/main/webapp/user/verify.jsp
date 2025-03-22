<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/sign/form.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="https://tienthangvet.vn/wp-content/uploads/cropped-favicon-Tien-Thang-Vet-192x192.png"
          sizes="192x192"/>
    <title>Đăng nhập</title>
</head>

<body>
<%
    Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
    if (failedAttempts == null) failedAttempts = 0;

    Long lockTime = (Long) session.getAttribute("lockTime");
    boolean isLocked = false;
    long remainingTime = 0;

    if (lockTime != null) {
        long elapsedTime = System.currentTimeMillis() - lockTime;
        if (elapsedTime >= 5 * 60 * 1000) {
            session.removeAttribute("lockTime");
            session.setAttribute("failedAttempts", 0);
        } else {
            isLocked = true;
            remainingTime = (5 * 60 * 1000) - elapsedTime;
        }
    }
%>

<div class="website-wrapper">
    <jsp:include page="header.jsp"/>

    <div class="body">
        <div class="form-container">
            <form class="sign-in-form" method="post" action="verify">
                <h2>Xác thực</h2>
                <% String error = (String) request.getAttribute("wrongAuthCode"); %>
                <p id="errorMsg" style="color: red; margin-bottom: 10px;"><%= error != null ? error : "" %></p>

                <% if (!isLocked && error == null && failedAttempts > 0) { %>
                <p style="color: blue; margin-bottom: 10px;">Bạn còn <%= 4 - failedAttempts %> lần thử.</p>
                <% } %>

                <div class="input-group">
                    <input type="text" id="verifycode" name="verifycode" placeholder="Vui lòng nhập mã xác thực" required <% if (isLocked) { %>disabled<% } %>>
                </div>
                <button id="submitBtn" <% if (isLocked) { %>disabled<% } %>>Gửi</button>

                <% if (isLocked) { %>
                <p id="countdown" style="color: red; margin-top: 10px;"></p>
                <% } %>
            </form>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</div>

<script>
    let isLocked = <%= isLocked %>;
    let remainingTime = <%= remainingTime %>;

    if (isLocked) {
        let countdownElement = document.getElementById("countdown");
        let inputField = document.getElementById("verifycode");
        let submitButton = document.getElementById("submitBtn");
        let errorMsg = document.getElementById("errorMsg");

        function updateCountdown() {
            let minutes = Math.floor(remainingTime / 60000);
            let seconds = Math.floor((remainingTime % 60000) / 1000);
            countdownElement.textContent = "Vui lòng thử lại sau " + minutes + " phút " + seconds + " giây.";

            if (remainingTime <= 0) {
                countdownElement.textContent = "";
                inputField.removeAttribute("disabled");
                submitButton.removeAttribute("disabled");
                errorMsg.textContent = "";
                clearInterval(timer);
            }

            remainingTime -= 1000;
        }

        updateCountdown();
        let timer = setInterval(updateCountdown, 1000);
    }
</script>


<script src="js/sign/scipts.js"></script>

</body>

</html>