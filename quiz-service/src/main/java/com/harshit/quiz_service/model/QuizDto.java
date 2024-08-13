package com.harshit.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    String title;
    String category;
    Integer numQuestions;
}
