package aula.spring_jwt_security.repository;

import aula.spring_jwt_security.model.User;  // Certifique-se de importar sua classe User
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    // A consulta JPQL foi ajustada para garantir a sintaxe correta
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    public User findByUsername(@Param("username") String username);

    // Verificar se o nome de usuário já existe
    boolean existsByUsername(String username);
}
