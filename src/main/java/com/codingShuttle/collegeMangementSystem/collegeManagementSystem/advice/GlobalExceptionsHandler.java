package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.advice;


import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handlerAuthenticateException(AuthenticationException ex){
        ApiError apiError = new ApiError(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError , HttpStatus.UNAUTHORIZED);
    }


}
