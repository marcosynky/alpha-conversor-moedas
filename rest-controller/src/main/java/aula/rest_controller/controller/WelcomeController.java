package aula.rest_controller.controller;  // Pacote onde a classe está localizada

// Importando as anotações necessárias do Spring
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Anotação que define que essa classe é um controlador REST (vai lidar com requisições HTTP)
public class WelcomeController {  // Nome da classe (controlador)

    @GetMapping("/welcome")  // Mapeamento de uma requisição HTTP GET para a URL "/welcome"
    public String welcome() {  // Método que será executado quando a URL "/welcome" for acessada
        return "Bem vindo ao Rest Controller! do Spring Boot";  // Retorna uma mensagem para o cliente (usuário)
    }

}
