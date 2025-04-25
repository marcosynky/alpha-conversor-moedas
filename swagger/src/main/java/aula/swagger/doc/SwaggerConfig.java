package aula.swagger.doc;




import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.webmvc.ui.SwaggerConfig;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public SwaggerConfig swaggerConfig() {
        return new SwaggerConfig();
    }
}

