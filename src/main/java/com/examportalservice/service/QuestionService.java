package com.examportalservice.service;

import com.examportalservice.entity.exam.Question;
import com.examportalservice.entity.exam.Quiz;

import java.util.Set;

public interface QuestionService {

    public Question saveQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getAllQuestion();
    public Question getQuestionById(Long id);
    public void deleteQuestion(Long id);
    public Set<Question> getQuestionOfQuiz(Quiz quiz);

}
