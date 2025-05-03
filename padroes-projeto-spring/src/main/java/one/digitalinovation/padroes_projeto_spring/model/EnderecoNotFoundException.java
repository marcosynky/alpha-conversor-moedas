package one.digitalinovation.padroes_projeto_spring.model;


public class EnderecoNotFoundException extends RuntimeException {

    // Construtor que recebe uma mensagem personalizada de erro
    public EnderecoNotFoundException(String message) {
        super(message);
    }
}