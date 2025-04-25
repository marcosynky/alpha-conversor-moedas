package aula.rest_controller.controller;  // Pacote onde a classe está localizada

import aula.rest_controller.model.Usuario;  // Importando a classe Usuario
import aula.rest_controller.repository.UsuarioRepository;  // Importando o repositório para acessar os dados do Usuário
import org.springframework.beans.factory.annotation.Autowired;  // Importando a anotação para injeção de dependências
import org.springframework.web.bind.annotation.*;  // Importando as anotações para os métodos HTTP (GET, POST, DELETE, etc.)

import java.util.List;  // Importando List para trabalhar com listas de usuários

@RestController  // Anotação que define a classe como um controlador REST
@RequestMapping("/users")  // Define o caminho base para todas as requisições (URL será /users)
public class UsuarioController {  // Definição da classe controladora

    @Autowired  // Injeção de dependência do repositório UsuarioRepository
    private UsuarioRepository repository;  // Repositório que será usado para acessar os dados de Usuários no banco de dados

    // Método para pegar todos os usuários da base de dados
    @GetMapping()  // Mapeia a requisição GET para a URL /users
    public List<Usuario> getUsers() {  // Retorna uma lista de usuários
        return repository.findAll();  // Chama o método findAll() do repositório para pegar todos os usuários
    }

    // Método para pegar um usuário específico com base no nome de usuário
    @GetMapping("/{username}")  // Mapeia a requisição GET para a URL /users/{username}, onde {username} é uma variável
    public Usuario getOne(@PathVariable("username") String username){  // O valor do {username} é passado para o método
        return repository.findByUsername(username);  // Chama o método findByUsername() do repositório para pegar o usuário pelo nome
    }

    // Método para deletar um usuário com base no ID
    @DeleteMapping("/{id}")  // Mapeia a requisição DELETE para a URL /users/{id}, onde {id} é a variável
    public void deleteUser(@PathVariable("id") Integer id){  // O valor do {id} é passado para o método
        repository.deleteById(id);  // Chama o método deleteById() do repositório para deletar o usuário com o ID fornecido
    }

    // Método para criar um novo usuário (POST)
    @PostMapping()  // Mapeia a requisição POST para a URL /users
    public void postUser(@RequestBody Usuario usuario){  // O corpo da requisição (um objeto Usuario) é mapeado para o parâmetro
        repository.save(usuario);  // Chama o método save() do repositório para salvar o novo usuário no banco de dados
    }
}
