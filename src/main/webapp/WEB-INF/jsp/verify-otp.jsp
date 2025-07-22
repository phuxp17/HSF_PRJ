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

            <!-- Hiển thị lỗi nếu có -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <!-- Hiển thị thành công nếu có -->
            <c:if test="${not empty success}">
                <div class="alert alert-success">${success}</div>
            </c:if>

            <!-- Form xác thực OTP -->
            <form action="/verify-otp" method="post">
                <input type="hidden" name="email" value="${email}" />
                <input type="text" name="otp" class="form-control mb-3" placeholder="Nhập mã OTP" required />
                <button type="submit" class="btn btn-primary w-100">Xác thực</button>
            </form>

            <!-- Gửi lại mã OTP -->
            <div class="text-center mt-3">
                <form action="/send-otp" method="post">
                    <input type="hidden" name="email" value="${email}" />
                    <button type="submit" class="btn btn-link">Gửi lại mã OTP</button>
                </form>
            </div>

            <!-- Liên kết quay lại đăng nhập -->
            <div class="text-center mt-3">
                <a href="/login" class="text-decoration-none">← Quay lại trang đăng nhập</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
