package dio.springboot;  // Declara o pacote onde a classe está localizada

import org.springframework.boot.SpringApplication;  // Importa a classe SpringApplication do Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;  // Importa a anotação SpringBootApplication

// A anotação @SpringBootApplication é usada para marcar a classe principal da aplicação
@SpringBootApplication
public class PrimeiroPassosApplication {  // Define a classe principal da aplicação Spring Boot

	public static void main(String[] args) {  // Método main é o ponto de entrada da aplicação
		SpringApplication.run(PrimeiroPassosApplication.class, args);  // Inicia a aplicação Spring Boot
	}

}
