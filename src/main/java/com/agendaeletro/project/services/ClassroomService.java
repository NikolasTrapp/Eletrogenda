package com.agendaeletro.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.agendaeletro.project.entities.Classroom;
import com.agendaeletro.project.repositories.ClassroomRepository;
import com.agendaeletro.project.services.exceptions.ResourceNotFoundException;

@Service // Anotação definindo que esta classe é um serviço
public class ClassroomService {
	/*
	 * Esta classe guarda as funções que realizam as operações do banco de dados
	 * que são chamadas pela camada de recursos
	 */

	@Autowired // Injeção de dependencia automático
	private ClassroomRepository classroomRepository;

	public List<Classroom> queryAll() {
		return classroomRepository.findAll();
	}

	public Classroom queryById(Long id) {
		Optional<Classroom> obj = classroomRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Classroom insert(Classroom classroom) {
		return classroomRepository.save(classroom);
	}

	public void delete(Long id) {
		try {
			classroomRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public Classroom update(Long id, Classroom classroom) {
		try {
			Classroom entity = classroomRepository.getReferenceById(id);
			updateData(entity, classroom);
			return classroomRepository.save(entity);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Classroom entity, Classroom classroom) {
		if (classroom.getName() != null) {
			entity.setName(classroom.getName());
		}
	}

}
