package aula.spring_security.repository;

import aula.spring_security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.username = :username")
    public Usuario findByUsername(@Param("username") String username);
}
