package com.harshit.quiz_service.service;

import com.harshit.quiz_service.dao.QuizDao;
import com.harshit.quiz_service.feign.QuizInterface;
import com.harshit.quiz_service.model.QuestionWrapper;
import com.harshit.quiz_service.model.Quiz;
import com.harshit.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        try{
            return new ResponseEntity<>(quizDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Quiz>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> createQuiz(String title, String category, Integer numQuestions) {
        try{
            List<Integer> questionIds = quizInterface.generateQuestionsForQuiz(category, numQuestions).getBody();
            System.out.println(questionIds);
            Quiz quiz = new Quiz();
            quiz.setQuizTitle(title);
            quiz.setAttempted(false);
            quiz.setScore(0);
            quiz.setQuestionIds(questionIds);
            quizDao.save(quiz);
            return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        List<QuestionWrapper> questions = new ArrayList<>();
        try{
            Optional<Quiz> q = quizDao.findById(id);
            List<Integer> questionIds = q.get().getQuestionIds();
            questions = quizInterface.getQuestionsFromId(questionIds).getBody();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(questions, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        try{
            Quiz q = quizDao.findById(id).get();
            Integer score = quizInterface.getScore(responses).getBody();
            q.setScore(score);
            q.setAttempted(true);
            quizDao.save(q);
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
