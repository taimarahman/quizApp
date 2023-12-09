package com.project.quizApp.dao;

import com.project.quizApp.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {

    public List<Questions> findQuestionsByCategory(String category);
}
