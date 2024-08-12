package com.harshit.question_service.dao;

import com.harshit.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> getQuestionsByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Integer> generateQuestionsForQuiz(String category, Integer numQuestions);
}
