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

import com.agendaeletro.project.entities.Scheduling;
import com.agendaeletro.project.services.ClassroomService;
import com.agendaeletro.project.services.SchedulingService;
import com.agendaeletro.project.services.TeacherService;

@RestController // Anotação para definir que esta classe é uma classe controladora
@RequestMapping(value = "/schedulings")
public class SchedulingResources {

	@Autowired
	private SchedulingService service;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ClassroomService classroomService;

	@GetMapping
	public ResponseEntity<List<Scheduling>> queryAll() {
		List<Scheduling> list = service.queryAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/insertScheduling")
	public ResponseEntity<Scheduling> insert(@RequestBody Scheduling scheduling) {
		System.out.println(scheduling);
		scheduling.setTeacher(teacherService.queryById(scheduling.getTeacher().getId()));
		scheduling.setClassroom(classroomService.queryById(scheduling.getClassroom().getId()));
		scheduling = service.insert(scheduling);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(scheduling.getId())
				.toUri();
		return ResponseEntity.created(uri).body(scheduling);
	}

	@DeleteMapping(value = "/deleteScheduling/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/updateScheduling/{id}")
	public ResponseEntity<Scheduling> update(@PathVariable Long id, @RequestBody Scheduling scheduling) {
		scheduling = service.update(id, scheduling);
		return ResponseEntity.ok().body(scheduling);
	}

}
