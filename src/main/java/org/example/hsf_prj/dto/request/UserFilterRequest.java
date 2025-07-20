package org.example.hsf_prj.dto.request;

import lombok.Data;

@Data
public class UserFilterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;   // STUDENT, TEACHER, ADMIN
    private String status; // ACTIVE, INACTIVE, etc.

    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = "createdAt";
    private String sortDirection = "desc";
}
