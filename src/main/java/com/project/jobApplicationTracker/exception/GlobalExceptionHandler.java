package com.project.jobApplicationTracker.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;	
import jakarta.validation.ConstraintViolationException;
@RestControllerAdvice

public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleMethodArgumentException(MethodArgumentNotValidException e){
        Map<String,String> result = new HashMap<String, String>();
        e.getBindingResult().getFieldErrors().forEach( oe -> {
            result.put(oe.getField(),oe.getDefaultMessage());
        });
        return result;
    }
	 @ExceptionHandler(RuntimeException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public Map<String, String> handleRuntimeException(RuntimeException e) {
	        Map<String, String> result = new HashMap<>();
	        result.put("errore", e.getMessage());
	        return result;
	    }

	    @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public Map<String, String> handleGenericException(Exception e) {
	        Map<String, String> result = new HashMap<>();
	        result.put("errore", "Errore interno del server");
	        return result;
	    }
	
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleConstraintViolationException(ConstraintViolationException e){
        Map<String,String> result = new HashMap<String, String>();
        e.getConstraintViolations().forEach( ce -> {
            result.put(ce.getPropertyPath().toString(),ce.getMessage());
        });
        return result;
    }
}
