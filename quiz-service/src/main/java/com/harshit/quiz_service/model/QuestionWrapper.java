package com.harshit.quiz_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionWrapper {

    private Integer id;
    private String questionTitle;
    private List<String> options;

}
