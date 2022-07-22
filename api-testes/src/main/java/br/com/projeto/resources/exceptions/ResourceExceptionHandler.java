package br.com.projeto.resources.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.projeto.services.exceptions.DataIntegrityViolationException;
import br.com.projeto.services.exceptions.ObjectNotFoundException;

//manipulador de exceções da nossa camada de recursos

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, 
			HttpServletRequest request) {
		StandardError erro = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolationException ex, 
			HttpServletRequest request) {
		StandardError erro = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), 
				ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}