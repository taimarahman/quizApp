package com.project.quizApp.controller;

import com.project.quizApp.Questions;
import com.project.quizApp.dao.QuestionDao;
import com.project.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Questions> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public  List<Questions> getCategorizedQuestions(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Questions question){
        return questionService.addQuestion(question);
    }
}
