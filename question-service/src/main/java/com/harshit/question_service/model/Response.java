package com.harshit.question_service.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private Integer id;
    private String content;
}
