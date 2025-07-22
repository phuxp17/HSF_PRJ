<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - HSF</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto" style="max-width: 420px;">
        <div class="card-body">
            <h4 class="text-center mb-4">Đăng nhập</h4>

            <%-- Hiển thị thông báo lỗi hoặc thành công nếu có --%>
            <c:if test="${not empty error}">
                <div class="alert alert-danger text-center">${error}</div>
            </c:if>
            <c:if test="${not empty success}">
                <div class="alert alert-success text-center">${success}</div>
            </c:if>

            <form action="/login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input id="email" type="email" name="email" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Mật khẩu:</label>
                    <input id="password" type="password" name="password" class="form-control" required />
                </div>

                <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
            </form>

            <div class="mt-3 text-center">
                <a href="/forgot-password" class="btn btn-link">Quên mật khẩu?</a><br>
                <a href="/register" class="btn btn-outline-secondary btn-sm mt-2">Chưa có tài khoản? Đăng ký</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
