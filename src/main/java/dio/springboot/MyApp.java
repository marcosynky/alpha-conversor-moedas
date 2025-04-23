package dio.springboot;  // Declara o pacote onde a classe está localizada

import org.springframework.beans.factory.annotation.Autowired;  // Importa a anotação Autowired, usada para injetar dependências
import org.springframework.boot.CommandLineRunner;  // Importa a interface CommandLineRunner, que permite executar código ao iniciar a aplicação
import org.springframework.stereotype.Component;  // Importa a anotação Component, usada para marcar essa classe como um bean gerenciado pelo Spring

// A anotação @Component indica que essa classe é um "bean" do Spring, ou seja, será gerenciada pelo Spring
@Component
public class MyApp implements CommandLineRunner {  // A classe MyApp implementa a interface CommandLineRunner

    @Autowired  // A anotação @Autowired indica que o Spring deve injetar automaticamente uma instância de Calculadora
    private Calculadora calculadora;  // Declara a dependência de Calculadora, que será injetada pelo Spring

    @Override
    public void run(String... args) throws Exception {  // O método run() será executado ao iniciar a aplicação Spring Boot
        // Dentro desse método, estamos chamando o método somar da classe Calculadora e exibindo o resultado
        System.out.println("O Resultado é: " + calculadora.somar(10, 20));  // Exibe o resultado da soma de 10 e 20
    }
}
