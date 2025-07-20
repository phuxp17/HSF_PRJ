<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký - HSF</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto" style="max-width: 500px;">
        <div class="card-body">
            <h4 class="text-center mb-3">Đăng ký tài khoản</h4>

            <form action="/register" method="post">
                <input type="text" name="firstName" class="form-control mb-2" placeholder="Họ" required/>
                <input type="text" name="lastName" class="form-control mb-2" placeholder="Tên" required/>
                <input type="text" name="phoneNumber" class="form-control mb-2" placeholder="Số điện thoại" required/>
                <input type="email" name="email" class="form-control mb-2" placeholder="Email" required/>
                <input type="password" name="password" class="form-control mb-2" placeholder="Mật khẩu" required/>

                <button type="submit" class="btn btn-success w-100">Đăng ký</button>
            </form>

            <div class="text-center mt-3">
                <a href="/login" class="text-decoration-none">Đã có tài khoản? Đăng nhập</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
