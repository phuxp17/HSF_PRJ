<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quên mật khẩu - HSF</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto" style="max-width: 400px;">
        <div class="card-body">
            <h4 class="text-center mb-3">Quên mật khẩu</h4>

            <form action="/forgot-password" method="post">
                <input type="email" name="email" class="form-control mb-3" placeholder="Nhập email để nhận link đặt lại mật khẩu" required/>
                <button type="submit" class="btn btn-warning w-100">Gửi liên kết đặt lại mật khẩu</button>
            </form>

            <div class="text-center mt-3">
                <a href="/login" class="text-decoration-none">Quay lại đăng nhập</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>