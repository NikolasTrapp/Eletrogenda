package com.agendaeletro.project.services;

import java.util.List;
import java.util.Optional;

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

}
