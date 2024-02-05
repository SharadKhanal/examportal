package com.examportalservice.controller;

import com.examportalservice.entity.exam.Question;
import com.examportalservice.entity.exam.Quiz;
import com.examportalservice.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody Question question){
        Question question1 = this.questionService.saveQuestion(question);
        return  ResponseEntity.ok(question1);
    }

    @GetMapping("/getQuestionById/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable("id") Long id){
        Question question = this.questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/all")
    public Set<Question> getAllQuestion(){
        return this.questionService.getAllQuestion();
    }

    @PutMapping("/update")
    public  ResponseEntity<?> updateQuestiion(@RequestBody Question question){
        Question question1 = this.questionService.updateQuestion(question);
        return ResponseEntity.ok(question1);
    }

    @DeleteMapping("/delete/{id}")
    public  void deleteQuestion(@PathVariable("id") Long id){
        this.questionService.deleteQuestion(id);
    }

    // get all question by any quiz
    @GetMapping("/quiz/{quizId}")
    public  ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable("quizId") Long quizId){
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        Set<Question>questionsOdQuiz = this.questionService.getQuestionOfQuiz(quiz);
        return  ResponseEntity.ok(questionsOdQuiz);
    }
}
