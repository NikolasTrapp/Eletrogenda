package com.agendaeletro.project.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.repositories.TeacherRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController // Anotação para definir que esta classe é uma classe controladora
@CrossOrigin("*")
public class UserResources {

	@Autowired // Esta notação faz o spring injetar automaticamente a dependencia
	private TeacherRepository teacherRepository;

	@PostMapping(value = "/validateLogin")
	public ResponseEntity<Object> validateLogin(@RequestBody Map<String, String> user) {
		System.out.println("Map received: " + user.values());
		try {
			Teacher t = teacherRepository.findUser(user.get("name"), user.get("email"), user.get("password"));

			if (t != null) {
				System.out.println("Teacher found: " + t);
				return ResponseEntity.status(HttpStatus.OK).body(Map.of("result", "ok", "details", "login approved"));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("result", "login denied", "details", "teacher not found"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("result", "error", "details", e.getMessage()));
		}
	}

}
