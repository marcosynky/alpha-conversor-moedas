package aula_hadlers.repository;

import aula_hadlers.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository  // Anotação que indica que essa classe é um repositório, ou seja, vai interagir com a camada de dados
public class UsuarioRepository {

    // Método para salvar ou atualizar um usuário no banco de dados
    public void save(Usuario usuario) {
        // Se o usuário não tem ID, significa que é um novo usuário (não existe no banco ainda)
        if(usuario.getId()==null) {
            System.out.println("SALVANDO - Recebendo o usuário na camada repositório");
        } else {  // Se o usuário já tem ID, significa que estamos atualizando um usuário existente
            System.out.println("ATUALIZANDO - Recebendo o usuário na camada repositório");
            System.out.println(usuario.toString());  // Exibe os detalhes do usuário atualizado
        }
    }

    // Método para deletar um usuário pelo ID
    public void deleteById(Integer id) {
        System.out.println("DELETANDO - Recebendo o usuário na camada repositório");
        System.out.println(id);  // Exibe o ID do usuário que será deletado
    }

    // Método para buscar todos os usuários do sistema
    public List<Usuario> findAll() {
        System.out.println("LISTA - Listando os usuários do sistema");

        // Criando uma lista fictícia de usuários
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Marco Antonio", "1234"));
        usuarios.add(new Usuario(2, "Janeth", "123"));

        // Retorna a lista de usuários fictícios
        return usuarios;
    }

    // Método para buscar um usuário pelo ID
    public Usuario findById(Integer id) {
        System.out.printf("BUSCANDO - Recebendo o usuário com o id:  %d para buscar na camada repositório%n", id);

        // Retorna um usuário fictício com o ID fornecido
        return new Usuario(1, "Marco Antonio", "1234");
    }

    // Método para buscar um usuário pelo nome de usuário (login)
    public Usuario findByUsername(String username) {
        System.out.printf("BUSCANDO - Recebendo o usuário com o username:  %s para buscar na camada repositório%n", username);

        // Retorna um usuário fictício com o nome de usuário fornecido
        return new Usuario(1, "Marco Antonio", "1234");
    }

}
