package com.example.ourdevboard.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {
    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handle(IllegalArgumentException ex) {
        // 바디
        ApiException apiException = new ApiException(
                ex.getMessage(),
                // HTTP 400 -> Client Error
                HttpStatus.BAD_REQUEST
                // HttpStatus.BAD_REQUEST400에러 :클라이언트의 잘못된인자로 인해 발생한 에러임을 알려줌
        );

        return new ResponseEntity<>(
                apiException, //바디
                HttpStatus.BAD_REQUEST // 헤더
        );
    }
}
