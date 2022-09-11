package com.agendaeletro.project.resources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.repositories.TeacherRepository;

@RestController // Anotação para definir que esta classe é uma classe controladora
@CrossOrigin("*")
public class UserResources {

	@Autowired // Esta notação faz o spring injetar automaticamente a dependencia
	private TeacherRepository teacherRepository;
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping(value = "/validateLogin")
	public ResponseEntity<Object> validateLogin(@RequestBody Map<String, String> user, HttpServletRequest request) {
		try {
			Teacher t = teacherRepository.findUser(user.get("name"), user.get("email"));
			if (t != null && encoder.matches(user.get("password"), t.getPassword())) {
				request.getSession().setAttribute("teacher", t);
				return ResponseEntity.status(HttpStatus.OK)
						.body(Map.of("result", "ok", "details", "login approved", "teacher_id", t.getId()));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(Map.of("result", "error", "details", "login denied"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("result", "error", "details", e.getMessage()));
		}
	}

}
