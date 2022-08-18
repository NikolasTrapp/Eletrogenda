package com.agendaeletro.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendaeletro.project.entities.Class;

public interface ClassRepository extends JpaRepository<Class, Long>{
    
}
