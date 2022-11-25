package com.nikolastrapp.agendaeletro.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nikolastrapp.agendaeletro.entities.Scheduling;

public interface SchedulingReporitory extends JpaRepository<Scheduling, Long>{
    // Esta interface define um "repositório" com a entidade Scheduling para
    // ganhar acesso às funções do Jpa  
	
	@Query(value = "SELECT * FROM scheduling s WHERE s.classroom_id = ?1 OR s.class_id = ?2", nativeQuery = true)
	ArrayList<Scheduling> getClassesClassrooms(Long s1, Long s2);
}
