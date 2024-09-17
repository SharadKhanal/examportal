package com.examportalservice.service;

import com.examportalservice.entity.exam.Category;
import com.examportalservice.entity.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);
    public void deleteQuiz(Long id);
    public Set<Quiz> getAllQuiz();

    public Quiz getQuizById(Long id);

   public List<Quiz> getQuizByCategory(Category category);
}
