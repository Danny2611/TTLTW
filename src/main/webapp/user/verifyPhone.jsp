<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xác thực số điện thoại</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <!-- Firebase JS SDK -->
    <script src="https://www.gstatic.com/firebasejs/9.19.1/firebase-app-compat.js"></script>
    <script src="https://www.gstatic.com/firebasejs/9.19.1/firebase-auth-compat.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center">Xác thực số điện thoại</h3>
                </div>
                <div class="card-body">
                    <% if (request.getAttribute("wrongAuthCode") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("wrongAuthCode") %>
                    </div>
                    <% } %>
                    <% if (request.getAttribute("success") != null) { %>
                    <div class="alert alert-success" role="alert">
                        <%= request.getAttribute("success") %>
                    </div>
                    <div class="text-center">
                        <a href="login.jsp" class="btn btn-primary">Đăng nhập ngay</a>
                    </div>
                    <% } else { %>
                    <div id="recaptcha-container"></div>
                    <div id="verification-step-1">
                        <p class="text-center">Vui lòng nhấn gửi mã xác thực để lấy mã xác thực cho tài khoản của bạn</p>
                        <div class="input-group mb-3">
                            <span class="input-group-text">+84</span>
                            <input type="tel" id="phone-display" class="form-control" placeholder="Số điện thoại" value="<%= ((String)session.getAttribute("phoneNumber")).startsWith("0") ? ((String)session.getAttribute("phoneNumber")).substring(1) : (String)session.getAttribute("phoneNumber") %>" disabled>
                            <button class="btn " style="background-color: #66b840;  color: #fff; font-weight: 600;transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(102, 184, 64, 0.2);" id="send-code-btn">Gửi mã xác thực</button>
                        </div>
                    </div>
                    <div id="verification-step-2" style="display: none;">
                        <p class="text-center">Nhập mã xác thực được gửi đến số điện thoại của bạn</p>
                        <div class="form-group mb-3">
                            <input type="text" id="verification-code" class="form-control" placeholder="Mã xác thực (6 chữ số)">
                        </div>
                        <div class="d-grid gap-2">
                            <button class="btn " style="background-color: #66b840;  color: #fff; font-weight: 600;transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(102, 184, 64, 0.2);" id="verify-code-btn">Xác thực</button>
                        </div>
                        <p class="text-center mt-3">
                            <span id="countdown">60</span>s
                            <button class="btn btn-link" id="resend-code-btn" disabled>Gửi lại mã</button>
                        </p>
                    </div>
                    <div id="result-message" class="alert mt-3" style="display: none;"></div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Firebase configuration - Thay thế bằng config của bạn
    const firebaseConfig = {
        apiKey: "AIzaSyCWc8ErrhlZPnj1xmYlifq75sHBSD5dQPY",
        authDomain: "web-thu-y-f0be1.firebaseapp.com",
        projectId: "web-thu-y-f0be1",
        storageBucket: "web-thu-y-f0be1.firebasestorage.app",
        messagingSenderId: "187288736906",
        appId: "1:187288736906:web:802db9e5b8e63f67e74973",
        measurementId: "G-S8659Y55H0"
    };


    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);

    // Phần xử lý xác thực
    document.addEventListener('DOMContentLoaded', function() {
        const phoneNumber = '<%= (String)session.getAttribute("phoneNumber") %>';
        let formattedPhone = phoneNumber.startsWith('0') ? '+84' + phoneNumber.substring(1) : '+84' + phoneNumber;

        const auth = firebase.auth();
        let verificationId = '';
        let verifier = new firebase.auth.RecaptchaVerifier('recaptcha-container', {
            'size': 'normal',
            'callback': function(response) {
                document.getElementById('send-code-btn').removeAttribute('disabled');
            }
        });

        // Gửi mã xác thực
        document.getElementById('send-code-btn').addEventListener('click', function() {
            const sendCodeBtn = this;
            sendCodeBtn.disabled = true;
            sendCodeBtn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Đang gửi...';

            auth.signInWithPhoneNumber(formattedPhone, verifier)
                .then(function(confirmationResult) {
                    verificationId = confirmationResult.verificationId;
                    document.getElementById('verification-step-1').style.display = 'none';
                    document.getElementById('verification-step-2').style.display = 'block';
                    startCountdown();
                })
                .catch(function(error) {
                    showError('Lỗi gửi mã xác thực: ' + error.message);
                    sendCodeBtn.disabled = false;
                    sendCodeBtn.textContent = 'Gửi lại mã';
                    verifier.clear();
                    verifier = new firebase.auth.RecaptchaVerifier('recaptcha-container', {
                        'size': 'normal'
                    });
                });
        });

        // Xác nhận mã OTP
        document.getElementById('verify-code-btn').addEventListener('click', function() {
            const verifyCodeBtn = this;
            const verificationCode = document.getElementById('verification-code').value.trim();

            if (!verificationCode || verificationCode.length !== 6) {
                showError('Vui lòng nhập mã xác thực 6 chữ số');
                return;
            }

            verifyCodeBtn.disabled = true;
            verifyCodeBtn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Đang xác thực...';

            const credential = firebase.auth.PhoneAuthProvider.credential(verificationId, verificationCode);
            auth.signInWithCredential(credential)
                .then(function(result) {
                    return result.user.getIdToken();
                })
                .then(function(idToken) {
                    // Gửi idToken đến server để xác minh
                    fetch('./verifyPhone', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                        },
                        body: 'idToken=' + encodeURIComponent(idToken)
                    })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                showSuccess(data.message);
                                setTimeout(() => {
                                    window.location.href = './signIn.jsp';
                                }, 2000);
                            } else {
                                showError(data.message);
                                verifyCodeBtn.disabled = false;
                                verifyCodeBtn.textContent = 'Xác thực';
                            }
                        })
                        .catch(error => {
                            showError('Lỗi kết nối đến server');
                            verifyCodeBtn.disabled = false;
                            verifyCodeBtn.textContent = 'Xác thực';
                        });
                })
                .catch(function(error) {
                    showError('Mã xác thực không đúng');
                    verifyCodeBtn.disabled = false;
                    verifyCodeBtn.textContent = 'Xác thực';
                });
        });

        // Gửi lại mã xác thực
        document.getElementById('resend-code-btn').addEventListener('click', function() {
            document.getElementById('verification-step-2').style.display = 'none';
            document.getElementById('verification-step-1').style.display = 'block';
            verifier.clear();
            verifier = new firebase.auth.RecaptchaVerifier('recaptcha-container', {
                'size': 'normal'
            });
        });

        function startCountdown() {
            const countdownEl = document.getElementById('countdown');
            const resendBtn = document.getElementById('resend-code-btn');
            let seconds = 60;

            const interval = setInterval(function() {
                seconds--;
                countdownEl.textContent = seconds;

                if (seconds <= 0) {
                    clearInterval(interval);
                    resendBtn.disabled = false;
                }
            }, 1000);
        }

        function showError(message) {
            const resultEl = document.getElementById('result-message');
            resultEl.className = 'alert alert-danger mt-3';
            resultEl.textContent = message;
            resultEl.style.display = 'block';
        }

        function showSuccess(message) {
            const resultEl = document.getElementById('result-message');
            resultEl.className = 'alert alert-success mt-3';
            resultEl.textContent = message;
            resultEl.style.display = 'block';
        }
    });
</script>
</body>
</html>