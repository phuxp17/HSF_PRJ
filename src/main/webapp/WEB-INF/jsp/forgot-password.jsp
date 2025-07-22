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

            <c:if test="${not empty success}">
                <div class="alert alert-success">${success}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form action="/forgot-password" method="post">
                <input type="email" name="email" class="form-control mb-3" placeholder="Nhập email" required/>
                <button type="submit" class="btn btn-primary w-100">Gửi yêu cầu</button>
            </form>

            <div class="text-center mt-3">
                <a href="/login" class="text-decoration-none">Về trang đăng nhập</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
