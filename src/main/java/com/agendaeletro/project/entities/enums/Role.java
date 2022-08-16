package com.agendaeletro.project.entities.enums;

public enum Role {
	
	/*
	 * Esta enum representa as possíveis opções para cadastrar um professor
	 * ela serve como uma camada de segurança e padronização do atributo Role
	 * */
	
	ADMIN("Admin"),
	TEACHER("Teacher");

	private String str;

	private Role(String str) {
		this.str = str;
	}

	public String getRole() {
		return str;
	}
}