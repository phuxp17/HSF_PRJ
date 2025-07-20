package org.example.hsf_prj.dto.response;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hsf_prj.entity.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponse {
    private String accessToken;
    @Enumerated
    private UserRole role;
}

