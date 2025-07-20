package org.example.hsf_prj.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.hsf_prj.config.JwtUtil;
import org.example.hsf_prj.dto.request.*;
import org.example.hsf_prj.dto.response.AuthLoginResponse;
import org.example.hsf_prj.dto.response.UserResponse;
import org.example.hsf_prj.service.AuthenticationService;
import org.example.hsf_prj.service.EmailService;
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

    // === XÁC THỰC OTP ===
    @PostMapping("/verify-otp")
    public String completeLoginWithOtp(@ModelAttribute VerifyOTPRequest request,
                                       HttpServletRequest servletRequest,
                                       Model model) {
        try {
            request.setIp(servletRequest.getRemoteAddr());
            request.setUserAgent(servletRequest.getHeader("User-Agent"));
            AuthLoginResponse response = emailService.verifyOtp(request);
            model.addAttribute("user", response);
            return "home"; // Home.jsp khi đăng nhập thành công
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
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

    // === TRANG YÊU CẦU RESET MẬT KHẨU ===
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String requestPasswordReset(@RequestParam String email, Model model) {
        try {
            authenticationService.requestPasswordReset(email);
            model.addAttribute("success", "If account exists, reset link sent.");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "forgot-password";
    }

    // === TRANG RESET MẬT KHẨU ===
    @GetMapping("/reset-password")
    public String showResetPasswordPage() {
        return "reset-password";
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

    // === ĐĂNG XUẤT ===
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String token = jwtUtil.extractTokenFromRequest(request);
        if (token != null) jwtUtil.blacklistToken(token);
        return "redirect:/auth/login";
    }


}
