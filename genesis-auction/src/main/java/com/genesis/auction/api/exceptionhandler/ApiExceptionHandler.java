package com.genesis.auction.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.genesis.auction.domain.exception.MaximumNumberOfBidsReachedException;
import com.genesis.auction.domain.exception.NoWinnerException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MaximumNumberOfBidsReachedException.class)
	public ResponseEntity<?> handleInvalidBidValueException(MaximumNumberOfBidsReachedException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.LOCKED, request);
	}

	@ExceptionHandler(NoWinnerException.class)
	public ResponseEntity<?> handleNoWinnerException(NoWinnerException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problem.builder()
					.dateTime(LocalDateTime.now())
					.title(status.getReasonPhrase())
					.status(status.value())
					.detail(ex.getMessage())
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.dateTime(LocalDateTime.now())
					.title((String)body)
					.status(status.value())
					.detail(ex.getMessage())
					.build();
		}
			
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
	
}
