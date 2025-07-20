package org.example.hsf_prj.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.example.hsf_prj.dto.request.LoginRequest;
import org.example.hsf_prj.dto.request.RegisterRequest;
import org.example.hsf_prj.dto.response.UserResponse;


import java.util.List;

public interface AuthenticationService {
    String login(LoginRequest request) throws MessagingException;
    UserResponse register(RegisterRequest request);
    void resetPassword(String token, String newPassword);
    void requestPasswordReset(String email) throws MessagingException;


}

