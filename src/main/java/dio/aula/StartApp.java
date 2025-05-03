package dio.aula;


import dio.aula.model.Usuario;
import dio.aula.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartApp implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // Criação de um novo usuário sem criptografia de senha
        Usuario user = new Usuario();
        user.setName("Marco Antonio");
        user.setUsername("marcosynky");

        // Senha simples (sem criptografia)
        user.setPassword("12345");

        // Salvando o usuário no banco de dados
        repository.save(user);

        // Exibindo todos os usuários cadastrados
        for (Usuario u : repository.findAll()) {
            System.out.println(u);
        }
    }
}
