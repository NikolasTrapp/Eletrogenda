package com.agendaeletro.project.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendaeletro.project.entities.Professor;

@RestController //Anotação para definir que esta classe é uma classe controladora
@RequestMapping(value = "/professors")
public class ProfessorResources {

	@GetMapping
	public ResponseEntity<Professor> queryAll(){
		Professor p = new Professor(1L, "Eder", "eder@gmail.com", "123", "professor");
		return ResponseEntity.ok().body(p);
	}
	
}
