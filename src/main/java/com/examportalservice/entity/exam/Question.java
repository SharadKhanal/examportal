package com.examportalservice.entity.exam;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="question")
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qusId;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content;

    private String images;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String answer;

    // Many questions can belong to one quiz
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_qid")  // This will be the foreign key column
    private Quiz quiz;


}

