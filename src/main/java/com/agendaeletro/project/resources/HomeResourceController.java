package com.agendaeletro.project.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Definindo que esta classe é uma classe controladora
public class HomeResourceController {
	/*
	 * Esta classe guarda as rotas que retornam as páginas html para o usuário
	 * assim que sua rota é chamada, GetMapping é a anotação que detecta quando
	 * uma rota foi acionada e retorna o template correspondente a sua rota
	 */

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

	@GetMapping("/mainPage")
	public String mainPage() {
		return "mainPage";
	}

	@GetMapping("/loginPage")
	public String loginPage() {
		return "login";
	}

}
