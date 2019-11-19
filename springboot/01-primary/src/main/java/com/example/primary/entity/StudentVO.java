package com.example.primary.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentVO implements Serializable {
    Integer id;
    int age;
    String name;
    String sex;

}
