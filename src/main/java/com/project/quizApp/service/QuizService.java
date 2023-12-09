package com.project.quizApp.service;

import com.project.quizApp.dao.QuestionDao;
import com.project.quizApp.dao.QuizDao;
import com.project.quizApp.model.Questions;
import com.project.quizApp.model.Quiz;
import com.project.quizApp.model.RequestQuiz;
import com.project.quizApp.model.ResponseQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuizDao quizDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Questions> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<ResponseQuestions>> getQuizQuestion(Integer id) {

        Optional<Quiz> quiz = quizDao.findById(id);
        List<Questions> quizQuestionsServer = quiz.get().getQuestions();
        List<ResponseQuestions> questionForClient = new ArrayList<>();
        for(Questions q:quizQuestionsServer){
            ResponseQuestions ques = new ResponseQuestions(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForClient.add(ques);
        }
        return new ResponseEntity<>(questionForClient, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateQuizResult(Integer id, List<RequestQuiz> requests) {
        Quiz quiz = quizDao.findById(id).get();
        List<Questions> questionList = quiz.getQuestions();
        int result = 0;
        int i = 0;
        for (RequestQuiz q: requests) {
            if(q.getResponse().equals(questionList.get(i).getRightAnswer()))
                result++;
            i++;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
