package com.agendaeletro.project.entities.enums;

public enum Role {
	
	/*
	 * Esta enum representa as possíveis opções para cadastrar um professor
	 * ela serve como uma espécie de filtro
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