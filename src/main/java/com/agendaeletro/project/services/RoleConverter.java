package com.agendaeletro.project.services;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.agendaeletro.project.entities.enums.Role;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

	@Override
	public String convertToDatabaseColumn(Role role) {
		if (role == null) {
			return null;
		}
		return role.getRole();
	}

	@Override
	public Role convertToEntityAttribute(String code) {
		if (code == null) {
			return null;
		}

		return Stream.of(Role.values()).filter(c -> c.getRole()
				.equals(code.toUpperCase()))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
