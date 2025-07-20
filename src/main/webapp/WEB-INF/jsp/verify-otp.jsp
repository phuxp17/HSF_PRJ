<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Xác thực OTP - HSF</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto" style="max-width: 400px;">
        <div class="card-body">
            <h4 class="text-center mb-3">Xác thực mã OTP</h4>

            <form action="/verify-otp" method="post">
                <input type="email" name="email" class="form-control mb-2" placeholder="Email" required/>
                <input type="text" name="otp" class="form-control mb-2" placeholder="Nhập mã OTP" required/>
                <button type="submit" class="btn btn-primary w-100">Xác thực</button>
            </form>

            <div class="text-center mt-3">
                <form action="/send-otp" method="post">
                    <input type="hidden" name="email" value="${param.email}"/>
                    <button type="submit" class="btn btn-link">Gửi lại mã OTP</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
