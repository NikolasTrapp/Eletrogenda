package com.agendaeletro.project.entities.enums;

public enum Role {
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