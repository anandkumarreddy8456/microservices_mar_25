package com.anand.microservice.userService.exception;

import com.anand.microservice.userService.payload.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception e, WebRequest req){
        ExceptionResponse exceptionResponse=new ExceptionResponse(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return ResponseEntity.ok(exceptionResponse);
    }
}
