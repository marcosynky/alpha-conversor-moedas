package one.digitalinovation.padroes_projeto_spring.controller;

import one.digitalinovation.padroes_projeto_spring.model.Cliente;
import one.digitalinovation.padroes_projeto_spring.service.ClienteService; // Serviço que contém a lógica de negócio para os clientes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Definindo o Controller REST para a entidade Cliente
@RestController // Adicionando a anotação @RestController para que esta classe seja tratada como um Controller REST no Spring.
@RequestMapping("/clientes") // Definindo a URL base para todas as requisições de cliente.
public class ClienteRestController {

    @Autowired // Injetando a dependência do serviço ClienteService no Controller.
    private ClienteService clienteService;

    // Método para buscar todos os clientes. Responde a requisições GET na URL "/clientes".
    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        // Retorna todos os clientes do banco de dados.
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    // Método para buscar um cliente específico pelo ID. Responde a requisições GET na URL "/clientes/{id}".
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        // Retorna o cliente com o ID especificado.
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    // Método para inserir um novo cliente. Responde a requisições POST na URL "/clientes".
    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        // O @RequestBody é usado para mapear o corpo da requisição para o objeto Cliente.
        clienteService.inserir(cliente);
        // Retorna o cliente inserido no corpo da resposta com status 200 OK.
        return ResponseEntity.ok(cliente);
    }

    // Método para atualizar as informações de um cliente existente. Responde a requisições PUT na URL "/clientes/{id}".
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        // Atualiza o cliente com o ID especificado e os novos dados fornecidos no corpo da requisição.
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente); // Retorna o cliente atualizado.
    }

    // Método para deletar um cliente. Responde a requisições DELETE na URL "/clientes/{id}".
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Deleta o cliente com o ID especificado.
        clienteService.deletar(id);
        // Retorna uma resposta vazia com status 200 OK indicando que a operação foi bem-sucedida.
        return ResponseEntity.ok().build();
    }
}
