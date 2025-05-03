package one.digitalinovation.padroes_projeto_spring.model;

public class ClienteNotFoundException extends RuntimeException {

    // Construtor que recebe uma mensagem personalizada de erro
    public ClienteNotFoundException(String message) {
        super(message);
    }
}
