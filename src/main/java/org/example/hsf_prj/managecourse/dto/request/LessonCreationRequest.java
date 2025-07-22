package org.example.hsf_prj.managecourse.dto.request;

import lombok.Data;
import org.example.hsf_prj.entity.enums.LessonType;

import java.util.List;

@Data
public class LessonCreationRequest {
    private String title;
    private String description;
    private Integer orderIndex;
    private Integer duration;
    private LessonType type;
    private List<LessonContentRequest> contents;
    private QuizCreationRequest quiz; // optional
}


