<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Teacher Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-success text-white">
            <h4>Welcome, ${user.role}!</h4>
        </div>
        <div class="card-body">
            <p><strong>Email:</strong> ${email}</p>

            <h5>Courses You're Teaching</h5>
            <ul class="list-group">
                <!-- Placeholder - replace with dynamic list -->
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    Java Web Development
                    <a href="/courses/edit/1" class="btn btn-sm btn-outline-secondary">Edit</a>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    Spring Framework
                    <a href="/courses/edit/2" class="btn btn-sm btn-outline-secondary">Edit</a>
                </li>
            </ul>

            <a href="/courses/create" class="btn btn-primary mt-3">+ Create New Course</a>
            <hr>
            <a href="/logout" class="btn btn-danger mt-3">Logout</a>
        </div>
    </div>
</div><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - HSF</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            min-height: 100vh;
        }
        .sidebar {
            min-height: 100vh;
            background-color: #343a40;
            color: white;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
            padding: 10px 15px;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">

        <!-- Sidebar -->
        <div class="col-md-2 sidebar">
            <h4 class="text-center py-3">HSF Dashboard</h4>
            <a href="#">🏠 Trang chủ</a>
            <a href="#">📚 Khóa học</a>
            <a href="#">🧑‍💼 Hồ sơ</a>
            <a href="#">⚙️ Cài đặt</a>
            <form action="/logout" method="post" class="mt-3 px-3">
                <button type="submit" class="btn btn-danger w-100">Đăng xuất</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">
            <h2>Chào mừng bạn đến với hệ thống học lập trình HSF!</h2>
            <p class="text-muted">Hôm nay bạn muốn học gì?</p>

            <div class="row mt-4">
                <div class="col-md-4">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Khóa học đang học</h5>
                            <p class="card-text">Java Spring Boot từ cơ bản đến nâng cao.</p>
                            <a href="#" class="btn btn-primary">Tiếp tục học</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Tiến độ học tập</h5>
                            <p class="card-text">Bạn đã hoàn thành 30% khóa học hiện tại.</p>
                            <a href="#" class="btn btn-outline-primary">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Thông báo</h5>
                            <p class="card-text">Bạn có 2 thông báo mới từ giảng viên.</p>
                            <a href="#" class="btn btn-outline-secondary">Xem thông báo</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

</body>
</html>
