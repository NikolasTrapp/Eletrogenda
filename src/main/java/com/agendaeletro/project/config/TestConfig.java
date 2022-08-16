package com.agendaeletro.project.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.agendaeletro.project.entities.Classroom;
import com.agendaeletro.project.entities.Equipment;
import com.agendaeletro.project.entities.Scheduling;
import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.entities.enums.Role;
import com.agendaeletro.project.repositories.ClassroomRepository;
import com.agendaeletro.project.repositories.EquipmentRepository;
import com.agendaeletro.project.repositories.SchedulingReporitory;
import com.agendaeletro.project.repositories.TeacherRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	//Esta é uma classe de testes, ela cria objetos de teste para testar o banco de ddados

	@Autowired // Essa anotação faz a injeção de dependencia automáticamente
	//criando os objetos de repositório
	private TeacherRepository teacherRepository;
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private ClassroomRepository classroomRepository;
	@Autowired
	private SchedulingReporitory schedulingReporitory;

	@Override
	public void run(String... args) throws Exception {
		//Nesta função são criados os objetos assim que o servidor inicia
		//os objetos são salvos num banco de dados na memória e são apagados
		//ao término da aplicação

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Teacher p = new Teacher(null, "Eder", "eder@gmail.com", "123", Role.TEACHER);
		Teacher p1 = new Teacher(null, "Nikolas", "nikolas@gmail.com", "123", Role.ADMIN);

		Classroom c = new Classroom(null, "C20");
		Classroom c1 = new Classroom(null, "D04");

		Equipment e = new Equipment(null, "Martelo", "Null", 15);
		Equipment e1 = new Equipment(null, "Solda", "Tudo quebrada", 20);

		Scheduling s = new Scheduling(null, sdf.parse("11/08/2022"), new Date(), p, c);
		Scheduling s1 = new Scheduling(null, sdf.parse("09/08/2022"), new Date(), p1, c1);

		// Adicionando os dados ao banco de dados
		teacherRepository.saveAll(Arrays.asList(p, p1));
		classroomRepository.saveAll(Arrays.asList(c, c1));
		equipmentRepository.saveAll(Arrays.asList(e, e1));
		schedulingReporitory.saveAll(Arrays.asList(s, s1));

		//Adicionando equipamentos aos agendamentos
		s.getEquipment().add(e);
		s.getEquipment().add(e1);
		s1.getEquipment().add(e);
		s1.getEquipment().add(e1);

		schedulingReporitory.saveAll(Arrays.asList(s, s1));

	}

}
