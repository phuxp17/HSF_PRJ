package org.example.hsf_prj.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.hsf_prj.config.JwtUtil;
import org.example.hsf_prj.dto.request.*;
import org.example.hsf_prj.dto.response.AuthLoginResponse;
import org.example.hsf_prj.dto.response.UserResponse;
import org.example.hsf_prj.entity.User;
import org.example.hsf_prj.repository.UserRepository;
import org.example.hsf_prj.service.AuthenticationService;
import org.example.hsf_prj.service.EmailService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    // === TRANG LOGIN ===
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // === XỬ LÝ LOGIN (GỬI OTP) ===
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, Model model) {
        try {
            String message = authenticationService.login(request);
            model.addAttribute("success", message);
            model.addAttribute("email", request.getEmail());
            return "verify-otp";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    // === TRANG NHẬP OTP ===
    @GetMapping("/verify-otp")
    public String showOtpPage() {
        return "verify-otp";
    }

    @PostMapping("/verify-otp")
    public String completeLoginWithOtp(@ModelAttribute VerifyOTPRequest request,
                                       HttpServletRequest servletRequest,
                                       Model model) {
        try {
            request.setIp(servletRequest.getRemoteAddr());
            request.setUserAgent(servletRequest.getHeader("User-Agent"));

            AuthLoginResponse response = emailService.verifyOtp(request);

            model.addAttribute("user", response);
            model.addAttribute("email", request.getEmail());

            // Chuyển hướng theo role
            switch (response.getRole()) {
                case STUDENT:
                    return "student/dashboard";  // WEB-INF/views/student/dashboard.jsp
                case TEACHER:
                    return "teacher/dashboard";  // WEB-INF/views/teacher/dashboard.jsp
                case ADMIN:
                    return "admin/dashboard";    // nếu có trang admin
                default:
                    model.addAttribute("error", "Không xác định được vai trò người dùng.");
                    return "verify-otp";
            }

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("email", request.getEmail());
            return "verify-otp";
        }
    }




    // === GỬI LẠI OTP ===
    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam String email, Model model) {
        try {
            emailService.sendOTP(email);
            model.addAttribute("success", "OTP sent to email");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "login";
    }

    // === TRANG ĐĂNG KÝ ===
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // === XỬ LÝ ĐĂNG KÝ ===
    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request, Model model) {
        try {
            UserResponse response = authenticationService.register(request);
            model.addAttribute("success", "Register success. Please login.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }


    // === TRANG RESET MẬT KHẨU ===
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password"; // tương ứng với WEB-INF/views/forgot-password.jsp
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        try {
            // 1. Tìm user theo email
            User user = userRepository.findByEmail(email.trim())
                    .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng với email: " + email));

            // 2. Tạo reset token
            String resetToken = jwtUtil.generatePasswordResetToken(user);  // bạn cần hàm này

            // 3. Gửi email chứa token
            emailService.sendPasswordResetEmail(user.getEmail(), resetToken);

            model.addAttribute("success", "Email đặt lại mật khẩu đã được gửi.");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "forgot-password";
    }


    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute ResetPasswordRequest request, Model model) {
        try {
            authenticationService.resetPassword(request.getToken(), request.getNewPassword());
            model.addAttribute("success", "Password reset successful.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "reset-password";
        }
    }
    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token); // truyền token để form JSP sử dụng
        return "reset-password";   // hoặc "reset-password" nếu JSP nằm trực tiếp trong WEB-INF/views
    }

    // === ĐĂNG XUẤT ===
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String token = jwtUtil.extractTokenFromRequest(request);
        if (token != null) jwtUtil.blacklistToken(token);
        return "redirect:/auth/login";
    }


}
