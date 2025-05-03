package aula.swagger.doc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("M_Alpha API")
                        .version("v001")
                        .description("Esta é a documentação da API gerada pelo Springdoc OpenAPI")
                        .contact(new Contact()
                                .name("M_alpha")
                                .email("malphasite.com.br"))
                        .license(new License()
                                .name("Licença MIT")
                                .url("https://opensource.org/licenses/MIT")
                        )
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("M_alpha site")
                                .url("https://m-alphasite.com.br")
                );
    }
}
