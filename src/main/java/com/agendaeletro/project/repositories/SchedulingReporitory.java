package com.agendaeletro.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendaeletro.project.entities.Scheduling;

public interface SchedulingReporitory extends JpaRepository<Scheduling, Long>{

}
