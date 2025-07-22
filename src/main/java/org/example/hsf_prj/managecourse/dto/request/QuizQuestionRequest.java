package org.example.hsf_prj.managecourse.dto.request;

import lombok.Data;
import org.example.hsf_prj.entity.enums.QuestionType;

import java.util.List;

@Data
public class QuizQuestionRequest {
    private String question;
    private QuestionType type;
    private Integer points;
    private Integer orderIndex;
    private List<QuizAnswerRequest> answers;
}
