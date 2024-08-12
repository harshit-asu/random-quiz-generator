package com.harshit.question_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String rightAnswer;
    private String difficultyLevel;
    private String category;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Option> options;
}
