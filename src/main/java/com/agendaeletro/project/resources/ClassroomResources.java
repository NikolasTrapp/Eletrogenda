package com.agendaeletro.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendaeletro.project.entities.Classroom;
import com.agendaeletro.project.services.ClassroomService;

@RestController // Anotação para definir que esta classe é uma classe controladora
@RequestMapping(value = "/classrooms")
public class ClassroomResources {

	@Autowired
	private ClassroomService service;

	@GetMapping
	public ResponseEntity<List<Classroom>> queryAll() {
		List<Classroom> list = service.queryAll();
		return ResponseEntity.ok().body(list);
	}

}
