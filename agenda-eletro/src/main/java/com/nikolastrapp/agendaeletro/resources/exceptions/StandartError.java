package com.nikolastrapp.agendaeletro.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandartError implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * Classe base destinada a criar erros personalizados com código htpp desejado
	 */

	// A anotaçao abaixo define o formado de hora
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Brazil/East")
	// Atributos
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	// Construtor vazio
	public StandartError() {
	}

	// Sobrecarga de construtor com parâmetros
	public StandartError(Instant timestamp, Integer status, String error, String message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	// Getters e setters
	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
