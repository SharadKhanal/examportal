package com.examportalservice.service.impl;

import com.examportalservice.entity.exam.Quiz;
import com.examportalservice.repo.QuizRepository;
import com.examportalservice.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long id) {
        Quiz quiz = new Quiz();
        quiz.setQid(id);
        this.quizRepository.delete(quiz);
    }

    @Override
    public Set<Quiz> getAllQuiz() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuizById(Long id) {
        return this.quizRepository.findById(id).get();
    }
}
