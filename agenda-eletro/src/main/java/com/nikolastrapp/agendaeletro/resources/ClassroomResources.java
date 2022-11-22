package com.nikolastrapp.agendaeletro.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nikolastrapp.agendaeletro.entities.Classroom;
import com.nikolastrapp.agendaeletro.services.ClassroomService;

@RestController // Anotação para definir que esta classe é uma classe controladora
@CrossOrigin("*") // Permitindo o compartilhamento de recursos entre diferentes origens
@RequestMapping(value = "/classrooms") // Rota de acesso às rotas referentes a esse controlador
public class ClassroomResources {
	/*
	 * Classe responsável por guardar as rotas essenciais destinadas á entidade
	 * Classroom, esta classe controla operações como queryes, inserts, deletes
	 * e updates, esta é a classe mais próxima do usuário.
	 * As anotações GetMapping, PostMapping, PutMapping e DeleteMapping controlam
	 * as operaçoes essenciais correspondentes aos seus métodos de requisiçao
	 * e correspondentes a sua classe de entidade
	 */

	@Autowired // Definindo que a injeção de dependencia será feita automáticamente
	private ClassroomService service; // Definindo camada de serviço da sala de aula

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
