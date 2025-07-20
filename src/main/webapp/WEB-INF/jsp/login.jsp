<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập - HSF</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto" style="max-width: 400px;">
        <div class="card-body">
            <h4 class="text-center mb-3">Đăng nhập</h4>

            <form action="/login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" name="email" class="form-control" required/>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Mật khẩu:</label>
                    <input type="password" name="password" class="form-control" required/>
                </div>
                <button type="submit" class="btn btn-primary w-100">Gửi mã OTP</button>
            </form>

            <div class="text-center mt-3">
                <a href="/forgot-password" class="text-decoration-none">Quên mật khẩu?</a><br>
                <a href="/register" class="text-decoration-none">Chưa có tài khoản? Đăng ký</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
