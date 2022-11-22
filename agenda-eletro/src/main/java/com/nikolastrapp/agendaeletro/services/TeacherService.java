package com.nikolastrapp.agendaeletro.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nikolastrapp.agendaeletro.entities.Teacher;
import com.nikolastrapp.agendaeletro.entities.enums.Role;
import com.nikolastrapp.agendaeletro.repositories.TeacherRepository;
import com.nikolastrapp.agendaeletro.services.exceptions.DatabaseException;
import com.nikolastrapp.agendaeletro.services.exceptions.ResourceNotFoundException;

@Service
public class TeacherService {
	/*
	 * Esta classe guarda as funções que realizam as operações do banco de dados
	 * que são chamadas pela camada de recursos
	 */

	@Autowired // Injeção de dependencia automático
	private TeacherRepository teacherRepository;
	@Autowired
	private PasswordEncoder encoder;

	public List<Teacher> queryAll() {
		return teacherRepository.findAll();
	}

	public Teacher queryById(Long id) {
		Optional<Teacher> obj = teacherRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Teacher queryByName(String name) {
		Teacher obj = teacherRepository.findByName(name);
		return obj;
	}

	public Teacher insert(Teacher teacher) {
		teacher.setPassword(encoder.encode(teacher.getPassword())); // Criptografar
		teacher.setRole(Role.TEACHER); // Definir a permissão padrão
		return teacherRepository.save(teacher);

	}

	public void delete(Long id) {
		try {
			teacherRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Teacher update(Long id, Teacher teacher) {
		try {
			Teacher entity = teacherRepository.getReferenceById(id);
			updateData(entity, teacher);
			return teacherRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Teacher entity, Teacher teacher) {
		if (!teacher.getName().isBlank()) {
			entity.setName(teacher.getName());
		}
		if (!teacher.getEmail().isBlank()) {
			entity.setEmail(teacher.getEmail());
		}
		if (!teacher.getPassword().isBlank()) {
			entity.setPassword(encoder.encode(teacher.getPassword()));
		}
	}

}
