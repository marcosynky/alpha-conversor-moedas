package aula.projectdbspring.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller


public class AuthController {

    // Página de login
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Exibe a página de login
    }

    // Lógica de login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Lógica para o login
        return "redirect:/home";  // Redireciona para a página de home após o login bem-sucedido
    }

    // Página de registro
    @GetMapping("/register")
    public String registerPage() {
        return "register";  // Exibe a página de registro
    }

    // Lógica de criação de usuário
    @PostMapping("/register/create")
    public String createUser(@RequestParam String username, @RequestParam String password,
                             @RequestParam String role, Model model) {
        // Lógica para criar o usuário e salvar no banco de dados
        return "redirect:/login";  // Redireciona para a página de login após o registro
    }
}
