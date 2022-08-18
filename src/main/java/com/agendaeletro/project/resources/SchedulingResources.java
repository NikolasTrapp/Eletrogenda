package com.agendaeletro.project.resources;

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

import com.agendaeletro.project.entities.Scheduling;
import com.agendaeletro.project.services.SchedulingService;
import com.agendaeletro.project.services.exceptions.DatabaseException;

@RestController // Anotação para definir que esta classe é uma classe controladora
@CrossOrigin("*") // Permitindo o compartilhamento de recursos entre diferentes origens
@RequestMapping(value = "/schedulings") // Definindo rota de acesso às rotas referentes a esse controlador
public class SchedulingResources {

	/*
	 * Classe responsável por guardar as rotas essenciais destinadas á entidade
	 * Scheduling, esta classe controla operações como queryes, inserts, deletes
	 * e updates, esta é a classe mais próxima do usuário.
	 * As anotações GetMapping, PostMapping, PutMapping e DeleteMapping controlam
	 * as operaçoes essenciais correspondentes aos seus métodos de requisiçao
	 * e correspondentes a sua classe de entidade
	 */

	@Autowired // Definindo que a injeção de dependencia será feita automáticamente
	private SchedulingService service; // Definindo camada de serviço do agendamento

	@GetMapping
	public ResponseEntity<List<Scheduling>> queryAll() {
		List<Scheduling> list = service.queryAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/insertScheduling")
	public ResponseEntity<Scheduling> insert(@RequestBody Scheduling scheduling) {
		if (!scheduling.compareTime()) {
			throw new DatabaseException("Invalid date.");
		}
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
