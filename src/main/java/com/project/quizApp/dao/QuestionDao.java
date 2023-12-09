package com.project.quizApp.dao;

import com.project.quizApp.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {

    public List<Questions> findQuestionsByCategory(String category);

    @Query(value="SELECT * FROM QUESTIONS Q WHERE Q.CATEGORY=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(String category, int numQ);
}
