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
import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.entities.Scheduling;
import com.agendaeletro.project.repositories.ClassroomRepository;
import com.agendaeletro.project.repositories.EquipmentRepository;
import com.agendaeletro.project.repositories.TeacherRepository;
import com.agendaeletro.project.repositories.SchedulingReporitory;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired //Essa anotação faz a injeção de dependencia automáticamente
	private TeacherRepository teacherRepository;
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private ClassroomRepository classroomRepository;
	@Autowired
	private SchedulingReporitory schedulingReporitory;

	@Override
	public void run(String... args) throws Exception {
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Teacher p = new Teacher(null, "Eder", "eder@gmail.com", "123", "professor");
		Teacher p1 = new Teacher(null, "Nikolas", "nikolas@gmail.com", "123", "admin");
		
		Equipment e = new Equipment(null, "Martelo", "Null", 15, null);
		Equipment e1 = new Equipment(null, "Solda", "Tudo quebrada", 20, null);
		
		Classroom c = new Classroom(null, "C20");
		Classroom c1 = new Classroom(null, "D04");
		
		Scheduling s = new Scheduling(null, sdf.parse("11/08/2022"), new Date(), p, c, Arrays.asList(e));
		p.addScheduling(s);
		e.setScheduling(s);
		c.addScheduling(s);
		Scheduling s1 = new Scheduling(null, sdf.parse("09/08/2022"), new Date(), p1, c1, Arrays.asList(e1));
		p1.addScheduling(s1);
		e1.setScheduling(s1);
		c1.addScheduling(s1);
		
		teacherRepository.saveAll(Arrays.asList(p, p1));
		equipmentRepository.saveAll(Arrays.asList(e, e1));
		classroomRepository.saveAll(Arrays.asList(c, c1));
		schedulingReporitory.saveAll(Arrays.asList(s, s1));*/
		
	}
		
}
