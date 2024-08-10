package com.examportalservice.controller;

import com.examportalservice.entity.exam.Question;
import com.examportalservice.entity.exam.Quiz;
import com.examportalservice.service.QuestionService;
import com.examportalservice.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final QuizService quizService;

    public QuestionController(QuestionService questionService, QuizService quizService) {
        this.questionService = questionService;
        this.quizService = quizService;
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
        log.info("question updatee calleddddd!!!!!");
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
//        Quiz quiz = new Quiz();
//        quiz.setQid(quizId);
//        Set<Question>questionsOdQuiz = this.questionService.getQuestionOfQuiz(quiz);
//        return  ResponseEntity.ok(questionsOdQuiz);
        Quiz quiz = this.quizService.getQuizById(quizId);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if(list.size()> Integer.parseInt(quiz.getNoOfQuestions())){
            list = list.subList(0,Integer.parseInt(quiz.getNoOfQuestions() +1));
        }
        Collections.shuffle(list);
        return  ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{quizId}")
    public  ResponseEntity<?> getAllQuestionOfQuizForAdmin(@PathVariable("quizId") Long quizId){
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        Set<Question>questionsOldQuiz = this.questionService.getQuestionOfQuiz(quiz);
        return  ResponseEntity.ok(questionsOldQuiz);
    }
}
