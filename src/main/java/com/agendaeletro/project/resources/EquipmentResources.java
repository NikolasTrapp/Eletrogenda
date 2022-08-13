package com.agendaeletro.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendaeletro.project.entities.Equipment;
import com.agendaeletro.project.services.EquipmentService;

@RestController // Anotação para definir que esta classe é uma classe controladora
@RequestMapping(value = "/equipments")
public class EquipmentResources {

	@Autowired
	private EquipmentService service;

	@GetMapping
	public ResponseEntity<List<Equipment>> queryAll() {
		List<Equipment> list = service.queryAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/queryEquipmentById/{id}")
	public ResponseEntity<Equipment> queryById(@PathVariable Long id) {
		Equipment p = service.queryById(id);
		return ResponseEntity.ok().body(p);
	}

}
