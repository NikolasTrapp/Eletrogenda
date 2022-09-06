package com.agendaeletro.project.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.agendaeletro.project.entities.Teacher;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/mainPage")
    public String mainPage(HttpServletRequest request) {
        // caso a sessão esteja vazia, redirecionar pra pagina de login, caso contrário para a pagina de agendamentos
        return request.getSession().getAttribute("teacher") == null ? "redirect:/login-page" : "schedule";
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
        //Se a sessão não estiver vazia, será invalidada, caso contrário
        if (request.getSession().getAttribute("teacher") != null) request.getSession().invalidate();
        return "redirect:/login-page";

    }

}
