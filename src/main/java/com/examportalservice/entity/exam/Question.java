package com.examportalservice.entity.exam;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="question")
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

    //fetch type eager means question fetch garda quiz pani aayos
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

}
