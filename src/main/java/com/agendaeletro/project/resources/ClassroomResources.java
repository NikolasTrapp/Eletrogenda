package com.agendaeletro.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@PostMapping("/insertClassroom")
	public ResponseEntity<Classroom> insert(@RequestBody Classroom classroom) {
		classroom = service.insert(classroom);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(classroom.getId())
				.toUri();
		return ResponseEntity.created(uri).body(classroom);
	}

	@DeleteMapping(value = "/deleteClassroom/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/updateClassroom/{id}")
	public ResponseEntity<Classroom> update(@PathVariable Long id, @RequestBody Classroom classroom) {
		classroom = service.update(id, classroom);
		return ResponseEntity.ok().body(classroom);
	}

}
