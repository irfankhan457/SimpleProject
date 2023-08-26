package com.khan.rest.webservices.FirstRestFulWebServices.user.error;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.khan.rest.webservices.FirstRestFulWebServices.user.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false), "Java Dev");
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false), "UserNotFoundException");
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		StringBuilder sb = new StringBuilder();
		for(FieldError s : ex.getFieldErrors()) {
			sb.append(s.getDefaultMessage());
		}
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				sb.toString(), request.getDescription(false), "Bad Request");
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
