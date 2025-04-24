package dio.web.api.controller;

// Importando as anotações e classes do Spring que permitem a criação de endpoints para a Web API
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// A anotação @RestController indica que esta classe é um controlador para uma API REST.
// Ela combina @Controller e @ResponseBody, significando que os métodos da classe retornam dados diretamente (não páginas HTML).
@RestController
public class WelcomeController {

    // A anotação @GetMapping é usada para mapear requisições HTTP GET para o método welcome().
    // Ou seja, quando alguém acessar a URL "/" via GET, o método welcome() será chamado.
    @GetMapping("/")
    public String welcome(){
        // Retorna uma mensagem simples para quem acessar a rota "/"
        return "Bem vindo ao Spring Boot Web API";
    }
}
