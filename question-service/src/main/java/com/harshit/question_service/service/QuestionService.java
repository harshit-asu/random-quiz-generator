package com.harshit.question_service.service;

import com.harshit.question_service.dao.QuestionDao;
import com.harshit.question_service.model.Question;
import com.harshit.question_service.model.QuestionWrapper;
import com.harshit.question_service.model.Response;
import com.harshit.question_service.util.CustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> createQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Question created successfully!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByCategory(String category) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        try {
            List<Question> questions = questionDao.getQuestionsByCategory(category);
            questionWrappers = CustomUtils.convertQuestionsToQuestionWrappers(questions);
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(questionWrappers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, Integer numQuestions) {
        try {
            List<Integer> questionIds = questionDao.generateQuestionsForQuiz(category, numQuestions);
            return new ResponseEntity<>(questionIds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Integer>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        try{
            List<Question> questions = questionDao.findAllById(questionIds);
            questionWrappers = CustomUtils.convertQuestionsToQuestionWrappers(questions);
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(questionWrappers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        try{
            int score = 0;
            for(Response r: responses){
                Question question = questionDao.findById(r.getId()).get();
                if(r.getContent().equals(question.getRightAnswer())){
                    score++;
                }
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
