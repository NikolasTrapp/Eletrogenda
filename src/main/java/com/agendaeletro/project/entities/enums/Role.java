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
	
	/*public static Role valueOf(String str) {
		for (Role r : Role.values()) {
			if (r.getRole() == str) {
				return r;
			}
		}
		throw new IllegalArgumentException("Invalid role code!");
	}*/
}
