package com.example.primary.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScoreVO implements Serializable {
    Integer id;
    Integer student_id;
    String subject_name;
    int score;

}
