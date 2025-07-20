<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HSF Learning Platform</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- HEADER -->
<header class="bg-primary text-white text-center py-5">
    <div class="container">
        <h1>Chào mừng đến với nền tảng học lập trình HSF</h1>
        <p class="lead">Nơi bắt đầu hành trình trở thành lập trình viên chuyên nghiệp</p>
        <div class="d-flex justify-content-center gap-3 mt-4">
            <a href="/login" class="btn btn-light btn-lg">Đăng nhập</a>
            <a href="/register" class="btn btn-outline-light btn-lg">Đăng ký</a>
        </div>
    </div>
</header>

<!-- WHY CHOOSE HSF -->
<section class="container py-5">
    <h2 class="text-center mb-4">Tại sao nên chọn HSF?</h2>
    <div class="row text-center">
        <div class="col-md-4">
            <h4>Khoá học thực chiến</h4>
            <p>Thực hành qua dự án thật: làm web, API, microservice, bảo mật và nhiều hơn nữa.</p>
        </div>
        <div class="col-md-4">
            <h4>Hỗ trợ 1-1</h4>
            <p>Mentor đồng hành, hỗ trợ bạn qua chat, call, feedback code mỗi ngày.</p>
        </div>
        <div class="col-md-4">
            <h4>Lộ trình rõ ràng</h4>
            <p>Từ cơ bản đến nâng cao: phù hợp cả người mới lẫn đã có kiến thức lập trình.</p>
        </div>
    </div>
</section>

<!-- BENEFITS -->
<section class="bg-light py-5">
    <div class="container">
        <h2 class="text-center mb-4">Bạn sẽ nhận được gì?</h2>
        <div class="row">
            <div class="col-md-6">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">✔️ Truy cập khóa học trọn đời</li>
                    <li class="list-group-item">✔️ Cộng đồng hỗ trợ 24/7</li>
                    <li class="list-group-item">✔️ Chứng chỉ hoàn thành khoá học</li>
                    <li class="list-group-item">✔️ Cơ hội được giới thiệu việc làm</li>
                </ul>
            </div>
            <div class="col-md-6">
                <img src="https://cdn.pixabay.com/photo/2016/11/21/15/46/computer-1846036_960_720.jpg" class="img-fluid rounded" alt="Lập trình viên">
            </div>
        </div>
    </div>
</section>

<!-- STUDENT FEEDBACK -->
<section class="container py-5">
    <h2 class="text-center mb-4">Cảm nhận học viên</h2>
    <div class="row text-center">
        <div class="col-md-4">
            <blockquote class="blockquote">
                <p>"HSF giúp mình có công việc đầu tiên với tư cách lập trình viên Java sau 4 tháng học!"</p>
                <footer class="blockquote-footer">Thành, Fresher Backend</footer>
            </blockquote>
        </div>
        <div class="col-md-4">
            <blockquote class="blockquote">
                <p>"Mentor rất tận tâm, lộ trình rõ ràng và thực tế."</p>
                <footer class="blockquote-footer">Trâm, Sinh viên CNTT</footer>
            </blockquote>
        </div>
        <div class="col-md-4">
            <blockquote class="blockquote">
                <p>"Không chỉ học code, mình còn học cách tư duy và làm dự án nhóm."</p>
                <footer class="blockquote-footer">Phúc, Developer tại TMA</footer>
            </blockquote>
        </div>
    </div>
</section>

<!-- FAQ -->
<section class="bg-light py-5">
    <div class="container">
        <h2 class="text-center mb-4">Câu hỏi thường gặp</h2>
        <div class="accordion" id="faqAccordion">
            <div class="accordion-item">
                <h2 class="accordion-header"><button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#q1">Học online hay offline?</button></h2>
                <div id="q1" class="accordion-collapse collapse show" data-bs-parent="#faqAccordion">
                    <div class="accordion-body">HSF là nền tảng học trực tuyến 100%, bạn có thể học mọi lúc, mọi nơi.</div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header"><button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#q2">Tôi cần kiến thức nền tảng không?</button></h2>
                <div id="q2" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                    <div class="accordion-body">Không cần! HSF có lộ trình từ cơ bản đến nâng cao cho người mới bắt đầu.</div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- CTA -->
<section class="text-center py-5 bg-primary text-white">
    <div class="container">
        <h2 class="mb-3">Sẵn sàng để trở thành lập trình viên?</h2>
        <a href="/register" class="btn btn-light btn-lg me-3">Đăng ký ngay</a>
        <a href="/login" class="btn btn-outline-light btn-lg">Tôi đã có tài khoản</a>
    </div>
</section>

<!-- FOOTER -->
<footer class="bg-dark text-white text-center py-3">
    &copy; 2025 HSF Learn. All rights reserved.
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
