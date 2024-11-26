package com.crm.exception;

import com.crm.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

//this become global class when occurs exception any where automatically come on this class
@ControllerAdvice
public class HandleException {
//    @ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<String> handleEmployeeNotFoundException(
//            ResourceNotFound e
//    ){
//        return new ResponseEntity<>("Record not found", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(
            ResourceNotFound e,
            WebRequest request
    ){
        ErrorDetails getDetails = new ErrorDetails(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(getDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalException(
            Exception e,
            WebRequest request
    ){
        ErrorDetails getDetails = new ErrorDetails(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(getDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
