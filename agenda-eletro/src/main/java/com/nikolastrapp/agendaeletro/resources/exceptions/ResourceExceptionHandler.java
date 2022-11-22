package com.nikolastrapp.agendaeletro.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nikolastrapp.agendaeletro.services.exceptions.DatabaseException;
import com.nikolastrapp.agendaeletro.services.exceptions.ResourceNotFoundException;

@ControllerAdvice // Anotação para definir que esta classe é um controlador de exceções
public class ResourceExceptionHandler {
	// Classe criada com a função de interceptar erros e retorna-los com códigos
	// http específicos

	@ExceptionHandler(ResourceNotFoundException.class) // Definindo que a exceção ResourceNotFoundException será
													   // interceptado aqui
	public ResponseEntity<StandartError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found"; // Mensagem de erro
		HttpStatus status = HttpStatus.NOT_FOUND; // Definindo código htpp desejado
		// Criando objeto de erro com os valores desejados
		StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err); // Retornando o objeto como entidade de resposta
	}

	@ExceptionHandler(DatabaseException.class) // Definindo que a exceção DatabaseException será interceptad aqui
	public ResponseEntity<StandartError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
