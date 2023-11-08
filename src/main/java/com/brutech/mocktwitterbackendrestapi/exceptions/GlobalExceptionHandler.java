package com.brutech.mocktwitterbackendrestapi.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException e) {
        TwitterErrorResponse errorResponse = new TwitterErrorResponse(e.getHttpStatus().value(), e.getMessage(), java.time.LocalDateTime.now());
        log.error("Exception: " + e.getMessage());
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(Exception e) {
        TwitterErrorResponse errorResponse = new TwitterErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), java.time.LocalDateTime.now());
        log.error("Exception: " + e.getMessage());
        return new ResponseEntity<>(errorResponse, org.springframework.http.HttpStatus.BAD_REQUEST);
    }
}
