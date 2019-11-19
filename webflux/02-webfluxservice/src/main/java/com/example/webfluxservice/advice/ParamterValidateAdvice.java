package com.example.webfluxservice.advice;

import com.example.webfluxservice.exception.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

//验证失败则返回失败信息及400
//表示当前类为通知（切面）其连接点为处理器方法
@ControllerAdvice
public class ParamterValidateAdvice {
    @ExceptionHandler
    public ResponseEntity<String> exHandle(StudentException ex) {
        String message = ex.getErrorFiled() + ex.getMessage() + ex.getErrorValue();
        return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> validateHandle(WebExchangeBindException ex) {
        return new ResponseEntity<String>(exToStr(ex), HttpStatus.BAD_REQUEST);
    }

    private String exToStr(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream().map(fieldError -> fieldError.getField() + fieldError.getDefaultMessage()).reduce("", (s1, s2) -> s1 + "\n" + s2);
    }
}
