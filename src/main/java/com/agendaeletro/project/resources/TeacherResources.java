package com.agendaeletro.project.resources;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.services.TeacherService;

@RestController // Anotação para definir que esta classe é uma classe controladora
@CrossOrigin("*") // Permitindo o compartilhamento de recursos entre diferentes origens
@RequestMapping(value = "/teachers") // Definindo rota de acesso às rotas referentes a esse controlador
public class TeacherResources {
	/*
	 * Classe responsável por guardar as rotas essenciais destinadas á entidade
	 * Equipment, esta classe controla operações como queryes, inserts, deletes
	 * e updates, esta é a classe mais próxima do usuário.
	 * As anotações GetMapping, PostMapping, PutMapping e DeleteMapping controlam
	 * as operaçoes essenciais correspondentes aos seus métodos de requisiçao
	 * e correspondentes a sua classe de entidade
	 */

	@Autowired // Definindo que a injeção de dependencia será feita automáticamente
	private TeacherService service; // Definindo camada de serviço do professor

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
	public ResponseEntity<Object> insert(@RequestBody Teacher teacher, HttpServletRequest request) {
		try {	
			teacher = service.insert(teacher);
			request.getSession().setAttribute("teacher", teacher);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("result", "ok", "details",
					String.format("Teacher %s inserted. Id: %d", teacher.getName(), teacher.getId()), "teacher_id", teacher.getId()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Map.of("result", "error", "details", e.getMessage()));
		}
	}

	@DeleteMapping(value = "/deleteTeacher/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/updateTeacher/{id}")
	public ResponseEntity<Teacher> update(@PathVariable Long id, @RequestBody Teacher teacher) {
		teacher = service.update(id, teacher);
		return ResponseEntity.ok().body(teacher);
	}

}
