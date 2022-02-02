package com.example.projectscheduler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler {

        @ExceptionHandler
        public ResponseEntity<Object> exceptionIdHandler(ProjectIdException e, WebRequest request){
            ProjectIdExceptionResponse projectIdExceptionResponse = new ProjectIdExceptionResponse(e.getMessage());
            return new ResponseEntity(projectIdExceptionResponse, HttpStatus.BAD_REQUEST);
        }
}
