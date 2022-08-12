package com.agendaeletro.project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.agendaeletro.project.entities.Professor;
import com.agendaeletro.project.repositories.ProfessorRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired //Essa anotação faz a injeção de dependencia automáticamente
	private ProfessorRepository professorRepository;

	@Override
	public void run(String... args) throws Exception {
		Professor p = new Professor(null, "Eder", "eder@gmail.com", "123", "professor");
		Professor p2 = new Professor(null, "Nikolas", "nikolas@gmail.com", "123", "admin");
		
		professorRepository.saveAll(Arrays.asList(p, p2));
		
	}
		
}
