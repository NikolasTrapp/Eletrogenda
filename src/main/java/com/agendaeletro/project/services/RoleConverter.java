package com.agendaeletro.project.services;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.agendaeletro.project.entities.enums.Role;

@Converter(autoApply = true) // Defindo que esta classe será chamada automáticamente
public class RoleConverter implements AttributeConverter<Role, String> {
	/*
	 * Esta classe tem como função converter o atributo "role" do professor de
	 * Enum para String a fim de ser compatível com o banco de dados e assim
	 * possa ser salvo com seu respectivo valor
	 */

	@Override // Sobreposição de método
	public String convertToDatabaseColumn(Role role) {
		// Convertendo de Enum para String
		if (role == null) {
			return null;
		}
		return role.getRole();
	}

	@Override
	public Role convertToEntityAttribute(String code) {
		// Convertendo de String para Enum
		if (code == null) {
			return null;
		}

		return Stream.of(Role.values()).filter(c -> c.getRole()
				.equals(code.toUpperCase()))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
