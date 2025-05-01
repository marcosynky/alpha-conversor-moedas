package aula.spring_jwt_security.security;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config") // Prefixo do arquivo de propriedades
public class SecurityConfig {
    public static String PREFIX; // Prefixo do token
    public static String KEY; // Chave do token
    public static Long EXPIRATION; // Tempo de expiração do token

    public void setPREFIX(String prefix) { // Múltiplos argumentos
        PREFIX = prefix; // Converte para uma lista
    }

    public void setKEY(String key) {
        KEY = key;
    }

    public void setEXPIRATION(Long expiration) {
        EXPIRATION = expiration;
    }

}
