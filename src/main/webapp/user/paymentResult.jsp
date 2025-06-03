<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kết quả thanh toán</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .error {
            color: #e74c3c;
            font-size: 18px;
        }

        .success {
            color: #2ecc71;
            font-size: 18px;
        }

        .info {
            margin-top: 20px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            background-color: #3498db;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
        }

        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Kết quả thanh toán</h2>

    <%
        String status = (String) request.getAttribute("status");
        String message = (String) request.getAttribute("message");
        String orderId = (String) request.getAttribute("orderId");
        String resultCode = (String) request.getAttribute("resultCode");
    %>

    <div class="<%= "success".equals(status) ? "success" : "error" %>">
        <strong><%= message != null ? message : "Không có thông báo lỗi." %></strong>
    </div>

    <div class="info">
        <p><strong>Mã đơn hàng:</strong> <%= orderId != null ? orderId : "N/A" %></p>
        <p><strong>Mã kết quả:</strong> <%= resultCode != null ? resultCode : "N/A" %></p>
    </div>

    <a href="<%= request.getContextPath() %>/user/home">Quay lại trang chính</a>
</div>
</body>
</html>
