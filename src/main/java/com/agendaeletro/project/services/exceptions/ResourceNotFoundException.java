package com.agendaeletro.project.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	// Classe que representa erro caso uma entidade/recurso não seja encontrada

	public ResourceNotFoundException(Object obj) {
		super("Resource not found. id: " + obj);
	}

}
