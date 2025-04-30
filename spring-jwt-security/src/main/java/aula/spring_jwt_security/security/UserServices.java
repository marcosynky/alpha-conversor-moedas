package aula.spring_jwt_security.security;

import aula.spring_jwt_security.model.User;  // Certifique-se de importar a sua entidade User
import aula.spring_jwt_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final PasswordEncoder encoder;

    // Injeção das dependências via construtor
    public UserServices(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    // Método para criar um novo usuário com senha codificada
    public void createUser(User user) {
        String pass = user.getPassword();  // Obtém a senha
        user.setPassword(encoder.encode(pass));  // Codifica a senha
        repository.save(user);  // Salva o usuário no repositório
    }
}
