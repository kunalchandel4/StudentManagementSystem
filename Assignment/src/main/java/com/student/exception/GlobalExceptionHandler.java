package com.student.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	

	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ShoppingError> myCourseHandler(CourseNotFoundException ae, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ae.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ShoppingError> myStudentHandler(StudentNotFoundException ae, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ae.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ShoppingError> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e,
			WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(e.getFieldError().toString());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ShoppingError> myExceptionHandler(Exception e, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ShoppingError> noHandlerFoundHandler(NoHandlerFoundException e, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
}
