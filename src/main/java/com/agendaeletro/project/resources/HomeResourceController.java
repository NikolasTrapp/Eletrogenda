package com.agendaeletro.project.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.agendaeletro.project.entities.Teacher;

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

	@GetMapping("/classesController")
	public String classesController() {
		return "classes";
	}

	@GetMapping("/equipmentController")
	public String equipmentController() {
		return "equipments";
	}

	@GetMapping("/schedulingController")
	public String schedulingController(HttpServletRequest request) {
		Teacher t = (Teacher) request.getSession().getAttribute("teacher");
		if (t != null && t.getRole().equals("ADMIN")){
			return "schedulings";
		} else {
			return "redirect:/login-page";
		}
	}

	@GetMapping("/mainPage")
	public String mainPage(HttpServletRequest request) {
		Teacher t = (Teacher) request.getSession().getAttribute("teacher");
		if (t != null){
			return "mainPage";
		} else {
			return "redirect:/login-page";
		}
	}

	@GetMapping("/login-page")
	public String loginPage() {
		return "login-page";
	}

	@GetMapping("/signIn-page")
	public String signInPage() {
		return "signIn-page";
	}

	@GetMapping("/signOut")
	public String signOut(HttpServletRequest request) {
		request.getSession().removeAttribute("teacher");
		return "login-page";
	}

}
