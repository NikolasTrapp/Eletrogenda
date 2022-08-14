package com.agendaeletro.project.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeResourceController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/teacherController")
	public String teacherController() {
		return "teachers";
	}
	
	@GetMapping("/classroomController")
	public String classroomController() {
		return "classrooms";
	}
	
	@GetMapping("/equipmentController")
	public String equipmentController() {
		return "equipments";
	}
	
	@GetMapping("/schedulingController")
	public String schedulingController() {
		return "schedulings";
	}

}
