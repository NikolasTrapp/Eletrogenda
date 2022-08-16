package com.agendaeletro.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agendaeletro.project.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	// Esta interface define um "repositório" com a entidade Teacher para
    // ganhar acesso às funções do Jpa

	// Função para retornar um teacher através do nome
	Teacher findByName(String name);

	// Função para retornar o nome, email e senha de um determinado professor
	@Query(value = "SELECT * FROM teacher t WHERE t.name = ?1 AND t.email = ?2", nativeQuery = true)
	Teacher findUser(String name, String email);

	@Query(value = "SELECT * FROM teacher t WHERE t.name = ?1 OR t.email = ?2", nativeQuery = true)
	Teacher findUserByEmailOrName(String name, String email);
}
