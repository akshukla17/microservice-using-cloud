package com.learn.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learn.user.UserNotFoundException;

@ControllerAdvice // handle exception thrown by all controllers
//@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class) // applicable for all unhandled exception 
	public final ResponseEntity<DefaultErrorResponse> handleAllExceptions(Exception ex, WebRequest request){
		DefaultErrorResponse errorResponse = new DefaultErrorResponse(new Date(), ex.getMessage(),
					request.getDescription(true));
		
		return new ResponseEntity<DefaultErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<DefaultErrorResponse> userNotFoundException(UserNotFoundException ex, WebRequest request){
		DefaultErrorResponse errorResponse = new DefaultErrorResponse(new Date(), ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<DefaultErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		//list all error in details
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();
		List<String> listErrors = new ArrayList<String>();
		
		//TODO need to fetch only default method and store into this list. Return it as part of the response
		List<ObjectError> defaultErrors =errors.stream().
							filter( p -> p.getDefaultMessage()!= null ).collect(Collectors.toList());
		System.out.println("**************************");
		defaultErrors.stream().forEach(e -> System.out.println(e));
		System.out.println("=====================");
		for(ObjectError e : errors) {
			listErrors.add(e.getDefaultMessage());
//			System.out.println(e.getCode()+" >>> "+e.getDefaultMessage());
		}
		DefaultErrorResponse errorResponse = new DefaultErrorResponse(new Date(), "Validation failed", 
				listErrors.toString());
		return new ResponseEntity<Object>(errorResponse,HttpStatus.BAD_REQUEST);
	}
}
