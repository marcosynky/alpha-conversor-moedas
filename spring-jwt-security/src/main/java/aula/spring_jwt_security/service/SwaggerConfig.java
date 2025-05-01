package aula.spring_jwt_security.service;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Minha API",
                version = "v1",
                description = "Descrição da API de Exemplo"
        )
)
public class SwaggerConfig {
    // Configurações adicionais, se necessário
}

