package com.agendaeletro.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agendaeletro.project.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Teacher findByName(String name);

	@Query(value = "SELECT * FROM teacher t WHERE t.name = ?1 AND t.email = ?2 AND t.password = ?3", nativeQuery = true)
	Teacher findUser(String name, String email, String password);

}
