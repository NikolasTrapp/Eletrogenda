package com.agendaeletro.project.services;

import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.repositories.TeacherRepository;

@Service
public class TeacherService {

	@Autowired // Esta notação faz o spring injetar automaticamente a dependencia
	private TeacherRepository teacherRepository;

	public List<Teacher> queryAll() {
		return teacherRepository.findAll();
	}

	public Teacher queryById(Long id) {
		Optional<Teacher> obj = teacherRepository.findById(id);
		return obj.get();
	}

	public Teacher queryByName(String name) {
		Teacher obj = teacherRepository.findByName(name);
		return obj;
	}

	public Teacher insert(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	public void delete(Long id) {
		teacherRepository.deleteById(id);;
	}
	
	public Teacher update(Long id, Teacher teacher) {
		Teacher entity = teacherRepository.getReferenceById(id);
		updateData(entity, teacher);
		return teacherRepository.save(entity);
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
