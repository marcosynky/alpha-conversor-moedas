package dio.aula_spring_data_jpa_postgre.repository;


import dio.aula_spring_data_jpa_postgre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Definindo uma interface de repositório para a entidade Usuario
public interface UserRepository extends JpaRepository<Usuario, Integer> {

   // Definindo um método para buscar um usuário pelo nome de usuário
    boolean findByUsername(String marcosynky);
   // Definindo um método para verificar se um usuário com o nome de usuário fornecido existe
    boolean existsByUsername(String marcosynky);
}
