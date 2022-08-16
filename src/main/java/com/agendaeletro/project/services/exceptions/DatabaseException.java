package com.agendaeletro.project.services.exceptions;

public class DatabaseException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	// Classe que representa erros na base de dados

	public DatabaseException(String msg) {
		super(msg);
	}
	
}
