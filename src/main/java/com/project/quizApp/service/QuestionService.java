package com.project.quizApp.service;

import com.project.quizApp.Questions;
import com.project.quizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public List<Questions> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Questions> getQuestionByCategory(String category) {
        return questionDao.findQuestionsByCategory(category);
    }

    public String addQuestion(Questions question) {
        questionDao.save(question);
        return "success";
    }
}
