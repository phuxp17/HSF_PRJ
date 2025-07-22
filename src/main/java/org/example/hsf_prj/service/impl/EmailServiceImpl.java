package org.example.hsf_prj.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.hsf_prj.config.JwtUtil;
import org.example.hsf_prj.dto.request.VerifyOTPRequest;
import org.example.hsf_prj.dto.response.AuthLoginResponse;
import org.example.hsf_prj.entity.Otp;
import org.example.hsf_prj.entity.User;
import org.example.hsf_prj.entity.enums.UserStatus;
import org.example.hsf_prj.repository.OTPVerificationRepository;
import org.example.hsf_prj.repository.UserRepository;
import org.example.hsf_prj.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final OTPVerificationRepository otpVerificationRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @Transactional
    public AuthLoginResponse verifyOtp(VerifyOTPRequest request) {
        String userEmail = request.getEmail().trim();
        String otpCode = request.getOtp();

        Otp otpRecord = otpVerificationRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("OTP not found for this email."));

        if (otpRecord.getStatus() != Otp.OtpStatus.PENDING) {
            throw new RuntimeException("OTP has already been used or expired.");
        }

        if (otpRecord.getExpiredTime().isBefore(LocalDateTime.now())) {
            otpRecord.setStatus(Otp.OtpStatus.EXPIRED);
            otpVerificationRepository.save(otpRecord);
            throw new RuntimeException("OTP has expired.");
        }

        if (!otpRecord.getOtp().equals(otpCode)) {
            otpRecord.setStatus(Otp.OtpStatus.USED);
            otpVerificationRepository.save(otpRecord);
            throw new RuntimeException("Invalid OTP.");
        }

        otpRecord.setStatus(Otp.OtpStatus.VERIFIED);
        otpVerificationRepository.save(otpRecord);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        if (user.getStatus() != UserStatus.ACTIVE) {
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
        }

        String token = jwtUtil.generateToken(user);

        AuthLoginResponse response = new AuthLoginResponse();
        response.setAccessToken(token);
        response.setRole(user.getRole());

        return response;
    }


    @Async
    @Override
    public void sendPasswordResetEmail(String recipientEmail, String resetToken) throws MessagingException {
        String resetLink = "http://localhost:8080/reset-password?token=" + resetToken;

        String subject = "[HSF System] Password Reset Request";
        String body = "Click the link to reset your password: <a href=\"" + resetLink + "\">Reset Password</a><br><br>" +
                "This link will expire soon. Please ignore this email if you didn't request a reset.";

        sendHtmlEmail(recipientEmail, subject, body);
    }

    @Async
    @Override
    public void sendHtmlEmail(String recipientEmail, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.setFrom(fromEmail);

        javaMailSender.send(message);
    }

    private String generateOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }
    @Override
    @Transactional
    public void sendOTP(String recipient) {
        String cleanEmail = recipient.trim();

        User user = userRepository.findByEmail(cleanEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for OTP generation"));

        otpVerificationRepository.findByEmail(cleanEmail)
                .ifPresent(otpVerificationRepository::delete);

        String otpCode = generateOTP();
        Otp otp = new Otp();
        otp.setEmail(cleanEmail);
        otp.setOtp(otpCode);
        otp.setCreatedDate(LocalDateTime.now());
        otp.setExpiredTime(LocalDateTime.now().plusMinutes(5));
        otp.setStatus(Otp.OtpStatus.PENDING);
        otp.setUsers(user); // chú ý: nếu entity là `user`, không phải `users` nhé
        otpVerificationRepository.save(otp);

        try {
            sendHtmlEmail(cleanEmail, "[HSF System] OTP Verification", "Your OTP code is: <b>" + otpCode + "</b>");
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email: " + e.getMessage());
        }
    }


}
