package com.agendaeletro.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendaeletro.project.entities.Scheduling;
import com.agendaeletro.project.services.SchedulingService;

@RestController // Anotação para definir que esta classe é uma classe controladora
@RequestMapping(value = "/schedulings")
public class SchedulingResources {

	@Autowired
	private SchedulingService service;

	@GetMapping
	public ResponseEntity<List<Scheduling>> queryAll() {
		List<Scheduling> list = service.queryAll();
		return ResponseEntity.ok().body(list);
	}

}
