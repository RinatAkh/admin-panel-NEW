package com.example.demo.exceptionhandler;

import com.example.demo.entity.ResponseWithMessage;
import com.example.demo.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointerException(NullPointerException exception) {

    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWithMessage> handleInvalidInputException(MethodArgumentNotValidException exception) {
        ResponseWithMessage message = new ResponseWithMessage();
        message.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(message);
    }


}
