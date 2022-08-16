package com.agendaeletro.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendaeletro.project.entities.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    // Esta interface define um "repositório" com a entidade Classroom para
    // ganhar acesso às funções do Jpa

}
