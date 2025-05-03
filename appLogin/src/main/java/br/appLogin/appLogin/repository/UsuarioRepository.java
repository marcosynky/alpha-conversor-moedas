package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    // Método para buscar um usuário pelo email
    Usuario findByEmail(String email);

    // Método para login com email e senha usando consulta SQL nativa
    @Query(value = "SELECT * FROM applogin.usuario WHERE email = :email AND senha = :senha", nativeQuery = true)
    public Usuario login(String email, String senha);
}
