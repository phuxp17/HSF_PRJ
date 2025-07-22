package org.example.hsf_prj.managecourse.dto.request;

import lombok.Data;

@Data
public class QuizAnswerRequest {
    private String answerText;
    private Boolean isCorrect;
    private Integer orderIndex;
}
