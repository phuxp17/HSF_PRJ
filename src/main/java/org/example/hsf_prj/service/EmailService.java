package org.example.hsf_prj.service;

import jakarta.mail.MessagingException;
import org.example.hsf_prj.dto.request.VerifyOTPRequest;
import org.example.hsf_prj.dto.response.AuthLoginResponse;

public interface EmailService {
    void sendOTP(String recipient) throws MessagingException;
    AuthLoginResponse verifyOtp(VerifyOTPRequest request);

    void sendPasswordResetEmail(String recipientEmail, String resetToken) throws MessagingException;

    void sendHtmlEmail(String recipientEmail, String subject, String htmlBody) throws MessagingException;
}
