package com.nikolastrapp.agendaeletro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikolastrapp.agendaeletro.entities.Class;

public interface ClassRepository extends JpaRepository<Class, Long>{
    
}
