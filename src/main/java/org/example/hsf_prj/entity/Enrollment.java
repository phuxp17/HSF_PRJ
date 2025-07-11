package org.example.hsf_prj.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hsf_prj.entity.enums.EnrollmentStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime enrolledAt;

    private LocalDateTime completedAt;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;

    @Builder.Default
    private Integer progressPercentage = 0;

    @Builder.Default
    private Integer totalTimeSpent = 0;

    // Relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<LessonProgress> lessonProgresses;

    @OneToOne(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private Certificate certificate;
}
