package com.harshit.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String quizTitle;
    private Integer score;
    private Boolean attempted;

    @ElementCollection
    private List<Integer> questionIds;
}
