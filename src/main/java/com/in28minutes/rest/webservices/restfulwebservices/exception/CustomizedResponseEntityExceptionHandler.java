package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
@Controller
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		
		ExceptionRespose exceptionRespose = 
				new ExceptionRespose(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionRespose,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		
		ExceptionRespose exceptionRespose = 
				new ExceptionRespose(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionRespose,HttpStatus.NOT_FOUND);
	}
	
	//// for validate bad argument from request 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionRespose exceptionRespose = 
				new ExceptionRespose(new Date(), "Validation Fail", ex.getBindingResult().toString());
		 return new ResponseEntity(exceptionRespose,HttpStatus.BAD_REQUEST);
	}
}
