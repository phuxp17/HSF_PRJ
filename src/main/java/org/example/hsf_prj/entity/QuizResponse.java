package org.example.hsf_prj.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String responseText;

    @Builder.Default
    private Boolean isCorrect = false;

    @Builder.Default
    private Integer pointsEarned = 0;

    @CreationTimestamp
    private LocalDateTime answeredAt;

    // Relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attempt_id", nullable = false)
    private QuizAttempt attempt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false)
    private QuizQuestion question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "selected_answer_id")
    private QuizAnswer selectedAnswer;
}

