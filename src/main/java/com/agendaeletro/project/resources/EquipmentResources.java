package com.agendaeletro.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agendaeletro.project.entities.Equipment;
import com.agendaeletro.project.entities.Teacher;
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
	
	@PostMapping("/insertEquipment")
	public ResponseEntity<Equipment> insert(@RequestBody Equipment equipment){
		equipment = service.insert(equipment);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(equipment.getId()).toUri();
		return ResponseEntity.created(uri).body(equipment);
	}
	
	@DeleteMapping(value = "/deleteEquipment/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
