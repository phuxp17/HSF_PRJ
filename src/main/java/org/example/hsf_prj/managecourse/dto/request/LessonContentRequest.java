package org.example.hsf_prj.managecourse.dto.request;

import lombok.Data;
import org.example.hsf_prj.entity.enums.ContentType;

@Data
public class LessonContentRequest {
    private String title;
    private ContentType type;
    private String content;
    private String fileUrl;
    private Integer orderIndex;
}


