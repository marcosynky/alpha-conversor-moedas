package aula.spring_jwt_security.controller;

import aula.spring_jwt_security.dto.Login;
import aula.spring_jwt_security.dto.Sessao;
import aula.spring_jwt_security.model.User;
import aula.spring_jwt_security.repository.UserRepository;
import aula.spring_jwt_security.security.JWTCreator;
import aula.spring_jwt_security.security.JWTObject;
import aula.spring_jwt_security.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
        // Encontrar o usuário pelo nome de usuário
        User user = repository.findByUsername(login.getUsername());

        // Verificar se o usuário existe
        if(user != null) {
            // Comparar a senha fornecida com a senha armazenada (usando PasswordEncoder)
            boolean passwordOk = encoder.matches(login.getPassword(), user.getUsername());

            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }

            // Criando a sessão para enviar o token JWT
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());

            // Criando o objeto JWT
            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));  // Data de emissão
            jwtObject.setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));  // Definindo a expiração do token
            jwtObject.setRoles(user.getRoles().stream().map(role -> role.getName()).toArray(String[]::new)); // Definindo os papéis do usuário

            // Gerando o token JWT usando o JWTCreator
            String token = JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject);
            sessao.setToken(token);  // Definindo o token JWT na sessão

            return sessao;
        } else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
