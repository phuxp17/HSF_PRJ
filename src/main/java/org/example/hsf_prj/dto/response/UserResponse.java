package org.example.hsf_prj.dto.response;

import lombok.*;
import org.example.hsf_prj.entity.enums.UserRole;
import org.example.hsf_prj.entity.enums.UserStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String avatar;
    private UserRole role;
    private UserStatus status;
}
