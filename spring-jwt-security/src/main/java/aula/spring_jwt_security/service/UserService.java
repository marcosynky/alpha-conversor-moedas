package aula.spring_jwt_security.service;



import aula.spring_jwt_security.model.User;
import aula.spring_jwt_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    public void createUser(User user){
        String pass = user.getUsername();
        //criptografando antes de salvar no banco
        user.setUsername(encoder.encode(pass));
        repository.save(user);
    }
}