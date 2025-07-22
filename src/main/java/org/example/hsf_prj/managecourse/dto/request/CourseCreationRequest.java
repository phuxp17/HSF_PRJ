package org.example.hsf_prj.managecourse.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CourseCreationRequest {
    private String title;
    private String description;
    private String fullDescription;
    private String thumbnail;
    private BigDecimal price;
    private Long teacherId;
    private Long categoryId;
    private List<LessonCreationRequest> lessons;
}
