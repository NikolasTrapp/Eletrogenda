package com.agendaeletro.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendaeletro.project.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	

}
