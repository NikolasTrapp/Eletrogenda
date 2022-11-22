package com.nikolastrapp.agendaeletro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikolastrapp.agendaeletro.entities.Scheduling;

public interface SchedulingReporitory extends JpaRepository<Scheduling, Long>{
    // Esta interface define um "repositório" com a entidade Scheduling para
    // ganhar acesso às funções do Jpa  
}
