package com.agendaeletro.project.resources;

import java.net.URI;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

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

import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.services.TeacherService;

@RestController // Anotação para definir que esta classe é uma classe controladora
@RequestMapping(value = "/teachers")
public class TeacherResources {

	@Autowired
	private TeacherService service;

	@GetMapping
	public ResponseEntity<List<Teacher>> queryAll() {
		List<Teacher> list = service.queryAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/queryTeacherById/{id}")
	public ResponseEntity<Teacher> queryById(@PathVariable Long id) {
		Teacher teacher = service.queryById(id);
		return ResponseEntity.ok().body(teacher);
	}

	@GetMapping(value = "/queryTeacherByName/{name}")
	public ResponseEntity<Teacher> queryByName(@PathVariable String name) {
		Teacher teacher = service.queryByName(name);
		return ResponseEntity.ok().body(teacher);
	}
	
	@PostMapping("/insertTeacher")
	public ResponseEntity<Teacher> insert(@RequestBody Teacher teacher){
		teacher = service.insert(teacher);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacher.getId()).toUri();
		return ResponseEntity.created(uri).body(teacher);
	}
	
	@DeleteMapping(value = "/deleteTeacher/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/updateTeacher/{id}")
	public ResponseEntity<Teacher> update(@PathVariable Long id, @RequestBody Teacher teacher){
		teacher = service.update(id, teacher);
		return ResponseEntity.ok().body(teacher);
	}

}
