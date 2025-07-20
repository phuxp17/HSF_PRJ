<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đặt lại mật khẩu - HSF</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto" style="max-width: 400px;">
        <div class="card-body">
            <h4 class="text-center mb-3">Đặt lại mật khẩu</h4>

            <form action="/reset-password" method="post">
                <input type="hidden" name="token" value="${param.token}" />
                <input type="password" name="newPassword" class="form-control mb-3" placeholder="Nhập mật khẩu mới" required/>
                <button type="submit" class="btn btn-success w-100">Cập nhật mật khẩu</button>
            </form>

            <div class="text-center mt-3">
                <a href="/login" class="text-decoration-none">Về trang đăng nhập</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
