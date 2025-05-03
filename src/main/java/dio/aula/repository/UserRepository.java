package dio.aula.repository;

import dio.aula.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
    // Aqui você já tem todos os métodos básicos como save(), findById(), etc.
}
