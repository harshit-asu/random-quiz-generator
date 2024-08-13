package com.harshit.quiz_service.controller;

import com.harshit.quiz_service.model.QuestionWrapper;
import com.harshit.quiz_service.model.Quiz;
import com.harshit.quiz_service.model.QuizDto;
import com.harshit.quiz_service.model.Response;
import com.harshit.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("getAllQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes(){
        return quizService.getAllQuizzes();
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getTitle(), quizDto.getCategory(), quizDto.getNumQuestions());
    }

    @GetMapping("{id}/questions")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("{id}/submit")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateScore(id, responses);
    }
}
