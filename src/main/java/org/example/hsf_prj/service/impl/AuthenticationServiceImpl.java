package org.example.hsf_prj.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.hsf_prj.config.JwtUtil;
import org.example.hsf_prj.config.SecurityConfig;
import org.example.hsf_prj.dto.request.LoginRequest;
import org.example.hsf_prj.dto.request.RegisterRequest;
import org.example.hsf_prj.dto.response.UserResponse;
import org.example.hsf_prj.entity.User;
import org.example.hsf_prj.entity.enums.UserRole;
import org.example.hsf_prj.entity.enums.UserStatus;
import org.example.hsf_prj.repository.UserRepository;
import org.example.hsf_prj.service.AuthenticationService;
import org.example.hsf_prj.service.EmailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final SecurityConfig securityConfig;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;


    @Override
    public String login(LoginRequest request) throws MessagingException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found after authentication."));

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new BadCredentialsException("Your account is not active.");
        }

        emailService.sendOTP(user.getEmail());
        return "OTP has been sent to your email. Please enter it to complete login.";
    }

    @Override
    @Transactional
    public UserResponse register(RegisterRequest register) {
        if (userRepository.existsByEmail(register.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        if (register.getPassword().length() > 72) {
            throw new IllegalArgumentException("Password cannot be more than 72 characters.");
        }

        User user = User.builder()
                .email(register.getEmail())
                .password(securityConfig.passwordEncoder().encode(register.getPassword()))
                .firstName(register.getFirstName())
                .lastName(register.getLastName())
                .phoneNumber(register.getPhoneNumber())
                .status(UserStatus.ACTIVE)
                .role(UserRole.STUDENT)
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }

    @Override
    @Transactional
    public void requestPasswordReset(String email) throws MessagingException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        String resetToken = jwtUtil.generatePasswordResetToken(user);
        emailService.sendPasswordResetEmail(user.getEmail(), resetToken);
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {
        String email = jwtUtil.extractUsername(token);
        if (email == null) {
            throw new BadCredentialsException("Invalid or malformed reset password token.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for password reset."));

        if (!jwtUtil.validateResetPasswordToken(token, user)) {
            throw new BadCredentialsException("Invalid or expired password reset token.");
        }

        if (newPassword == null || newPassword.length() < 6 || newPassword.length() > 72) {
            throw new IllegalArgumentException("New password must be between 6 and 72 characters.");
        }

        user.setPassword(securityConfig.passwordEncoder().encode(newPassword));
        userRepository.save(user);
    }
}
