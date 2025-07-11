package org.example.hsf_prj.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hsf_prj.entity.enums.AttemptStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quiz_attempts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    @Builder.Default
    private Double score = 0.0;

    @Builder.Default
    private Integer totalPoints = 0;

    @Builder.Default
    private Integer earnedPoints = 0;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AttemptStatus status = AttemptStatus.IN_PROGRESS;

    // Relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL)
    private List<QuizResponse> responses;
}

