package com.analistas.bloggingplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(EntityNotFound.class)
    public ProblemDetail getProblemDetail(EntityNotFound entityNotFound) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, entityNotFound.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail getMethodArgumentNotValidException(MethodArgumentNotValidException notValidException) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");
        Map<String, String> listProblemsMap = new HashMap<>();

        notValidException.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                listProblemsMap.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        problemDetail.setProperty("errors", listProblemsMap);
        return problemDetail;
    }
}
