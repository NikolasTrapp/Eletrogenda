package com.agendaeletro.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.services.TeacherService;

@RestController //Anotação para definir que esta classe é uma classe controladora
@RequestMapping(value = "/professors")
public class TeacherResources {
	
	@Autowired
	private TeacherService service;

	@GetMapping
	public ResponseEntity<List<Teacher>> queryAll(){
		List<Teacher> list = service.queryAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/queryById/{id}")
	public ResponseEntity<Teacher> queryById(@PathVariable Long id){
		Teacher p = service.queryById(id);
		return ResponseEntity.ok().body(p);
	}
	
	@GetMapping(value = "/queryByName/{name}")
	public ResponseEntity<Teacher> queryByName(@PathVariable String name){
		Teacher p = service.queryByName(name);
		return ResponseEntity.ok().body(p);
	}
	
}
