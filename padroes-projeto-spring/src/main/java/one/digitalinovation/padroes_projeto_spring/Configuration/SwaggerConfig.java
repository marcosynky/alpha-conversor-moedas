package one.digitalinovation.padroes_projeto_spring.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI();
        
        }
        // Configuração do Swagger


}
