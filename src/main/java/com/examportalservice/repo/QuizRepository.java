package com.examportalservice.repo;

import com.examportalservice.entity.exam.Category;
import com.examportalservice.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
 public  List<Quiz> findBycategory(Category category);
}
