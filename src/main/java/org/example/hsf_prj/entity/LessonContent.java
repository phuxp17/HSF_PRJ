package org.example.hsf_prj.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hsf_prj.entity.enums.ContentType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "lesson_contents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType type;

    @Column(nullable = false)
    private String title;

    @Column(length = 5000)
    private String content;

    private String fileName;
    private String fileUrl;
    private Long fileSize;

    @Column(nullable = false)
    private Integer orderIndex;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
}

