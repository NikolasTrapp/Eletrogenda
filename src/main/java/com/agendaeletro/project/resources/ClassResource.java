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

import com.agendaeletro.project.entities.Class;
import com.agendaeletro.project.services.ClassService;

@RestController // Anotação para definir que esta classe é uma classe controladora
@CrossOrigin("*") // Permitindo o compartilhamento de recursos entre diferentes origens
@RequestMapping(value = "/class") // Rota de acesso às rotas referentes a esse controlador
public class ClassResource {

    @Autowired
    private ClassService service;

    @GetMapping
    public ResponseEntity<List<Class>> queryAll() {
        List<Class> list = service.queryAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/insertClass")
    public ResponseEntity<Class> insert(@RequestBody Class c) {
        c = service.insert(c);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
                .toUri();
        return ResponseEntity.created(uri).body(c);
    }

    @DeleteMapping(value = "deleteClass/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "updateClass/{id}")
    public ResponseEntity<Class> update(@PathVariable Long id, @RequestBody Class c) {
        c = service.update(c, id);
        return ResponseEntity.ok().body(c);
    }

}
