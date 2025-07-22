package org.example.hsf_prj.managecourse.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class QuizCreationRequest {
    private String title;
    private String description;
    private Integer timeLimit;
    private Integer maxAttempts;
    private Double passingScore;

    private List<QuizQuestionRequest> questions;
}

