package com.examportalservice.controller;

import com.examportalservice.entity.exam.Category;
import com.examportalservice.entity.exam.Quiz;
import com.examportalservice.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
        Quiz quiz1 = this.quizService.addQuiz(quiz);
        return  ResponseEntity.ok(quiz1);
    }

    @GetMapping("/getQuizById/{id}")
    public  ResponseEntity<?> getQuizById(@PathVariable("id") Long id){
        Quiz quiz = this.quizService.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/all")
    public Set<Quiz> getAllQuizzes(){
        return this.quizService.getAllQuiz();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
        Quiz quiz1 = this.quizService.updateQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuiz(@PathVariable("id") Long id){
        this.quizService.deleteQuiz(id);
    }

    @GetMapping("/getQuizByCategory/{categoryId}")
    public List<Quiz> getQuizByCategory(@PathVariable("categoryId") Long categoryId){
        Category category = new Category();
        category.setCid(categoryId);
        return this.quizService.getQuizByCategory(category);
    }
}
