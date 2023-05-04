package com.myblog.myblog.Exception;

import com.myblog.myblog.Payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {//its like global catch block
    @ExceptionHandler(ResourceNotFoundException.class)
    //specific exception
    public ResponseEntity<ErrorDetails>handlerResourceNotFound(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));

return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    //global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails>handleGlobalException(Exception exception,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));



        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }














}
