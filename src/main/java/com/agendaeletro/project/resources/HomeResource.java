package com.agendaeletro.project.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeResource {

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index.html");
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		return mv;
	}

}
