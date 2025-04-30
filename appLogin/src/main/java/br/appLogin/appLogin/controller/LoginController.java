package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastroUsuario")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario()); // Cria um novo objeto 'Usuario' e passa para o template
        return "cadastro"; // Nome do template
    }

}




