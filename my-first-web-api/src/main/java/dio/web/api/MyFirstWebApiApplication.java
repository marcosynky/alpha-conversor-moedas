package dio.web.api;

// Importando a classe que permite rodar a aplicação Spring Boot
import org.springframework.boot.SpringApplication;
// Importando a anotação que marca a classe como uma aplicação Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;

// A anotação @SpringBootApplication configura a aplicação e faz o Spring Boot inicializar a aplicação automaticamente
@SpringBootApplication
public class MyFirstWebApiApplication {

	// O método main é o ponto de entrada para a aplicação Java
	public static void main(String[] args) {

		// Rodando a aplicação Spring Boot
		// SpringApplication.run inicia a aplicação Spring Boot e carrega os componentes da aplicação
		SpringApplication.run(MyFirstWebApiApplication.class, args);
	}

}
