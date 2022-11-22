package com.nikolastrapp.agendaeletro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikolastrapp.agendaeletro.entities.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
	// Esta interface define um "repositório" com a entidade Equipment para
    // ganhar acesso às funções do Jpa
}
