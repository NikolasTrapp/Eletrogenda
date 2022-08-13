package com.agendaeletro.project.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.repositories.TeacherRepository;
import com.agendaeletro.project.services.exceptions.DatabaseException;
import com.agendaeletro.project.services.exceptions.ResourceNotFoundException;

@Service
public class TeacherService {

	@Autowired // Esta notação faz o spring injetar automaticamente a dependencia
	private TeacherRepository teacherRepository;

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
		if (teacher.getName() != null) {
			entity.setName(teacher.getName());
		}
		if (teacher.getEmail() != null) {
			entity.setEmail(teacher.getEmail());
		}
		if (teacher.getPassword() != null) {
			entity.setPassword(teacher.getPassword());
		}
		if (teacher.getRole() != null) {
			entity.setRole(teacher.getRole());
		}
	}

}
