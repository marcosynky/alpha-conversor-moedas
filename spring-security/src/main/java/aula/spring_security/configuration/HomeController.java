package aula.spring_security.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Modificar a rota para '/home' se não estiver já configurada dessa forma
    @GetMapping("/home")
    public String home() {
        return "home";  // A página inicial será a home.html
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // A página de login será a login.html
    }
}
