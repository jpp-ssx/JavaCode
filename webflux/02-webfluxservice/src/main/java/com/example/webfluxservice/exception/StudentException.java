package com.example.webfluxservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentException extends RuntimeException {
    private String errorFiled;
    private String errorValue;

    public StudentException(String errorFiled, String errorValue, String message) {
        super(message);
        this.errorFiled = errorFiled;
        this.errorValue = errorValue;
    }
}
