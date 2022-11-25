package com.nikolastrapp.agendaeletro.services.exceptions;

public class NotCompatibleDate extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NotCompatibleDate() {
		super("This date is already registered");
	}

}
