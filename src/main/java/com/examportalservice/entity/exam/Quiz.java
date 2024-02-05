package com.examportalservice.entity.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qid;

    private String title;

    private String description;

    private String maxMarks;

    private String noOfQuestions;

    private boolean active = false;

    //here fetch type eager means quiz fetch garda category pani aayos
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //here jsonignore gareko chai cyclic dependency na aayos vanera becoz quiz fetch garda questions and again qstion fetch garda quiz
    @JsonIgnore
    private Set<Question> questions = new LinkedHashSet<>();
}
