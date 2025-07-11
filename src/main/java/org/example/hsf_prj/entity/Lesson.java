package org.example.hsf_prj.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hsf_prj.entity.enums.LessonType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private Integer orderIndex;

    @Builder.Default
    private Integer duration = 0; // Duration in minutes

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private LessonType type = LessonType.VIDEO;

    @Builder.Default
    private Boolean isPreview = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<LessonContent> contents;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<LessonProgress> progresses;
}
