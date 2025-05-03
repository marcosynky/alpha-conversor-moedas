package dio.springboot;  // Declara o pacote onde a classe está localizada

import org.springframework.stereotype.Component;  // Importa a anotação Component do Spring

// A anotação @Component indica que esta classe é um "bean" do Spring
// Isso significa que o Spring irá gerenciar esta classe e suas dependências automaticamente.
@Component
public class Calculadora {  // Define a classe Calculadora, responsável por realizar operações matemáticas

    // Método somar, que recebe dois números e retorna a soma deles
    public int somar(int numero1, int numero2) {
        return numero1 + numero2;  // Retorna a soma dos dois números fornecidos
    }
}
