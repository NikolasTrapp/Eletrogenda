package com.agendaeletro.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendaeletro.project.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	
	Teacher findByName(String name);
	
}
