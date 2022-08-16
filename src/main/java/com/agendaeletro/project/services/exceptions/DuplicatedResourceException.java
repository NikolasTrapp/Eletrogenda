package com.agendaeletro.project.services.exceptions;

public class DuplicatedResourceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DuplicatedResourceException(String str){
        super(str);
    }
    
}
