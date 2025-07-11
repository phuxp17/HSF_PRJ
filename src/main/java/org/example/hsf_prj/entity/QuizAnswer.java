package org.example.hsf_prj.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quiz_answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String answerText;

    @Builder.Default
    private Boolean isCorrect = false;

    @Column(nullable = false)
    private Integer orderIndex;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false)
    private QuizQuestion question;
}

