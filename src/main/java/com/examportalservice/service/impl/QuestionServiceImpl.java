package com.examportalservice.service.impl;

import com.examportalservice.entity.exam.Question;
import com.examportalservice.entity.exam.Quiz;
import com.examportalservice.repo.QuestionRepository;
import com.examportalservice.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question saveQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
            log.info("update  qstion impl serveree!");
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getAllQuestion() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestionById(Long id) {
        return this.questionRepository.findById(id).get();
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = new Question();
        question.setQusId(id);
        this.questionRepository.delete(question);
    }

    @Override
    public Set<Question> getQuestionOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }
}
