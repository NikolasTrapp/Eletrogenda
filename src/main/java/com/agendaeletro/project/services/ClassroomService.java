package com.agendaeletro.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendaeletro.project.entities.Classroom;
import com.agendaeletro.project.repositories.ClassroomRepository;

@Service
public class ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepository;

	public List<Classroom> queryAll() {
		return classroomRepository.findAll();
	}

	public Classroom queryById(Long id) {
		Optional<Classroom> obj = classroomRepository.findById(id);
		return obj.get();
	}

	public Classroom insert(Classroom classroom) {
		return classroomRepository.save(classroom);
	}
	
	public void delete(Long id) {
		classroomRepository.deleteById(id);;
	}

}
