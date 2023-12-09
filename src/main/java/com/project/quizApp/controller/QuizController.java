package com.project.quizApp.controller;

import com.project.quizApp.model.ResponseQuestions;
import com.project.quizApp.model.RequestQuiz;
import com.project.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<ResponseQuestions>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<RequestQuiz> requests){
        return quizService.calculateQuizResult(id, requests);
    }
}
