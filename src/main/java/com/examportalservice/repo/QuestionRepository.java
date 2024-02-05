package com.examportalservice.repo;

import com.examportalservice.entity.exam.Question;
import com.examportalservice.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
