package dio.aula_spring_data_jpa_postgre;

import dio.aula_spring_data_jpa_postgre.model.Usuario;
import dio.aula_spring_data_jpa_postgre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartApp implements CommandLineRunner {

    @Autowired
    private UserRepository repository; // Injetando o repositório para manipular os dados de Usuário

    @Override
    public void run(String... args) throws Exception {

        // Criação de um novo usuário
        Usuario user2 = new Usuario();
        user2.setName("Leticia"); // Definindo o nome do usuário
        user2.setUsername("leca"); // Definindo o nome de nickname do usuário
        user2.setPassword("7@82$@#"); // Definindo a senha do usuário

        // Verificando se o usuário já existe pelo nome de usuário
        if (repository.existsByUsername(user2.getUsername())) {
            System.out.println("Usuário com o nome de usuário " + user2.getUsername() + " já existe.");
        } else {
            // Salvando o novo usuário no banco de dados
            repository.save(user2);
            System.out.println("Usuário " + user2.getUsername() + " salvo com sucesso!");
        }

        // Exibindo todos os usuários cadastrados
        for (Usuario u : repository.findAll()) {
            System.out.println(u); // Imprime a representação do objeto Usuario
        }
    }
}
