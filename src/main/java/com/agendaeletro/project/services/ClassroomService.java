package com.agendaeletro.project.services;

import java.util.List;

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

}
